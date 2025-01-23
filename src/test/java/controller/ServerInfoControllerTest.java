package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.rhbgroup.dte.pg1enhancement.config.ApplicationProperties;
import com.rhbgroup.dte.pg1enhancement.controller.ServerInfoController;
import com.rhbgroup.dte.pg1enhancement.model.ServerInfoResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServerInfoControllerTest {
  private ServerInfoController serverInfoController;
  @Mock private ApplicationProperties applicationProperties;

  @BeforeAll
  void setUp() {
    serverInfoController = new ServerInfoController(applicationProperties);
  }

  @Test
  void testSuccessfullyGetServerInfo() {
    when(applicationProperties.getServerVersion()).thenReturn("version");
    when(applicationProperties.getServerDesciption()).thenReturn("desc");
    when(applicationProperties.getServerLastUpdate()).thenReturn("last_update");
    when(applicationProperties.getServerChangesHistory()).thenReturn("changes\nhistory");
    ResponseEntity<ServerInfoResponse> response = serverInfoController.getServerInfo();
    assertNotNull(response);
    assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    assertNotNull(response.getBody());
    assertNotNull(response.getBody().getVersion().getName());
  }
}
