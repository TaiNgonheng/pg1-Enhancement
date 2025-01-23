package com.rhbgroup.dte.pg1enhancement.client;

import com.rhbgroup.dte.pg1enhancement.config.ApplicationProperties;
import com.rhbgroup.dte.pg1enhancement.model.CIFTPChannel;
import com.rhbgroup.dte.pg1enhancement.model.CIFTPWebHistoryResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@AllArgsConstructor
@Slf4j
public class CIFTPClient {

  private final RestTemplate restTemplate;
  private final ApplicationProperties properties;

  private static final String CHANNEL = "channel";
  private static final String FILTER = "filter";

  public CIFTPWebHistoryResponse getCIFTPWebHistoryResponse(
      CIFTPChannel channel, String transactionHash) {
    try {
      UriComponentsBuilder builder =
          UriComponentsBuilder.fromHttpUrl(
                  properties.getCiftpUrl()
                      + "/ciftp-payment-service/tps/api/transfer-management/fast-transactions/web-history")
              .queryParam(CHANNEL, channel)
              .queryParam(FILTER, transactionHash);
      ResponseEntity<CIFTPWebHistoryResponse> response =
          restTemplate.exchange(
              builder.toUriString(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
      return response.getBody();
    } catch (RestClientException exception) {
      log.error(
          "Something went wrong while trying to get web history history with hash: {}",
          transactionHash,
          exception);
      throw new ResponseStatusException(
          HttpStatus.INTERNAL_SERVER_ERROR,
          "Something when wrong while trying to inquire transaction from CIFTP",
          exception);
    }
  }
}
