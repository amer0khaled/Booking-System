package com.amerkhaled.bookingsystem.mappers;

import com.amerkhaled.bookingsystem.domain.dto.UserRequestDto;
import com.amerkhaled.bookingsystem.domain.dto.UserResponseDto;
import com.amerkhaled.bookingsystem.domain.dto.UserUpdateDto;
import com.amerkhaled.bookingsystem.domain.entities.User;

public interface UserMapper {

    User fromRequestDto(UserRequestDto userRequestDto);
    User fromUpdateDto(UserUpdateDto userUpdateDto);
    UserResponseDto toResponseDto(User user);

}
