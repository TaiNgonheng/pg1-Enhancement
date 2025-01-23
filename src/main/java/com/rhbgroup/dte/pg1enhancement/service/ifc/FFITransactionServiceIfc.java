package com.rhbgroup.dte.pg1enhancement.service.ifc;

import com.rhbgroup.dte.pg1enhancement.model.TransactionHistoryResponse;

public interface FFITransactionServiceIfc {
  public TransactionHistoryResponse getTransactionHistoryByPaymentInfo(
      String instructionRef, String receiverAccountNo, String senderAccountNo);

  public TransactionHistoryResponse getTransactionHistoryByTransactionHash(String transactionHash);
}
