package com.rhbgroup.dte.pg1enhancement.common;

import static com.rhbgroup.dte.pg1enhancement.common.CommonConstants.CIFTP_AUTHENTICATE_URL;

import com.rhbgroup.dte.pg1enhancement.config.ApplicationProperties;
import com.rhbgroup.dte.pg1enhancement.util.JwtTokenUtils;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

@RequiredArgsConstructor
@Slf4j
public class CIFTPHttpHeaderBearerTokenInjector implements ClientHttpRequestInterceptor {
  private final ApplicationProperties applicationProperties;
  private final JwtTokenUtils jwtTokenUtils;

  @Override
  public ClientHttpResponse intercept(
      HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
    final String targetHost = request.getURI().getHost();
    final String targetPath = request.getURI().getPath();

    if (shouldInjectToken(targetHost, targetPath)) {
      String token = jwtTokenUtils.getCIFTPToken();
      request.getHeaders().add("Authorization", "Bearer " + token);
      log.debug("Injected Bearer token for request to: {}", request.getURI());
    }

    return execution.execute(request, body);
  }

  private boolean shouldInjectToken(String host, String path) {
    return applicationProperties.getCiftpUrl().contains(host)
        && !CIFTP_AUTHENTICATE_URL.contains(path);
  }
}
