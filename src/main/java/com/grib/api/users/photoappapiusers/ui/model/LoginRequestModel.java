package com.grib.api.users.photoappapiusers.ui.model;

import lombok.Data;

@Data
public class LoginRequestModel {
    private String email;
    private String password;
}
