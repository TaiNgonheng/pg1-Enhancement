package com.rhbgroup.dte.pg1enhancement.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FFITransactionJoin {

  // Fast Iroha Transaction Fields
  private String srcAccountId;
  private String destAccountId;
  private String assetId;
  private Float amount;
  private Date createTime;
  private String trxHash;
  private Float netAmount;
  private String errorMsg;
  private String description;
  private Long fastTransactionId;
  private Long irohaTransactionDetailsId;
  private String statusString;
  private String srcAccountType;
  private String dstAccountType;
  private String srcBin;
  private String srcPartcode;
  private String dstBin;
  private String dstPartcode;
  private String details;
  private String receiverBankAccount;
  private String qrCode;
  private String transactionContent;
  private String extRef;

  // Fast Transaction Fields
  private Float transferAmount;
  private String inputterLogin;
  private String instructionRef;
  private Date createdTime;
  private String currentStatus;
  private String transactionType;
  private String checkedTime;
  private Date sentTime;
  private String fastDescription;
  private String controllerLogin;
  private String controllerNote;
  private Long batchId;
  private Long payeeDetailsId;
  private Long payerDetailsId;
  private String msgId;
  private String senderNote;
  private String senderDetails;
  private String receiverDetails;

  public FFITransactionJoin(
      String srcAccountId,
      String destAccountId,
      String assetId,
      Float amount,
      Date createTime,
      String trxHash,
      Float netAmount,
      String errorMsg,
      String description,
      Long fastTransactionId,
      Long irohaTransactionDetailsId,
      String statusString,
      String srcAccountType,
      String dstAccountType,
      String srcBin,
      String srcPartcode,
      String dstBin,
      String dstPartcode,
      String details,
      String receiverBankAccount,
      String qrCode,
      String transactionContent,
      String extRef) {
    this.srcAccountId = srcAccountId;
    this.destAccountId = destAccountId;
    this.assetId = assetId;
    this.amount = amount;
    this.createTime = createTime;
    this.trxHash = trxHash;
    this.netAmount = netAmount;
    this.errorMsg = errorMsg;
    this.description = description;
    this.fastTransactionId = fastTransactionId;
    this.irohaTransactionDetailsId = irohaTransactionDetailsId;
    this.statusString = statusString;
    this.srcAccountType = srcAccountType;
    this.dstAccountType = dstAccountType;
    this.srcBin = srcBin;
    this.srcPartcode = srcPartcode;
    this.dstBin = dstBin;
    this.dstPartcode = dstPartcode;
    this.details = details;
    this.receiverBankAccount = receiverBankAccount;
    this.qrCode = qrCode;
    this.transactionContent = transactionContent;
    this.extRef = extRef;
  }
}
