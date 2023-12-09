package org.sonthai.sleep_tracker.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.sonthai.sleep_tracker.entity.User;
import org.sonthai.sleep_tracker.model.dto.UserDto;
import org.sonthai.sleep_tracker.model.dto.UserRegisterRequestDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

  @Autowired
  PasswordEncoder passwordEncoder;

  @Mapping(target = "password", ignore = true)
  public abstract UserDto toDto (User user);

  @Mapping(target = "password", ignore = true)
  public abstract List<UserDto> toDto (List<User> user);

  public abstract User toEntity (UserDto userDto);

  @Mapping(target = "password", qualifiedByName = "encodePassword")
  public abstract User toEntity (UserRegisterRequestDto userRegisterRequestDto);

  @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "password", qualifiedByName = "encodePassword")
  public abstract void update (@MappingTarget User user, UserDto userDto);

  @Named("encodePassword")
  public String encodePassword (String password) {
    return passwordEncoder.encode(password);

  }
}
