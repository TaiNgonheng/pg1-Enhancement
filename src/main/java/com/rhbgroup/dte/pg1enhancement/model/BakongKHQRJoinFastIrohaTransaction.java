package com.rhbgroup.dte.pg1enhancement.model;

import java.time.Instant;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BakongKHQRJoinFastIrohaTransaction {
  // BakongKHQR
  private long id;
  private String srcAccountId;
  private String assetId;
  private String amount;
  private Instant createTime;
  private String trxHash;
  private String qrCode;
  private String trnType;
  private String status;
  private String srcAccountType;
  private String dstAccountType;
  private String srcPartCode;
  private String dstPartCode;
  private SenderDetails senderDetails;

  // FastIrohaTransaction
  private String irohaSrcAccountId;
  private String destAccountId;
  private String irohaAssetId;
  private Float irohaAmount;
  private Date irohaCreateTime;
  private String irohaTrxHash;
  private Float netAmount;
  private String errorMsg;
  private String description;
  private Long fastTransactionId;
  private Long irohaTransactionDetailsId;
  private String statusString;
  private String irohaSrcAccountType;
  private String irohaDstAccountType;
  private String srcBin;
  private String irohaSrcPartcode;
  private String dstBin;
  private String irohaDstPartcode;
  private String details;
  private String receiverBankAccount;
  private String irohaQrCode;
  private String transactionContent;
  private String extRef;

  public BakongKHQRJoinFastIrohaTransaction(
      long id,
      String srcAccountId,
      String assetId,
      String amount,
      Instant createTime,
      String trxHash,
      String qrCode,
      String trnType,
      String status,
      String srcAccountType,
      String dstAccountType,
      String srcPartCode,
      String dstPartCode,
      Object senderDetails,
      String transactionContent) {
    this.id = id;
    this.srcAccountId = srcAccountId;
    this.assetId = assetId;
    this.amount = amount;
    this.createTime = createTime;
    this.trxHash = trxHash;
    this.qrCode = qrCode;
    this.trnType = trnType;
    this.status = status;
    this.srcAccountType = srcAccountType;
    this.dstAccountType = dstAccountType;
    this.srcPartCode = srcPartCode;
    this.dstPartCode = dstPartCode;
    this.senderDetails = (SenderDetails) senderDetails;
    this.transactionContent = transactionContent;
  }
}
