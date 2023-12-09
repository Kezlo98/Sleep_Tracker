package org.sonthai.sleep_tracker;

import org.sonthai.sleep_tracker.config.OAuth2Properties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {OAuth2Properties.class})
public class SleepTrackerApplication {

  public static void main (String[] args) {
    SpringApplication.run(SleepTrackerApplication.class, args);
  }

}
