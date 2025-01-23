package com.rhbgroup.dte.pg1enhancement.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import java.time.Instant;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bk_khqr")
@TypeDefs({@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)})
public class BakongKHQREntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "src_account_id")
  private String srcAccountId;

  @Column(name = "asset_id")
  private String assetId;

  @Column(name = "amount")
  private String amount;

  @Column(name = "create_time")
  private Instant createTime;

  @Column(name = "trx_hash")
  private String trxHash;

  @Column(name = "qr_code")
  private String qrCode;

  @Column(name = "trn_type")
  private String trnType;

  @Column(name = "status")
  private String status;

  @Column(name = "src_account_type")
  private String srcAccountType;

  @Column(name = "dst_account_type")
  private String dstAccountType;

  @Column(name = "src_partcode")
  private String srcPartCode;

  @Column(name = "dst_partcode")
  private String dstPartCode;

  @Type(type = "jsonb")
  @Column(name = "sender_details", columnDefinition = "jsonb")
  private String senderDetails;
}
