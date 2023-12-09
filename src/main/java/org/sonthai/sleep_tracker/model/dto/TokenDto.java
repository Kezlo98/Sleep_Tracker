package org.sonthai.sleep_tracker.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(SnakeCaseStrategy.class)
public class TokenDto {
  public Long expiresIn;
  public String scope;
  public String tokenType;
  public String accessToken;
}

