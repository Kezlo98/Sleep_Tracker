package org.sonthai.sleep_tracker.model.dto;

import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRegisterRequestDto {
  @NotNull(message = "username cannot be null")
  @NotBlank(message = "username cannot be blank")
  private String username;
  @NotNull(message = "password cannot be null")
  @NotBlank(message = "password cannot be blank")
  private String password;
  @Email
  private String email;
  private Set<String> roles = Set.of("ROLE_USER");
}
