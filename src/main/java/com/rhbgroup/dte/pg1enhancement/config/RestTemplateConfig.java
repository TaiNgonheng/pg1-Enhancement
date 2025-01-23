package com.rhbgroup.dte.pg1enhancement.config;

import com.rhbgroup.dte.pg1enhancement.common.CIFTPHttpHeaderBearerTokenInjector;
import com.rhbgroup.dte.pg1enhancement.util.JwtTokenUtils;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

  @Bean
  public CIFTPHttpHeaderBearerTokenInjector ciftpHttpHeaderBearerTokenInjector(
      ApplicationProperties applicationProperties, @Lazy JwtTokenUtils jwtTokenUtils) {
    return new CIFTPHttpHeaderBearerTokenInjector(applicationProperties, jwtTokenUtils);
  }

  @Bean
  public RestTemplate restTemplate(
      CIFTPHttpHeaderBearerTokenInjector ciftpHttpHeaderBearerTokenInjector) {
    try {
      SSLContext sslContext =
          SSLContexts.custom().loadTrustMaterial(null, (chain, authType) -> true).build();
      SSLConnectionSocketFactory csf =
          new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

      CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

      HttpComponentsClientHttpRequestFactory requestFactory =
          new HttpComponentsClientHttpRequestFactory(httpClient);

      RestTemplate restTemplate = new RestTemplate(requestFactory);
      restTemplate.getInterceptors().add(ciftpHttpHeaderBearerTokenInjector);
      return restTemplate;
    } catch (Exception e) {
      throw new RuntimeException("Failed to create RestTemplate", e);
    }
  }
}
