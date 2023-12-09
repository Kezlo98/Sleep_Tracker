package org.sonthai.sleep_tracker.service.impl;

import org.sonthai.sleep_tracker.constant.RegistrationEnum;
import org.sonthai.sleep_tracker.entity.User;
import org.sonthai.sleep_tracker.exception.DomainCode;
import org.sonthai.sleep_tracker.exception.DomainException;
import org.sonthai.sleep_tracker.mapper.UserMapper;
import org.sonthai.sleep_tracker.model.dto.UserDto;
import org.sonthai.sleep_tracker.model.dto.UserRegisterRequestDto;
import org.sonthai.sleep_tracker.repository.UserRepository;
import org.sonthai.sleep_tracker.service.UserService;
import org.sonthai.sleep_tracker.util.UserUtils;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public UserDto register(UserRegisterRequestDto requestDto){
        Optional<User> userOpt = userRepository.findUserByUsernameAndRegistrationId(requestDto.getUsername(), RegistrationEnum.BASIC);
        if(userOpt.isPresent()){
            throw new DomainException(DomainCode.USERNAME_IS_EXISTED,new Object[]{requestDto.getUsername()});
        }
        try{
            User user = userMapper.toEntity(requestDto);
            user.setRegistrationId(RegistrationEnum.BASIC);
            user = userRepository.save(user);

            return userMapper.toDto(user);
        } catch (Exception ex){
            throw new DomainException(DomainCode.GENERAL_ERROR);
        }
    }

    @Override
    public List<UserDto> getAllUserDetail() {
        List<User> user = userRepository.findAll();
        return userMapper.toDto(user);
    }

    @Override
    public UserDto getUserDetail(){
        UserDto userDto = UserUtils.getUserDtoFromAuthentication(SecurityContextHolder.getContext().getAuthentication());
        Optional <User> userOpt = userRepository.findUserByUsernameAndRegistrationId(userDto.getUsername(), userDto.getRegistrationId());
        if(userOpt.isEmpty()){
            throw new DomainException(DomainCode.USERNAME_IS_NOT_EXISTED,new Object[]{userDto.getUsername(), userDto.getRegistrationId()});
        }

        return userMapper.toDto(userOpt.get());
    }

    @Override
    public void deleteUser (Long id){
        userRepository.findById(id).orElseThrow(() -> new DomainException(DomainCode.USER_IS_NOT_EXISTED,new Object[]{id}));
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser (UserDto userDto, Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new DomainException(DomainCode.USER_IS_NOT_EXISTED,new Object[]{id}));
        userMapper.update(user, userDto);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

}
