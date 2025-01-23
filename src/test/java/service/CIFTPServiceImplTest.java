package service;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowableOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.rhbgroup.dte.pg1enhancement.client.CIFTPClient;
import com.rhbgroup.dte.pg1enhancement.config.ApplicationProperties;
import com.rhbgroup.dte.pg1enhancement.exception.BadRequestException;
import com.rhbgroup.dte.pg1enhancement.mapper.CIFTPMapper;
import com.rhbgroup.dte.pg1enhancement.mapper.CIFTPMapperImpl;
import com.rhbgroup.dte.pg1enhancement.model.CIFTPChannel;
import com.rhbgroup.dte.pg1enhancement.model.CIFTPTransactionInquiryResponse;
import com.rhbgroup.dte.pg1enhancement.model.CIFTPWebHistoryResponse;
import com.rhbgroup.dte.pg1enhancement.service.impl.CIFTPServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import util.TestUtility;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CIFTPServiceImplTest {

  @Mock private ApplicationProperties properties;
  @Mock private CIFTPClient ciftpClient;

  @Spy private CIFTPMapper ciftpMapper = new CIFTPMapperImpl();

  @InjectMocks private CIFTPServiceImpl ciftpService;

  @Test
  void testCIFTPTransactionInquirySuccessfully() {
    when(ciftpClient.getCIFTPWebHistoryResponse(
            CIFTPChannel.RETAIL,
            "9579edab077b0c507529e069edba478899d71d779b2228e2745eefaff90b804d"))
        .thenReturn(TestUtility.getCIFTPWebHistoryResponse());
    when(properties.getCiftpLargeValueUSD()).thenReturn(50000.00);
    CIFTPTransactionInquiryResponse response =
        ciftpService.inquireCIFTPTransaction(TestUtility.getCIFTPTransactionInquiryRequest());
    assertEquals(
        "9579edab077b0c507529e069edba478899d71d779b2228e2745eefaff90b804d",
        response.getTransactionHash());
    assertEquals(10500.00, response.getAmount());
    assertEquals("SUCCESS", response.getStatus());
  }

  @Test
  void testCIFTPTransactionInquiryWithNonFoundResource() {
    when(ciftpClient.getCIFTPWebHistoryResponse(
            CIFTPChannel.RETAIL,
            "9579edab077b0c507529e069edba478899d71d779b2228e2745eefaff90b804d"))
        .thenReturn(new CIFTPWebHistoryResponse());
    when(properties.getCiftpLargeValueUSD()).thenReturn(50000.00);
    BadRequestException exception =
        catchThrowableOfType(
            () ->
                ciftpService.inquireCIFTPTransaction(
                    TestUtility.getCIFTPTransactionInquiryRequest()),
            BadRequestException.class);
    assertEquals("Cannot find unique transaction for the request", exception.getMessage());
    assertEquals(1000, exception.getCode());
  }

  @Test
  void testCIFPTTransactionInquiryWIthManyResources() {
    when(ciftpClient.getCIFTPWebHistoryResponse(
            CIFTPChannel.RETAIL,
            "9579edab077b0c507529e069edba478899d71d779b2228e2745eefaff90b804d"))
        .thenReturn(TestUtility.getCIFTPWebHistoryResponseWithManyResources());
    when(properties.getCiftpLargeValueUSD()).thenReturn(50000.00);
    BadRequestException exception =
        catchThrowableOfType(
            () ->
                ciftpService.inquireCIFTPTransaction(
                    TestUtility.getCIFTPTransactionInquiryRequest()),
            BadRequestException.class);
    assertEquals("Cannot find unique transaction for the request", exception.getMessage());
    assertEquals(1000, exception.getCode());
  }
}
