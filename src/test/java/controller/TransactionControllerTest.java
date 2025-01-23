package controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static util.TestUtility.getTestTransactionHistoryResponse;

import com.rhbgroup.dte.pg1enhancement.controller.TransactionController;
import com.rhbgroup.dte.pg1enhancement.exception.RequiredParamMismatchException;
import com.rhbgroup.dte.pg1enhancement.model.TransactionHistoryResponse;
import com.rhbgroup.dte.pg1enhancement.service.impl.FFITransactionServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionControllerTest {
  private FFITransactionServiceImpl transactionService;
  private TransactionController transactionController;

  @BeforeAll
  void setUp() {
    transactionService = Mockito.mock(FFITransactionServiceImpl.class);
    transactionController = new TransactionController(transactionService);
  }

  @Test
  void testSuccessfullyGetTransactionHistoryWithPaymentInfo() {
    when(transactionService.getTransactionHistoryByPaymentInfo(
            "C002470002", "OSKIKHPPXXX", "ACLBKHPPXXX"))
        .thenReturn(getTestTransactionHistoryResponse());
    ResponseEntity<TransactionHistoryResponse> response =
        transactionController.getTransactionHistory(
            "OSKIKHPPXXX", "ACLBKHPPXXX", "C002470002", null);
    assertNotNull(response);
    assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    TransactionHistoryResponse transactionHistoryResponse = response.getBody();
    assertEquals(getTestTransactionHistoryResponse(), transactionHistoryResponse);
  }

  @Test
  void testThrowExceptionOnGetTransactionHistoryWithPaymentInfo() {
    RequiredParamMismatchException response =
        assertThrows(
            RequiredParamMismatchException.class,
            () ->
                transactionController.getTransactionHistory(
                    "C002470002", "ACLBKHPPXXX", null, null));

    assertEquals(
        "Expecting (sender AND receiver AND payment_ref) OR transactionHash parameter",
        response.getMessage());
  }

  @Test
  void testSuccessfullyGetTransactionHistoryWithTransactionHash() {
    when(transactionService.getTransactionHistoryByTransactionHash("trxHash"))
        .thenReturn(getTestTransactionHistoryResponse());
    ResponseEntity<TransactionHistoryResponse> response =
        transactionController.getTransactionHistory(null, null, null, "trxHash");
    assertNotNull(response);
    assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    TransactionHistoryResponse transactionHistoryResponse = response.getBody();
    assertEquals(getTestTransactionHistoryResponse(), transactionHistoryResponse);
  }
}
