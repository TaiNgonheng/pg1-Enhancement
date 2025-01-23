package com.rhbgroup.dte.pg1enhancement.controller;

import com.rhbgroup.dte.pg1enhancement.api.CiftpApiDelegate;
import com.rhbgroup.dte.pg1enhancement.model.CIFTPTransactionInquiryRequest;
import com.rhbgroup.dte.pg1enhancement.model.CIFTPTransactionInquiryResponse;
import com.rhbgroup.dte.pg1enhancement.service.ifc.CIFTPServiceIfc;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CIFTPController implements CiftpApiDelegate {

  private final CIFTPServiceIfc ciftpService;

  @Override
  public ResponseEntity<CIFTPTransactionInquiryResponse> inquireCIFTPTransaction(
      CIFTPTransactionInquiryRequest request) {
    log.info("Start CIFTP transaction inquiry API");
    return ResponseEntity.ok(ciftpService.inquireCIFTPTransaction(request));
  }
}
