package com.grib.api.users.photoappapiusers.data;

import com.grib.api.users.photoappapiusers.ui.model.AlbumResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "albums-ws")

public interface AlbumsServiceClient {

    @GetMapping("/users/{id}/albums")
    List<AlbumResponseModel> getAlbums(@PathVariable String id);

}
