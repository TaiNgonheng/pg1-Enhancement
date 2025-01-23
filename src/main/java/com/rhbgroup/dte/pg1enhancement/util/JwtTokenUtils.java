package com.rhbgroup.dte.pg1enhancement.util;

import static com.rhbgroup.dte.pg1enhancement.common.CommonConstants.CIFTP_AUTHENTICATE_URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.core.HazelcastInstance;
import com.rhbgroup.dte.pg1enhancement.config.ApplicationProperties;
import com.rhbgroup.dte.pg1enhancement.model.CIFTPAuthRequest;
import com.rhbgroup.dte.pg1enhancement.model.CIFTPAuthResponse;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.ConcurrentMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenUtils {

  private final HazelcastInstance hazelcastInstance;
  private final RestTemplate restTemplate;
  private final ApplicationProperties applicationProperties;
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private static final String CIFTP_TOKEN_CACHE_KEY = "CIFTP-TOKEN-CACHE-KEY";
  private static final String CIFTP_CACHE_MAP = "CIFTP-CACHE-MAP";
  private static final String CLAIMS_EXPIRY_DATE = "exp";

  private static final int SUCCESS = 0;

  public String getCIFTPToken() {
    String cachedToken = retrieveMap().get(CIFTP_TOKEN_CACHE_KEY);
    if (cachedToken != null && !isTokenExpired(cachedToken)) {
      return cachedToken;
    } else {
      String newToken = fetchTokenFromCIFTPServer();
      retrieveMap().put(CIFTP_TOKEN_CACHE_KEY, newToken);
      return newToken;
    }
  }

  boolean isTokenExpired(String token) {
    try {
      String[] parts = token.split("\\.");

      String payload = new String(Base64.getDecoder().decode(parts[1]));
      long exp = OBJECT_MAPPER.readTree(payload).get(CLAIMS_EXPIRY_DATE).longValue();
      return new Date(exp * 1000).before(new Date());
    } catch (Exception e) {
      log.error("Error parsing token for expiration check", e);
      return true;
    }
  }

  private String fetchTokenFromCIFTPServer() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    CIFTPAuthRequest ciftpAuthRequest =
        new CIFTPAuthRequest()
            .username(applicationProperties.getCiftpUsername())
            .password(applicationProperties.getCiftpPassword());
    HttpEntity<CIFTPAuthRequest> entity = new HttpEntity<>(ciftpAuthRequest, headers);

    try {
      CIFTPAuthResponse response =
          restTemplate.postForObject(
              applicationProperties.getCiftpUrl().concat(CIFTP_AUTHENTICATE_URL),
              entity,
              CIFTPAuthResponse.class);

      if (response == null
          || response.getStatus() == null
          || SUCCESS != response.getStatus().getCode()) {
        throw new ResponseStatusException(
            HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch token from CIFTP server");
      }
      return response.getData().getIdToken();
    } catch (RestClientException e) {
      log.error("Error fetching token from CIFTP server", e);
      throw new ResponseStatusException(
          HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching token from CIFTP server", e);
    }
  }

  private ConcurrentMap<String, String> retrieveMap() {
    return hazelcastInstance.getMap(CIFTP_CACHE_MAP);
  }
}
