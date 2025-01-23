package com.rhbgroup.dte.pg1enhancement.service.impl;

import com.rhbgroup.dte.pg1enhancement.constant.ErrorMessageConstants;
import com.rhbgroup.dte.pg1enhancement.exception.ResourceNotFoundException;
import com.rhbgroup.dte.pg1enhancement.mapper.TransactionHistoryMapper;
import com.rhbgroup.dte.pg1enhancement.model.FFITransactionJoin;
import com.rhbgroup.dte.pg1enhancement.model.TransactionHistoryResponse;
import com.rhbgroup.dte.pg1enhancement.repository.FastTransactionRepository;
import com.rhbgroup.dte.pg1enhancement.service.ifc.FFITransactionServiceIfc;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Slf4j
@Transactional(readOnly = true)
@Service
public class FFITransactionServiceImpl implements FFITransactionServiceIfc {
  private final FastTransactionRepository fastTransactionRepository;
  private TransactionHistoryMapper mapper;

  @Override
  public TransactionHistoryResponse getTransactionHistoryByPaymentInfo(
      String instructionRef,
      String sourceBankParticipantCode,
      String destinationBankParticipantCode) {
    List<FFITransactionJoin> fFITransactionJoinSearchResult =
        fastTransactionRepository.findBySourceDestinationPartCodeAndInstructionRef(
            instructionRef, sourceBankParticipantCode, destinationBankParticipantCode);
    if (!fFITransactionJoinSearchResult.isEmpty()) {
      return mapper.toFFITransactionJoinDto(fFITransactionJoinSearchResult.get(0));
    }
    throw new ResourceNotFoundException(
        ErrorMessageConstants.RESOURCE_NOT_FOUND_BY_INFO
            + "instructionRef: "
            + instructionRef
            + ", destinationBankParticipantCode: "
            + destinationBankParticipantCode
            + ", sourceBankParticipantCode: "
            + sourceBankParticipantCode);
  }

  @Override
  public TransactionHistoryResponse getTransactionHistoryByTransactionHash(String transactionHash) {
    FFITransactionJoin fFITransactionJoin =
        fastTransactionRepository
            .findByTransactionHash(transactionHash)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        ErrorMessageConstants.RESOURCE_NOT_FOUND_BY_INFO
                            + "transactionHash"
                            + transactionHash));

    return mapper.toFFITransactionJoinDto(fFITransactionJoin);
  }
}
