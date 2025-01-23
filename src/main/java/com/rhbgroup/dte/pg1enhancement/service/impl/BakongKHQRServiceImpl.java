package com.rhbgroup.dte.pg1enhancement.service.impl;

import static com.rhbgroup.dte.pg1enhancement.constant.BusinessConstraints.INCOMING_TRANSACTION_STATUS_UPDATE_ALLOW_STATUS;

import com.rhbgroup.dte.pg1enhancement.constant.ErrorMessageConstants;
import com.rhbgroup.dte.pg1enhancement.exception.BadRequestException;
import com.rhbgroup.dte.pg1enhancement.exception.ResourceNotFoundException;
import com.rhbgroup.dte.pg1enhancement.mapper.BakongKHQRMapper;
import com.rhbgroup.dte.pg1enhancement.model.BakongKHQRDraft;
import com.rhbgroup.dte.pg1enhancement.model.BakongKHQREntity;
import com.rhbgroup.dte.pg1enhancement.model.BakongKHQRJoinFastIrohaTransaction;
import com.rhbgroup.dte.pg1enhancement.model.PendingBakongKHQRReponse;
import com.rhbgroup.dte.pg1enhancement.repository.BakongKHQRRepository;
import com.rhbgroup.dte.pg1enhancement.service.ifc.BakongKHQRServiceIfc;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Slf4j
@Transactional
@Service
public class BakongKHQRServiceImpl implements BakongKHQRServiceIfc {
  private BakongKHQRRepository bakongKHQRRepository;
  private BakongKHQRMapper bakongKHQRMapper;

  @Override
  public void incomingTransactionStatusUpdate(String transactionHash, String status) {
    BakongKHQREntity bakongKHQR =
        bakongKHQRRepository
            .findByTrxHash(transactionHash)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        ErrorMessageConstants.RESOURCE_NOT_FOUND_BY_INFO
                            + "transactionHash: "
                            + transactionHash));

    String rule = String.format("%s -> %s", bakongKHQR.getStatus(), status);
    if (!Arrays.asList(INCOMING_TRANSACTION_STATUS_UPDATE_ALLOW_STATUS).contains(rule)) {
      throw new BadRequestException(
          String.format("Cannot update the status from %s to %s", bakongKHQR.getStatus(), status));
    }
    bakongKHQR.setStatus(status);
    bakongKHQRRepository.save(bakongKHQR);
  }

  @Override
  public PendingBakongKHQRReponse getIncomingBakongKHQR(Integer page, Integer size, String status) {
    Page<BakongKHQRJoinFastIrohaTransaction> bakongKHQRJoinFastIrohaTransaction =
        bakongKHQRRepository.getBakongKHQRFastIrohaTransactionByStatus(
            status, PageRequest.of(page, size, Sort.by("createTime")));
    PendingBakongKHQRReponse response = new PendingBakongKHQRReponse();
    response.setTotalPages(bakongKHQRJoinFastIrohaTransaction.getTotalPages());
    response.setTotalItems(bakongKHQRJoinFastIrohaTransaction.getTotalElements());
    response.setCurrentPage(bakongKHQRJoinFastIrohaTransaction.getNumber());
    List<BakongKHQRDraft> bakongKHQRList =
        bakongKHQRMapper.toBakongKHQRDraftList(bakongKHQRJoinFastIrohaTransaction.getContent())
            .stream()
            .map(
                x -> {
                  if (x.getSrcAccountType().equals("BANK")) {
                    if (x.getSenderName() == null) {
                      x.setSenderName(x.getAlternativeSenderName());
                    }
                    if (x.getSenderAccount() == null) {
                      x.setSenderAccount(x.getAlternativeSenderAccount());
                    }
                  }
                  return x;
                })
            .collect(Collectors.toList());

    response.setBakongKHQR(
        bakongKHQRMapper.fromBakongKHQRDraftToPendingBakongKHQRResponse(bakongKHQRList));
    return response;
  }

  @Override
  public void validateBakongKHQR() {
    /* Set status to INVALID if the transaction status is pending and
    (qr_code is null or empty or both of MerchantId and AccountInformation are null) */

    List<BakongKHQREntity> bakongKHQRList = bakongKHQRRepository.findByStatus("PENDING");
    List<BakongKHQREntity> inValidBakongKHQRList =
        bakongKHQRList.stream()
            .filter(
                x ->
                    x.getQrCode() == null
                        || x.getQrCode().isEmpty()
                        || bakongKHQRMapper.getBakongAccountNumber(x.getQrCode()) == null)
            .collect(Collectors.toList());

    for (BakongKHQREntity entity : inValidBakongKHQRList) {
      log.info("Invalidating BakongKHQR with trx hash: {}", entity.getTrxHash());
      entity.setStatus("INVALID");
      bakongKHQRRepository.save(entity);
    }
  }
}
