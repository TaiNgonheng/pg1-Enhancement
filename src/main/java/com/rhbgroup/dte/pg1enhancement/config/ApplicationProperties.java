package com.rhbgroup.dte.pg1enhancement.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class ApplicationProperties {
  @Value("${config.server_info.version}")
  private String serverVersion;

  @Value("${config.server_info.last_update}")
  private String serverLastUpdate;

  @Value("${config.server_info.server_environment}")
  private String serverEnvironment;

  @Value("${config.server_info.description}")
  private String serverDesciption;

  @Value("${config.server_info.changes_history}")
  private String serverChangesHistory;

  @Value("${config.get_incoming_bakong_khqr.page_size}")
  private int getIncomingBakongKHQRPageSize;

  @Value("${config.ciftp.username}")
  private String ciftpUsername;

  @Value("${config.ciftp.password}")
  private String ciftpPassword;

  @Value("${config.ciftp.url}")
  private String ciftpUrl;

  @Value("${config.ciftp.largeValueUSD}")
  private Double ciftpLargeValueUSD;

  @Value("${config.ciftp.largeValueKHR}")
  private Double ciftpLargeValueKHR;
}
