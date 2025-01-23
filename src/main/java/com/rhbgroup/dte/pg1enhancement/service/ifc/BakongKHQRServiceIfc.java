package com.rhbgroup.dte.pg1enhancement.service.ifc;

import com.rhbgroup.dte.pg1enhancement.model.PendingBakongKHQRReponse;

public interface BakongKHQRServiceIfc {
  void incomingTransactionStatusUpdate(String transactionHash, String status);

  PendingBakongKHQRReponse getIncomingBakongKHQR(Integer page, Integer size, String status);

  void validateBakongKHQR();
}
