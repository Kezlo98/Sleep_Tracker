package org.sonthai.sleep_tracker.controller.impl;

import org.sonthai.sleep_tracker.controller.UserOperations;
import org.sonthai.sleep_tracker.model.dto.UserDto;
import org.sonthai.sleep_tracker.model.dto.UserRegisterRequestDto;
import org.sonthai.sleep_tracker.service.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController implements UserOperations {


    private final UserService userService;

    @Override
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@Valid @RequestBody UserRegisterRequestDto requestDto){
        return userService.register(requestDto);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserDetail(){
        return userService.getUserDetail();
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUser(){
        return userService.getAllUserDetail();
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUser (@PathVariable Long id){
        userService.deleteUser(id);
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser (@PathVariable Long id, @RequestBody UserDto userDto){
        return userService.updateUser(userDto, id);
    }
}
