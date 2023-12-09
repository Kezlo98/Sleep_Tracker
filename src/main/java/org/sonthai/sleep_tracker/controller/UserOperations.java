package org.sonthai.sleep_tracker.controller;

import org.sonthai.sleep_tracker.model.dto.UserDto;
import org.sonthai.sleep_tracker.model.dto.UserRegisterRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface UserOperations {

  @Operation
  @SecurityRequirements()
  UserDto register(UserRegisterRequestDto requestDto);

  UserDto getUserDetail();

  List<UserDto> getAllUser();

  void deleteUser(@PathVariable Long id);

  UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto);
}
