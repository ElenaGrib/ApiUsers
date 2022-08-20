package com.grib.api.users.photoappapiusers.dto;

import com.grib.api.users.photoappapiusers.ui.model.AlbumResponseModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = -2615337875202740401L;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private List<AlbumResponseModel> albums;
}
