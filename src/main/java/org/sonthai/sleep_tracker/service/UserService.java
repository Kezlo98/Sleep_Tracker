package org.sonthai.sleep_tracker.service;

import org.sonthai.sleep_tracker.model.dto.UserDto;
import org.sonthai.sleep_tracker.model.dto.UserRegisterRequestDto;
import java.util.List;

public interface UserService {
    UserDto register(UserRegisterRequestDto requestDto);

    List<UserDto> getAllUserDetail();

    UserDto getUserDetail();

  void deleteUser (Long id);

  UserDto updateUser (UserDto userDto, Long id);
}
