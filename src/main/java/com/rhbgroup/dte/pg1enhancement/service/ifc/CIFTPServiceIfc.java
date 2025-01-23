package com.rhbgroup.dte.pg1enhancement.service.ifc;

import com.rhbgroup.dte.pg1enhancement.model.CIFTPTransactionInquiryRequest;
import com.rhbgroup.dte.pg1enhancement.model.CIFTPTransactionInquiryResponse;

public interface CIFTPServiceIfc {

  CIFTPTransactionInquiryResponse inquireCIFTPTransaction(CIFTPTransactionInquiryRequest request);
}
