package controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.rhbgroup.dte.pg1enhancement.config.ApplicationProperties;
import com.rhbgroup.dte.pg1enhancement.controller.BakongKHQRController;
import com.rhbgroup.dte.pg1enhancement.exception.RequiredParamMismatchException;
import com.rhbgroup.dte.pg1enhancement.model.BakongKHQR;
import com.rhbgroup.dte.pg1enhancement.model.BakongKHQRStatusUpdateResponse;
import com.rhbgroup.dte.pg1enhancement.model.IncomingTransactionStatusUpdateRequest;
import com.rhbgroup.dte.pg1enhancement.model.PendingBakongKHQRReponse;
import com.rhbgroup.dte.pg1enhancement.service.ifc.BakongKHQRServiceIfc;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import util.BakongKHQRTestUtil;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BakongKHQRControllerTest {
  private BakongKHQRController bakongKHQRController;

  @Mock private BakongKHQRServiceIfc bakongKHQRServiceIfc;
  @Mock private ApplicationProperties applicationProperties;

  @BeforeAll
  void setUp() {
    bakongKHQRController = new BakongKHQRController(bakongKHQRServiceIfc, applicationProperties);
  }

  @Test
  void testSuccessfullyIncomingTransactionStatusUpdate() {
    IncomingTransactionStatusUpdateRequest request = new IncomingTransactionStatusUpdateRequest();
    request.setStatus(IncomingTransactionStatusUpdateRequest.StatusEnum.COMPLETED);
    request.setTransactionHash("#");
    ResponseEntity<BakongKHQRStatusUpdateResponse> response =
        bakongKHQRController.incomingTransactionStatusUpdate(request);
    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(BakongKHQRStatusUpdateResponse.StatusEnum.ACSP, response.getBody().getStatus());
  }

  @Test
  void testThrowRequiredParamMismatchExceptionIncomingTransactionStatusUpdate() {
    IncomingTransactionStatusUpdateRequest request = new IncomingTransactionStatusUpdateRequest();

    try {
      bakongKHQRController.incomingTransactionStatusUpdate(request);
      fail();
    } catch (RequiredParamMismatchException ex) {
      assertTrue(true);
    }
  }

  @Test
  void testGetPendingBakongKHQR() {
    List<BakongKHQR> bakongKHQRList = Arrays.asList(BakongKHQRTestUtil.getBakongKHQR());
    when(bakongKHQRServiceIfc.getIncomingBakongKHQR(0, 20, "PENDING"))
        .thenReturn(
            new PendingBakongKHQRReponse()
                .totalItems(1L)
                .totalPages(1)
                .currentPage(1)
                .bakongKHQR(bakongKHQRList));
    ResponseEntity<PendingBakongKHQRReponse> response =
        bakongKHQRController.getIncomingBakongKHQR(0, 20, "PENDING");
    assertNotNull(response);
    assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    PendingBakongKHQRReponse pendingBakongKHQRReponse = response.getBody();
    assertEquals(1, pendingBakongKHQRReponse.getTotalItems());
    assertEquals(
        "sophearum_ban1@oski", pendingBakongKHQRReponse.getBakongKHQR().get(0).getSenderAccount());
  }
}
