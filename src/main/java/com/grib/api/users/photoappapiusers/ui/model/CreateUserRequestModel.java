package com.grib.api.users.photoappapiusers.ui.model;

import lombok.Data;

@Data
public class CreateUserRequestModel {
    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
