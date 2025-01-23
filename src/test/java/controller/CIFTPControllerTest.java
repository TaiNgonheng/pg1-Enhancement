package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.rhbgroup.dte.pg1enhancement.controller.CIFTPController;
import com.rhbgroup.dte.pg1enhancement.model.CIFTPTransactionInquiryResponse;
import com.rhbgroup.dte.pg1enhancement.service.ifc.CIFTPServiceIfc;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import util.TestUtility;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CIFTPControllerTest {

  @Mock private CIFTPServiceIfc ciftpServiceIfc;
  @InjectMocks private CIFTPController ciftpController;

  @Test
  void testCIFTPTransactionInquirySuccessfully() {
    when(ciftpServiceIfc.inquireCIFTPTransaction(TestUtility.getCIFTPTransactionInquiryRequest()))
        .thenReturn(TestUtility.getCIFTPTransactionInquiryResponse());
    ResponseEntity<CIFTPTransactionInquiryResponse> response =
        ciftpController.inquireCIFTPTransaction(TestUtility.getCIFTPTransactionInquiryRequest());
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(
        "9579edab077b0c507529e069edba478899d71d779b2228e2745eefaff90b804d",
        Objects.requireNonNull(response.getBody()).getTransactionHash());
  }
}
