package com.rhbgroup.dte.pg1enhancement.controller;

import com.rhbgroup.dte.pg1enhancement.api.FastTransactionApiDelegate;
import com.rhbgroup.dte.pg1enhancement.exception.RequiredParamMismatchException;
import com.rhbgroup.dte.pg1enhancement.model.TransactionHistoryResponse;
import com.rhbgroup.dte.pg1enhancement.service.impl.FFITransactionServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

// implements FastTransactionApiDelegate
@Slf4j
@Service
@AllArgsConstructor
public class TransactionController implements FastTransactionApiDelegate {
  private final FFITransactionServiceImpl transactionService;

  @Override
  public ResponseEntity<TransactionHistoryResponse> getTransactionHistory(
      @RequestParam(name = "sender") String sourceBankParticipantCode,
      @RequestParam(name = "receiver") String destinationBankParticipantCode,
      @RequestParam(name = "payment_ref") String instructionRef,
      @RequestParam(name = "transactionHash") String transactionHash) {
    if (transactionHash != null) {
      return getTransactionHistoryByTransactionHash(transactionHash);
    } else if (sourceBankParticipantCode != null
        && destinationBankParticipantCode != null
        && instructionRef != null) {
      return getTransactionHistoryByPaymentInfo(
          instructionRef, sourceBankParticipantCode, destinationBankParticipantCode);
    } else {
      throw new RequiredParamMismatchException(
          "Expecting (sender AND receiver AND payment_ref) OR transactionHash parameter");
    }
  }

  public ResponseEntity<TransactionHistoryResponse> getTransactionHistoryByPaymentInfo(
      String instructionRef,
      String sourceBankParticipantCode,
      String destinationBankParticipantCode) {
    log.info(
        String.format(
            "API: getTransactionHistoryByPaymentInfo-> sender: %s, receiver: %s, ref: %s",
            sourceBankParticipantCode, destinationBankParticipantCode, instructionRef));

    TransactionHistoryResponse transactionHistoryResponse =
        transactionService.getTransactionHistoryByPaymentInfo(
            instructionRef, sourceBankParticipantCode, destinationBankParticipantCode);
    return ResponseEntity.ok(transactionHistoryResponse);
  }

  public ResponseEntity<TransactionHistoryResponse> getTransactionHistoryByTransactionHash(
      String transactionHash) {
    log.info("API: getTransactionHistoryByTransactionHash: " + transactionHash);

    TransactionHistoryResponse transactionHistoryResponse =
        transactionService.getTransactionHistoryByTransactionHash(transactionHash);
    return ResponseEntity.ok(transactionHistoryResponse);
  }
}
