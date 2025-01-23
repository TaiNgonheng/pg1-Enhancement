package com.rhbgroup.dte.pg1enhancement.constant;

public class BusinessConstraints {
  public static final String[] INCOMING_TRANSACTION_STATUS_UPDATE_ALLOW_STATUS = {
    "PENDING -> COMPLETED",
    "PENDING -> FAILED",
    "PENDING -> REFUND_FAILED",
    "PENDING -> DUPLICATE",
    "FAILED -> REFUND_FAILED",
  };
}
