package com.grib.api.users.photoappapiusers.ui.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateUserRequestModel {
    @NotNull(message = "FirstName cannot be missing")
    @Size(min = 2, message = "FirstName must be longer than 2 characters")
    private String firstName;

    @NotNull(message = "LastName cannot be missing")
    @Size(min = 2, message = "LastName must be longer than 2 characters")
    private String lastName;

    @NotNull(message = "Email cannot be missing")
    @Email(message = "Email format is not correct")
    private String email;

    @NotNull(message = "Password cannot be missing")
    @Size(min = 8, max = 16, message = "Password must be from 8 to 16 characters")
    private String password;
}
