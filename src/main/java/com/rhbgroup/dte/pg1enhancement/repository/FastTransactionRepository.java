package com.rhbgroup.dte.pg1enhancement.repository;

import com.rhbgroup.dte.pg1enhancement.model.FFITransactionJoin;
import com.rhbgroup.dte.pg1enhancement.model.FastTransaction;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FastTransactionRepository extends JpaRepository<FastTransaction, Long> {

  @Query(
      "SELECT new com.rhbgroup.dte.pg1enhancement.model.FFITransactionJoin("
          + "fit.srcAccountId, "
          + "fit.destAccountId, "
          + "fit.assetId, "
          + "fit.amount, "
          + "fit.createTime, "
          + "fit.trxHash, "
          + "fit.netAmount, "
          + "fit.errorMsg, "
          + "fit.description, "
          + "fit.fastTransactionId, "
          + "fit.irohaTransactionDetailsId, "
          + "fit.statusString, "
          + "fit.srcAccountType, "
          + "fit.dstAccountType, "
          + "fit.srcBin, "
          + "fit.srcPartcode, "
          + "fit.dstBin, "
          + "fit.dstPartcode, "
          + "fit.details, "
          + "fit.receiverBankAccount, "
          + "fit.qrCode, "
          + "fit.transactionContent, "
          + "fit.extRef, "
          + "ft.transferAmount,"
          + "ft.inputterLogin,"
          + "ft.instructionRef,"
          + "ft.createdTime,"
          + "ft.currentStatus,"
          + "ft.transactionType,"
          + "ft.checkedTime,"
          + "ft.sentTime,"
          + "ft.description,"
          + "ft.controllerLogin,"
          + "ft.controllerNote,"
          + "ft.batchId,"
          + "ft.payeeDetailsId,"
          + "ft.payerDetailsId,"
          + "ft.msgId,"
          + "ft.senderNote,"
          + "ft.senderDetails,"
          + "ft.receiverDetails"
          + ") "
          + "FROM FastIrohaTransaction fit INNER JOIN FastTransaction ft ON ft.id = fit.fastTransactionId  "
          + "WHERE ft.instructionRef =?1 AND fit.srcPartcode =?2 AND fit.dstPartcode =?3 "
          + "ORDER BY fit.createTime DESC")
  List<FFITransactionJoin> findBySourceDestinationPartCodeAndInstructionRef(
      String instructionRef,
      String sourceBankParticipantCode,
      String destinationBankParticipantCode);

  @Query(
      "SELECT new com.rhbgroup.dte.pg1enhancement.model.FFITransactionJoin("
          + "fit.srcAccountId, "
          + "fit.destAccountId, "
          + "fit.assetId, "
          + "fit.amount, "
          + "fit.createTime, "
          + "fit.trxHash, "
          + "fit.netAmount, "
          + "fit.errorMsg, "
          + "fit.description, "
          + "fit.fastTransactionId, "
          + "fit.irohaTransactionDetailsId, "
          + "fit.statusString, "
          + "fit.srcAccountType, "
          + "fit.dstAccountType, "
          + "fit.srcBin, "
          + "fit.srcPartcode, "
          + "fit.dstBin, "
          + "fit.dstPartcode, "
          + "fit.details, "
          + "fit.receiverBankAccount, "
          + "fit.qrCode, "
          + "fit.transactionContent, "
          + "fit.extRef "
          + ") "
          + "FROM FastIrohaTransaction fit "
          + "WHERE fit.trxHash =?1")
  Optional<FFITransactionJoin> findByTransactionHash(String transactionHash);

  List<FastTransaction> findAll();
}
