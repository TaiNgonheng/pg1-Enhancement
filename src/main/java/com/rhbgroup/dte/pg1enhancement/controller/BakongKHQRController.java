package com.rhbgroup.dte.pg1enhancement.controller;

import com.rhbgroup.dte.pg1enhancement.api.BakongKhqrApiDelegate;
import com.rhbgroup.dte.pg1enhancement.config.ApplicationProperties;
import com.rhbgroup.dte.pg1enhancement.exception.RequiredParamMismatchException;
import com.rhbgroup.dte.pg1enhancement.model.BakongKHQRStatusUpdateResponse;
import com.rhbgroup.dte.pg1enhancement.model.IncomingTransactionStatusUpdateRequest;
import com.rhbgroup.dte.pg1enhancement.model.PendingBakongKHQRReponse;
import com.rhbgroup.dte.pg1enhancement.service.ifc.BakongKHQRServiceIfc;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class BakongKHQRController implements BakongKhqrApiDelegate {
  private BakongKHQRServiceIfc bakongKHQRService;
  private final ApplicationProperties applicationProperties;

  @Override
  public ResponseEntity<PendingBakongKHQRReponse> getIncomingBakongKHQR(
      Integer page, Integer size, String status) {
    log.info("Get Incoming Bakong KH QR by status", status);
    if (size == null) size = applicationProperties.getGetIncomingBakongKHQRPageSize();

    return ResponseEntity.ok(bakongKHQRService.getIncomingBakongKHQR(page, size, status));
  }

  @Override
  public ResponseEntity<BakongKHQRStatusUpdateResponse> incomingTransactionStatusUpdate(
      IncomingTransactionStatusUpdateRequest updateRequest) {
    if (updateRequest != null
        && updateRequest.getTransactionHash() != null
        && updateRequest.getStatus() != null) {
      bakongKHQRService.incomingTransactionStatusUpdate(
          updateRequest.getTransactionHash(), updateRequest.getStatus().getValue());
      return ResponseEntity.status(HttpStatus.OK)
          .body(
              new BakongKHQRStatusUpdateResponse()
                  .status(BakongKHQRStatusUpdateResponse.StatusEnum.ACSP));
    } else {
      throw new RequiredParamMismatchException(
          "Expecting non-empty transactionHash and status fields");
    }
  }
}
