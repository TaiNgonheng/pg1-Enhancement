package com.rhbgroup.dte.pg1enhancement.repository;

import com.rhbgroup.dte.pg1enhancement.model.BakongKHQREntity;
import com.rhbgroup.dte.pg1enhancement.model.BakongKHQRJoinFastIrohaTransaction;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BakongKHQRRepository extends JpaRepository<BakongKHQREntity, Long> {

  @Query(
      "SELECT new com.rhbgroup.dte.pg1enhancement.model.BakongKHQRJoinFastIrohaTransaction("
          + "bkhqr.id, "
          + "bkhqr.srcAccountId, "
          + "bkhqr.assetId, "
          + "bkhqr.amount, "
          + "bkhqr.createTime, "
          + "bkhqr.trxHash, "
          + "bkhqr.qrCode, "
          + "bkhqr.trnType, "
          + "bkhqr.status, "
          + "bkhqr.srcAccountType, "
          + "bkhqr.dstAccountType, "
          + "bkhqr.srcPartCode, "
          + "bkhqr.dstPartCode, "
          + "bkhqr.senderDetails, "
          + "fit.transactionContent"
          + ") "
          + "FROM BakongKHQREntity bkhqr INNER JOIN FastIrohaTransaction fit ON bkhqr.trxHash = fit.trxHash  "
          + "WHERE bkhqr.status = ?1 AND bkhqr.qrCode IS NOT NULL AND bkhqr.qrCode != '' "
          + "ORDER BY fit.createTime ASC")
  Page<BakongKHQRJoinFastIrohaTransaction> getBakongKHQRFastIrohaTransactionByStatus(
      String status, Pageable pageable);

  Optional<BakongKHQREntity> findByTrxHash(String transactionHash);

  List<BakongKHQREntity> findByStatus(String status);
}
