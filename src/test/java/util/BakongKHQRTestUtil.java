package util;

import com.rhbgroup.dte.pg1enhancement.model.BakongKHQR;

public class BakongKHQRTestUtil {

  public static BakongKHQR getBakongKHQR() {
    return new BakongKHQR()
        .id("12")
        .trxHash("72d7e933fa59724fdd7fabc054f44bd78f89988a4032fa107cabfd1d0bcff486")
        .amount("10000.00")
        .assetId("khr#nbc")
        .accountNumber("2000001000106286")
        .accountName("JD Specialty Shop")
        .billNumber("123123")
        .srcAccountId("williamsit_rhb@oski")
        .srcAccountType("MOBILE")
        .srcPartCode("OSKIKHPPXXX")
        .dstPartCode("OSKIKHPPXXX")
        .dstAccountType("Bank")
        .senderAccount("sophearum_ban1@oski")
        .senderName("Sophearum Ban")
        .createTime("2022-04-18T21:59:27.545122Z");
  }
}
