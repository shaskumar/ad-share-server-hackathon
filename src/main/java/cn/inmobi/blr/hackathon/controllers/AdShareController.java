package cn.inmobi.blr.hackathon.controllers;

import cn.inmobi.blr.hackathon.dto.bundle.GetShareRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdShareController {

    @PostMapping(path = "/ad/share", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public int getShareCount(@RequestBody GetShareRequest shareRequest){
        //logic
        return 0;
    }
}
