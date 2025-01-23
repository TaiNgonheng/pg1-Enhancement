package util;

import com.rhbgroup.dte.pg1enhancement.model.*;
import java.math.BigDecimal;

public class TestUtility {
  public static final String fiTransactionId = null;
  public static final String sourceAccountId = "sophearum_ban1@oski";
  public static final String destinationAccountId = "oskikhppxxx@oski";
  public static final String sourceName = "Sophearum Ban";
  public static final String destinationName = "RHB Bank (Camboia) Plc.";
  public static final String sourceBankParticipantCode = "OSKIKHPPXXX";
  public static final String sourceBankName = "RHB Bank (Camboia) Plc.";
  public static final String destinationBankParticipantCode = "OSKIKHPPXXX";
  public static final String destinationBankName = "RHB Bank (Camboia) Plc.";
  public static final BigDecimal amount = BigDecimal.valueOf(15000.0);
  public static final String currencyName = "KHR";
  public static final Long transactionCreatedTime = 1628642552857L;
  public static final String sourcePhoneNumber = "85577906996";
  public static final String destinationPhoneNumber = null;
  public static final String sourceWalletType = "MOBILE";
  public static final String destinationWalletType = "BANK";
  public static final String status = "SUCCESS";
  public static final String transactionHash =
      "3cb72af126500b022f9c82f80f8b6b3e70e51868f1d44e92192dec757b1b97b3";
  public static final String assetId = "khr#nbc";
  public static final String description = "C20197";
  public static final String errorMessage = null;
  public static final BigDecimal fastTransactionId = BigDecimal.valueOf(122501);
  public static final String receiverBankAccount = "2000001000008972";
  public static final String fstTrxStatus = "RECEIVED_AT_FI";
  public static final String sourceAddress = "";
  public static final String destinationAddress = null;
  public static final String qrCode = null;
  public static final BigDecimal fee = BigDecimal.ZERO;
  public static final BigDecimal tax = BigDecimal.ZERO;

  public static TransactionHistoryResponse getTestTransactionHistoryResponse() {

    TransactionHistoryResponse transactionHistoryResponse = new TransactionHistoryResponse();
    transactionHistoryResponse.setSourceAccountId(sourceAccountId);
    transactionHistoryResponse.setDestinationAccountId(destinationAccountId);
    transactionHistoryResponse.setSourceBankParticipantCode(sourceBankParticipantCode);
    transactionHistoryResponse.setSourceBankName(sourceBankName);
    transactionHistoryResponse.setDestinationBankParticipantCode(destinationBankParticipantCode);
    transactionHistoryResponse.setDestinationBankName(destinationBankName);
    transactionHistoryResponse.setTransactionCreatedTime(
        BigDecimal.valueOf(transactionCreatedTime));
    transactionHistoryResponse.setSourceWalletType(sourceWalletType);
    transactionHistoryResponse.setDestinationWalletType(destinationWalletType);
    transactionHistoryResponse.setStatus(status);
    transactionHistoryResponse.setTransactionHash(transactionHash);
    transactionHistoryResponse.setAssetId(assetId);
    transactionHistoryResponse.setDescription(description);
    transactionHistoryResponse.setErrorMessage(errorMessage);
    transactionHistoryResponse.setReceiverBankAccount(receiverBankAccount);
    transactionHistoryResponse.setFstTrxStatus(fstTrxStatus);
    transactionHistoryResponse.setQrCode(qrCode);
    transactionHistoryResponse.setAmount(amount);
    transactionHistoryResponse.setFastTransactionId(fastTransactionId);
    transactionHistoryResponse.setSourceName(sourceName);
    transactionHistoryResponse.setDestinationName(destinationName);
    transactionHistoryResponse.setCurrencyName(currencyName);
    transactionHistoryResponse.setSourcePhoneNumber(sourcePhoneNumber);
    transactionHistoryResponse.setDestinationPhoneNumber(destinationPhoneNumber);
    transactionHistoryResponse.setSourceAddress(sourceAddress);
    transactionHistoryResponse.setDestinationAddress(destinationAddress);
    transactionHistoryResponse.setFee(fee);
    transactionHistoryResponse.setTax(tax);

    return transactionHistoryResponse;
  }

  public static CIFTPWebHistoryResponse getCIFTPWebHistoryResponse() {
    CIFTPWebHistoryResponse response = new CIFTPWebHistoryResponse();
    response.add(
        new CIFTPWebHistory()
            .sourceAccountId("oskikhppxxx@oski")
            .destinationAccountId("uatwilliam_dimaano@oski")
            .sourceName("RHB Bank (Camboia) Plc.")
            .destinationName("Uat william Dimaano")
            .sourceBankName("RHB Bank (Camboia) Plc.")
            .destinationBankName("RHB Bank (Camboia) Plc.")
            .amount(10500.00)
            .currencyName(Currency.USD)
            .transactionHash("9579edab077b0c507529e069edba478899d71d779b2228e2745eefaff90b804d")
            .status("SUCCESS"));
    return response;
  }

  public static CIFTPWebHistoryResponse getCIFTPWebHistoryResponseWithManyResources() {
    CIFTPWebHistoryResponse response = new CIFTPWebHistoryResponse();
    response.add(
        new CIFTPWebHistory()
            .sourceAccountId("oskikhppxxx@oski")
            .destinationAccountId("uatwilliam_dimaano@oski")
            .sourceName("RHB Bank (Camboia) Plc.")
            .destinationName("Uat william Dimaano")
            .sourceBankName("RHB Bank (Camboia) Plc.")
            .destinationBankName("RHB Bank (Camboia) Plc.")
            .amount(10500.00)
            .currencyName(Currency.USD)
            .transactionHash("9579edab077b0c507529e069edba478899d71d779b2228e2745eefaff90b804d")
            .status("SUCCESS"));
    response.add(
        new CIFTPWebHistory()
            .sourceAccountId("oskikhppxxx@oski")
            .destinationAccountId("uatwilliam_dimaano@oski")
            .sourceName("RHB Bank (Camboia) Plc.")
            .destinationName("Uat william Dimaano")
            .sourceBankName("RHB Bank (Camboia) Plc.")
            .destinationBankName("RHB Bank (Camboia) Plc.")
            .amount(10500.00)
            .currencyName(Currency.KHR)
            .transactionHash("ad23edab077b0c507529e069edba478899d71d779b2228e2745eefaff90b804d")
            .status("SUCCESS"));
    return response;
  }

  public static CIFTPTransactionInquiryRequest getCIFTPTransactionInquiryRequest() {
    return new CIFTPTransactionInquiryRequest()
        .amount(10500.00)
        .currency(Currency.USD)
        .transactionHash("9579edab077b0c507529e069edba478899d71d779b2228e2745eefaff90b804d");
  }

  public static CIFTPTransactionInquiryResponse getCIFTPTransactionInquiryResponse() {
    return new CIFTPTransactionInquiryResponse()
        .sourceAccountId("oskikhppxxx@oski")
        .destinationAccountId("uatwilliam_dimaano@oski")
        .sourceName("RHB Bank (Camboia) Plc.")
        .destinationName("Uat william Dimaano")
        .sourceBankName("RHB Bank (Camboia) Plc.")
        .destinationBankName("RHB Bank (Camboia) Plc.")
        .amount(10500.00)
        .currency(Currency.USD)
        .transactionHash("9579edab077b0c507529e069edba478899d71d779b2228e2745eefaff90b804d")
        .status("SUCCESS");
  }
}
