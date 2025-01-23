package com.rhbgroup.dte.pg1enhancement.scheduler;

import static com.rhbgroup.dte.pg1enhancement.constant.Constants.*;

import com.rhbgroup.dte.pg1enhancement.service.ifc.BakongKHQRServiceIfc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BakongKHQRValidationTask {
  private static final Logger log = LoggerFactory.getLogger(BakongKHQRValidationTask.class);
  @Autowired private BakongKHQRServiceIfc bakongKHQRService;

  @Scheduled(cron = EVERY_6_HOUR_FREQUENCY)
  public void reportCurrentTime() {
    log.info("Validating BakongHRQR records");
    bakongKHQRService.validateBakongKHQR();
  }
}
