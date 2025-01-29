package com.rhbgroup.dte.pg1enhancement.mapper;

import com.google.gson.Gson;
import com.rhbgroup.dte.pg1enhancement.model.BakongKHQRDraft;
import com.rhbgroup.dte.pg1enhancement.model.BakongKHQRJoinFastIrohaTransaction;
import com.rhbgroup.dte.pg1enhancement.model.OriginTransactionContent;
import com.rhbgroup.dte.pg1enhancement.model.SenderDetails;
import com.rhbgroup.dte.pg1enhancement.util.PropertyAccessor;
import java.math.BigDecimal;
import java.util.List;
import kh.org.nbc.bakong_khqr.BakongKHQR;
import kh.org.nbc.bakong_khqr.model.KHQRDecodeData;
import kh.org.nbc.bakong_khqr.utils.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BakongKHQRMapper {
  Gson gson = new Gson();

  @Named("GetBakongAccountNumber")
  default String getBakongAccountNumber(String qrCode) {
    if (qrCode == null) {
      return null;
    }
    KHQRDecodeData decodeData = kh.org.nbc.bakong_khqr.BakongKHQR.decode(qrCode).getData();
    if (decodeData.getMerchantId() != null) {
      return decodeData.getMerchantId();
    } else {
      return decodeData.getAccountInformation();
    }
  }

  @Named("GetMerchantName")
  default String getMerchantName(String qrCode) {
    if (qrCode == null) {
      return null;
    }
    return kh.org.nbc.bakong_khqr.BakongKHQR.decode(qrCode).getData().getMerchantName();
  }

  @Named("GetBillNumber")
  default String getBillNumber(String qrCode) {
    if (qrCode == null) {
      return null;
    }
    return kh.org.nbc.bakong_khqr.BakongKHQR.decode(qrCode).getData().getBillNumber();
  }

  @Named("GetSenderAccount")
  default String getSenderAccount(SenderDetails senderDetails) {
    if (senderDetails == null) {
      return null;
    }
    return senderDetails.getAccountNumber();
  }

  @Named("GetSenderName")
  default String getSenderName(SenderDetails senderDetails) {
    if (senderDetails == null) {
      return null;
    }
    return senderDetails.getFullName();
  }

  @Named("GetAlternativeSenderAccount")
  default String getAlternativeSenderAccount(String transactionContent) {
    if (transactionContent == null) {
      return null;
    }
    return PropertyAccessor.valueOrDefault(
        () ->
            gson.fromJson(transactionContent, OriginTransactionContent.class)
                .getDetails()
                .getSourceOfFunds()
                .getPhoneNumber(),
        null);
  }

  @Named("GetAlternativeSenderName")
  default String getAlternativeSenderName(String transactionContent) {
    if (transactionContent == null) {
      return null;
    }

    return PropertyAccessor.valueOrDefault(
        () ->
            gson.fromJson(transactionContent, OriginTransactionContent.class)
                .getDetails()
                .getSourceOfFunds()
                .getName(),
        null);
  }

  @Named("AssertIdAdjustment")
  default String assertIdAdjustment(String assertId) {
    if (StringUtils.isNotBlank(assertId)) {
      return assertId.replace("#nbc", "").toUpperCase();
    }
    return assertId;
  }

  @Named("Decimal2DigitsFormatter")
  default BigDecimal decimal2DigitsFormat(String input) {
    if (StringUtils.isNotBlank(input)) {
      BigDecimal bd = new BigDecimal(input);
      return bd.setScale(2);
    }
    return null;
  }

  List<BakongKHQRDraft> toBakongKHQRDraftList(
      List<BakongKHQRJoinFastIrohaTransaction> bakongKHQRJoinFastIrohaTransaction);

  @Mapping(source = "qrCode", target = "accountNumber", qualifiedByName = "GetBakongAccountNumber")
  @Mapping(source = "qrCode", target = "accountName", qualifiedByName = "GetMerchantName")
  @Mapping(source = "qrCode", target = "billNumber", qualifiedByName = "GetBillNumber")
  @Mapping(source = "senderDetails", target = "senderAccount", qualifiedByName = "GetSenderAccount")
  @Mapping(source = "senderDetails", target = "senderName", qualifiedByName = "GetSenderName")
  @Mapping(
      source = "transactionContent",
      target = "alternativeSenderAccount",
      qualifiedByName = "GetAlternativeSenderAccount")
  @Mapping(
      source = "transactionContent",
      target = "alternativeSenderName",
      qualifiedByName = "GetAlternativeSenderName")
  @Mapping(source = "assetId", target = "assetId", qualifiedByName = "AssertIdAdjustment")
  @Mapping(source = "amount", target = "amount", qualifiedByName = "Decimal2DigitsFormatter")
  BakongKHQRDraft toBakongKHQRDraft(
      BakongKHQRJoinFastIrohaTransaction bakongKHQRJoinFastIrohaTransaction);

  BakongKHQR toBakongKHQR(BakongKHQRDraft bakongKHQRDraft);

  List<com.rhbgroup.dte.pg1enhancement.model.BakongKHQR>
      fromBakongKHQRDraftToPendingBakongKHQRResponse(List<BakongKHQRDraft> bakongKHQRDraftList);
}
