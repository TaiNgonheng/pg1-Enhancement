package com.rhbgroup.dte.pg1enhancement.controller;

import com.rhbgroup.dte.pg1enhancement.api.ServerInfoApiDelegate;
import com.rhbgroup.dte.pg1enhancement.config.ApplicationProperties;
import com.rhbgroup.dte.pg1enhancement.model.ServerInfoResponse;
import com.rhbgroup.dte.pg1enhancement.model.ServerInfoResponseVersion;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ServerInfoController implements ServerInfoApiDelegate {

  private final ApplicationProperties applicationProperties;

  @Override
  public ResponseEntity<ServerInfoResponse> getServerInfo() {
    log.info("API: getServerInfo");
    ServerInfoResponse serverInfoResponse = new ServerInfoResponse();
    ServerInfoResponseVersion serverInfoResponseVersion = new ServerInfoResponseVersion();
    serverInfoResponseVersion.setName(applicationProperties.getServerVersion());
    serverInfoResponseVersion.setDate(applicationProperties.getServerLastUpdate());
    serverInfoResponseVersion.setServerEnvironment(applicationProperties.getServerEnvironment());
    serverInfoResponseVersion.setDescription(applicationProperties.getServerDesciption());

    List<String> changesHistory =
        Arrays.stream(applicationProperties.getServerChangesHistory().split("\n"))
            .map(String::trim)
            .collect(Collectors.toList());

    serverInfoResponseVersion.setChangesHistory(changesHistory);
    serverInfoResponse.setVersion(serverInfoResponseVersion);
    return ResponseEntity.ok(serverInfoResponse);
  }
}
