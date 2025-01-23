package com.rhbgroup.dte.pg1enhancement.mapper;

import com.rhbgroup.dte.pg1enhancement.model.CIFTPTransactionInquiryResponse;
import com.rhbgroup.dte.pg1enhancement.model.CIFTPWebHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CIFTPMapper {

  @Mapping(source = "currencyName", target = "currency")
  CIFTPTransactionInquiryResponse toCIFTPTransactionHistoryInquiryResponse(
      CIFTPWebHistory ciftpWebHistory);
}
