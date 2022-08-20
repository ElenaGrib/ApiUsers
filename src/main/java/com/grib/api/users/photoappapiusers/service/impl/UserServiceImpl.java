package com.grib.api.users.photoappapiusers.service.impl;

import com.grib.api.users.photoappapiusers.data.UserEntity;
import com.grib.api.users.photoappapiusers.dto.UserDto;
import com.grib.api.users.photoappapiusers.repository.UserRepository;
import com.grib.api.users.photoappapiusers.service.UserService;
import com.grib.api.users.photoappapiusers.ui.model.AlbumResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    RestTemplate restTemplate;
    UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    Environment environment;

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

        userRepository.save(userEntity);

        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);

        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);

        if (userEntity == null) throw new UsernameNotFoundException(username);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),
                true, true, true, true, new ArrayList<>());
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) throw new UsernameNotFoundException(email);

        return new ModelMapper().map(userEntity, UserDto.class);
    }

    public UserDto getUserByUserId(String userId) {
        //String albumsUrl = String.format(environment.getProperty("albums.url"), userId);
        String albumsUrl = String.format("http://ALBUMS-WS/users/%s/albums", userId);

        UserEntity userEntity = userRepository.findById(userId);
        if (userEntity == null) throw new UsernameNotFoundException("User not found");

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);

        ResponseEntity<List<AlbumResponseModel>> albumsListResponse = restTemplate.exchange(albumsUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<AlbumResponseModel>>() {
                });
        List<AlbumResponseModel> albumsList = albumsListResponse.getBody();

        userDto.setAlbums(albumsList);

        return new ModelMapper().map(userEntity, UserDto.class);
    }
}
