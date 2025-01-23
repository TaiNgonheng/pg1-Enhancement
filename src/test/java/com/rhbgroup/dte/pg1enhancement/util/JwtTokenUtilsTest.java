package com.rhbgroup.dte.pg1enhancement.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.rhbgroup.dte.pg1enhancement.config.ApplicationProperties;
import com.rhbgroup.dte.pg1enhancement.model.CIFTPAuthResponse;
import com.rhbgroup.dte.pg1enhancement.model.CIFTPAuthResponseData;
import com.rhbgroup.dte.pg1enhancement.model.CIFTPAuthResponseStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

class JwtTokenUtilsTest {

  @Mock private HazelcastInstance hazelcastInstance;

  @Mock private RestTemplate restTemplate;

  @Mock private ApplicationProperties applicationProperties;

  @Mock private IMap concurrentMap;

  @InjectMocks private JwtTokenUtils jwtTokenUtils;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    when(hazelcastInstance.getMap(anyString())).thenReturn(concurrentMap);
  }

  @Test
  void whenCachedTokenNotExpired_thenShouldReturnCachedToken() {
    String cachedToken =
        "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlVTRVIiLCJBRE1JTiJdLCJzdWIiOiJzcGdfYWRtaW4iLCJpYXQiOjE3MDg5MTIyNzMsImV4cCI6OTAwMDAwMDAwMH0.bY_r1WTOKQ928THDHnXytEOAvAp_ZsEbdSEOfBYVL76f2GSBSUekbGJ2oTRHKhs8X7ELt8uaN5qlDKWcNfPwww";
    // Mock behavior
    when(concurrentMap.get(anyString())).thenReturn(cachedToken);

    // Action
    String token = jwtTokenUtils.getCIFTPToken();

    // Assertion
    assertEquals(cachedToken, token);
    verify(concurrentMap, times(1)).get(anyString());
    verifyNoInteractions(restTemplate);
  }

  @Test
  void whenTokenIsExpired_shouldFetchNewToken() {
    String expiredToken =
        "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbIlVTRVIiLCJBRE1JTiJdLCJzdWIiOiJzcGdfYWRtaW4iLCJpYXQiOjE3MDg5MTIyNzMsImV4cCI6MTcwMDAwMDAwMH0.sL80MjJ9Jih6FqBZXXWJhqnnFQx5kXlfqHMKn3_uSysv0c9r9P2tUPzXgIjlnHs5jVcivZQHGBRw5TTndT4Xpw";
    when(concurrentMap.get(anyString())).thenReturn(expiredToken);

    // Mock response from server
    CIFTPAuthResponse mockResponse = new CIFTPAuthResponse();
    CIFTPAuthResponseData ciftpAuthResponseData = new CIFTPAuthResponseData();
    ciftpAuthResponseData.setIdToken("newToken");
    mockResponse.setData(ciftpAuthResponseData);
    CIFTPAuthResponseStatus ciftpAuthResponseStatus = new CIFTPAuthResponseStatus();
    ciftpAuthResponseStatus.setCode(0);
    mockResponse.setStatus(ciftpAuthResponseStatus);
    when(restTemplate.postForObject(anyString(), any(), eq(CIFTPAuthResponse.class)))
        .thenReturn(mockResponse);

    // Mock application properties
    when(applicationProperties.getCiftpUrl()).thenReturn("http://fake-url");
    when(applicationProperties.getCiftpUsername()).thenReturn("user");
    when(applicationProperties.getCiftpPassword()).thenReturn("pass");

    // Action
    String token = jwtTokenUtils.getCIFTPToken();

    // Assertion
    assertEquals("newToken", token);
    verify(concurrentMap, times(1)).put(anyString(), eq("newToken")); // Ensure new token is cached
  }

  @Test
  void whenServerThrowsException_shouldThrowResponseStatusException() {
    // Mock application properties
    when(applicationProperties.getCiftpUrl()).thenReturn("http://fake-url");
    when(applicationProperties.getCiftpUsername()).thenReturn("user");
    when(applicationProperties.getCiftpPassword()).thenReturn("pass");
    // Mock token expiration
    when(concurrentMap.get(anyString())).thenReturn(null);
    when(restTemplate.postForObject(anyString(), any(), eq(CIFTPAuthResponse.class)))
        .thenThrow(new RestClientException("Failed"));

    // Assert that an exception is thrown
    assertThrows(ResponseStatusException.class, () -> jwtTokenUtils.getCIFTPToken());
  }
}
