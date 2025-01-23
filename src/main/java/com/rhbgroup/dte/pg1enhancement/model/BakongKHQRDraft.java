package com.rhbgroup.dte.pg1enhancement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BakongKHQRDraft {
  private long id;
  private String trxHash;
  private String amount;
  private String assetId;
  private String accountNumber;
  private String accountName;
  private String billNumber;
  private String srcAccountId;
  private String srcAccountType;
  private String srcPartCode;
  private String dstPartCode;
  private String dstAccountType;
  private String senderAccount;
  private String alternativeSenderAccount;
  private String senderName;
  private String alternativeSenderName;
  private String createTime;
}
