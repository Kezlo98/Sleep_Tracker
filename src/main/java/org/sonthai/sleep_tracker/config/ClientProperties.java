package org.sonthai.sleep_tracker.config;

import lombok.Data;

@Data
public class ClientProperties {
  private String redirectUri;
  private String scope;
  private String authorizationUri;
  private String tokenUri;
}
