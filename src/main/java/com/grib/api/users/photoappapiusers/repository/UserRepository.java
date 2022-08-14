package com.grib.api.users.photoappapiusers.repository;

import com.grib.api.users.photoappapiusers.data.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
