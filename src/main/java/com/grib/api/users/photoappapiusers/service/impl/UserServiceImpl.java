package com.grib.api.users.photoappapiusers.service.impl;

import com.grib.api.users.photoappapiusers.data.UserEntity;
import com.grib.api.users.photoappapiusers.dto.UserDto;
import com.grib.api.users.photoappapiusers.service.UserService;
import org.modelmapper.ModelMapper;

import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        return null;
    }
}
