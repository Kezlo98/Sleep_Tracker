package org.sonthai.sleep_tracker.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.sonthai.sleep_tracker.constant.RegistrationEnum;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDto {

  private Long id;

  @NotNull(message = "username cannot be null")
  @NotBlank(message = "username cannot be blank")
  private String username;

  private String password;
  
  private String email;

  private RegistrationEnum registrationId = RegistrationEnum.BASIC;

  private Set<String> roles = Set.of("ROLE_USER");
}
