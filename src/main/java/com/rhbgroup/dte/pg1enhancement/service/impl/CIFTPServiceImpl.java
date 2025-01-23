package com.rhbgroup.dte.pg1enhancement.service.impl;

import com.rhbgroup.dte.pg1enhancement.client.CIFTPClient;
import com.rhbgroup.dte.pg1enhancement.config.ApplicationProperties;
import com.rhbgroup.dte.pg1enhancement.exception.BadRequestException;
import com.rhbgroup.dte.pg1enhancement.mapper.CIFTPMapper;
import com.rhbgroup.dte.pg1enhancement.model.*;
import com.rhbgroup.dte.pg1enhancement.service.ifc.CIFTPServiceIfc;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CIFTPServiceImpl implements CIFTPServiceIfc {

  private final ApplicationProperties properties;
  private final CIFTPClient ciftpClient;
  private final CIFTPMapper ciftpMapper;

  @Override
  public CIFTPTransactionInquiryResponse inquireCIFTPTransaction(
      CIFTPTransactionInquiryRequest request) {
    CIFTPChannel channel =
        isLargeValueTransaction(request) ? CIFTPChannel.LARGE_VALUE : CIFTPChannel.RETAIL;
    CIFTPWebHistoryResponse webHistory =
        ciftpClient.getCIFTPWebHistoryResponse(channel, request.getTransactionHash());
    if (webHistory == null || webHistory.size() != 1) {
      throw new BadRequestException("Cannot find unique transaction for the request");
    }
    return ciftpMapper.toCIFTPTransactionHistoryInquiryResponse(webHistory.get(0));
  }

  private boolean isLargeValueTransaction(CIFTPTransactionInquiryRequest request) {
    double largeValueThreshold =
        request.getCurrency().equals(Currency.USD)
            ? properties.getCiftpLargeValueUSD()
            : properties.getCiftpLargeValueKHR();
    return request.getAmount() >= largeValueThreshold;
  }
}
