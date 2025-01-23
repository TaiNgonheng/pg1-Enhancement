package com.rhbgroup.dte.pg1enhancement.mapper;

import static com.rhbgroup.dte.pg1enhancement.util.MapperUtil.getJSONFromObject;
import static com.rhbgroup.dte.pg1enhancement.util.MapperUtil.getObjectFromJSON;

import com.google.gson.Gson;
import com.rhbgroup.dte.pg1enhancement.model.*;
import java.util.Date;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TransactionHistoryMapper {
  @Mapping(source = "irohaTransactionDetailsId", target = "fiTransactionId")
  @Mapping(source = "srcAccountId", target = "sourceAccountId")
  @Mapping(source = "destAccountId", target = "destinationAccountId")
  @Mapping(
      target = "sourceName",
      expression =
          "java(toTransactionContent(fFITransactionJoin.getTransactionContent()).getDetails().getSenderName())")
  @Mapping(
      target = "destinationName",
      expression =
          "java(toTransactionContent(fFITransactionJoin.getTransactionContent()).getDetails().getReceiverName())")
  @Mapping(source = "srcPartcode", target = "sourceBankParticipantCode")
  @Mapping(source = "srcBin", target = "sourceBankName")
  @Mapping(source = "dstPartcode", target = "destinationBankParticipantCode")
  @Mapping(source = "dstBin", target = "destinationBankName")
  @Mapping(
      target = "currencyName",
      expression =
          "java(toTransactionContent(fFITransactionJoin.getTransactionContent()).getDetails().getCurrency())")
  @Mapping(
      source = "createTime",
      target = "transactionCreatedTime",
      qualifiedByName = "DateTimestamp")
  @Mapping(
      target = "sourcePhoneNumber",
      expression =
          "java(toTransactionContent(fFITransactionJoin.getTransactionContent()).getDetails().getSenderNumber())")
  @Mapping(
      target = "destinationPhoneNumber",
      expression =
          "java(toTransactionContent(fFITransactionJoin.getTransactionContent()).getDetails().getReceiverNumber())")
  @Mapping(source = "srcAccountType", target = "sourceWalletType")
  @Mapping(source = "dstAccountType", target = "destinationWalletType")
  @Mapping(source = "statusString", target = "status")
  @Mapping(source = "trxHash", target = "transactionHash")
  @Mapping(source = "assetId", target = "assetId")
  @Mapping(source = "description", target = "description")
  @Mapping(source = "errorMsg", target = "errorMessage")
  @Mapping(source = "receiverBankAccount", target = "receiverBankAccount")
  @Mapping(source = "currentStatus", target = "fstTrxStatus")
  @Mapping(
      target = "sourceAddress",
      expression =
          "java(toTransactionContent(fFITransactionJoin.getTransactionContent()).getDetails().getSenderAddress())")
  @Mapping(
      target = "destinationAddress",
      expression =
          "java(toTransactionContent(fFITransactionJoin.getTransactionContent()).getDetails().getReceiverAddress())")
  @Mapping(source = "qrCode", target = "qrCode")
  @Mapping(target = "fee", expression = "java(BigDecimal.ZERO)")
  @Mapping(target = "tax", expression = "java(BigDecimal.ZERO)")
  @Mapping(
      source = "transactionContent",
      target = "transactionContent",
      qualifiedByName = "JSONToTransactionContent")
  TransactionHistoryResponse toFFITransactionJoinDto(FFITransactionJoin fFITransactionJoin);

  TransactionHistoryResponseTransactionContent fromOrigin(
      OriginTransactionContent originTransactionContent);

  default String map(OriginTransactionContentDetailsSourceOfFunds value) {
    return getJSONFromObject(value);
  }

  @Named("ObjectToJSON")
  default String map(Object value) {
    return getJSONFromObject(value);
  }

  @Named("JSONToObject")
  default Object map(String value) {
    return getObjectFromJSON(value, Object.class);
  }

  @Named("JSONToTransactionContent")
  default TransactionHistoryResponseTransactionContent toTransactionContent(String json) {
    if (json == null) return null;
    Gson gson = new Gson();
    OriginTransactionContent origin = gson.fromJson(json, OriginTransactionContent.class);
    return fromOrigin(origin);
  }

  @Named("DateTimestamp")
  default Long getTimestampFromDate(Date date) {
    return date.getTime();
  }
}
