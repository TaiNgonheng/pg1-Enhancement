package com.rhbgroup.dte.pg1enhancement.model;

import com.fasterxml.jackson.annotation.JsonRawValue;
import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fst_transaction")
public class FastTransaction {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "transfer_amount")
  public Float transferAmount;

  @Column(name = "inputter_login")
  public String inputterLogin;

  @Column(name = "instruction_ref")
  public String instructionRef;

  @Column(name = "created_time")
  public Date createdTime;

  @Column(name = "current_status")
  public String currentStatus;

  @Column(name = "transaction_type")
  public String transactionType;

  @Column(name = "checked_time")
  public String checkedTime;

  @Column(name = "sent_time")
  public Date sentTime;

  @Column(name = "description")
  public String description;

  @Column(name = "controller_login")
  public String controllerLogin;

  @Column(name = "controller_note")
  public String controllerNote;

  @Column(name = "batch_id")
  public Long batchId;

  @Column(name = "currency_id")
  public Long currencyId;

  @Column(name = "payee_details_id")
  public Long payeeDetailsId;

  @Column(name = "payer_details_id")
  public Long payerDetailsId;

  @Column(name = "msg_id")
  public String msgId;

  @Column(name = "sender_note")
  public String senderNote;

  @JsonRawValue
  @Column(name = "sender_details", columnDefinition = "json")
  public String senderDetails;

  @JsonRawValue
  @Column(name = "receiver_details", columnDefinition = "json")
  public String receiverDetails;
}
