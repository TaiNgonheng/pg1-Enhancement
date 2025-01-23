package com.rhbgroup.dte.pg1enhancement.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fst_iroha_trx")
public class FastIrohaTransaction {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "src_account_id")
  private String srcAccountId;

  @Column(name = "dest_account_id")
  private String destAccountId;

  @Column(name = "asset_id")
  private String assetId;

  @Column(name = "amount")
  private Float amount;

  @Column(name = "create_time")
  private Date createTime;

  @Column(name = "trx_hash")
  private String trxHash;

  @Column(name = "net_amount")
  private Float netAmount;

  @Column(name = "error_msg")
  private String errorMsg;

  @Column(name = "description")
  private String description;

  @Column(name = "fast_transaction_id")
  private Long fastTransactionId;

  @Column(name = "iroha_transaction_details_id")
  private Long irohaTransactionDetailsId;

  @Column(name = "status_string")
  private String statusString;

  @Column(name = "src_account_type")
  private String srcAccountType;

  @Column(name = "dst_account_type")
  private String dstAccountType;

  @Column(name = "src_bin")
  private String srcBin;

  @Column(name = "src_partcode")
  private String srcPartcode;

  @Column(name = "dst_bin")
  private String dstBin;

  @Column(name = "dst_partcode")
  private String dstPartcode;

  @JsonRawValue
  @Column(name = "details", columnDefinition = "json")
  private String details;

  @Column(name = "receiver_bank_account")
  private String receiverBankAccount;

  @Column(name = "qr_code")
  private String qrCode;

  @JsonRawValue
  @Column(name = "transaction_content", columnDefinition = "json")
  private String transactionContent;

  @Column(name = "ext_ref")
  private String extRef;
}
