package com.grib.api.users.photoappapiusers.service;

import com.grib.api.users.photoappapiusers.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);

    UserDto getUserDetailsByEmail(String email);
}
