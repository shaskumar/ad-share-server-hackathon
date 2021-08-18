package cn.inmobi.blr.springmockserver.controllers;

import cn.inmobi.blr.springmockserver.dto.bundle.GetBundleRequest;
import cn.inmobi.blr.springmockserver.dto.bundle.GetBundleResponse;
import cn.inmobi.blr.springmockserver.dto.bundle.GetNotInstalledAppsRequest;
import cn.inmobi.blr.springmockserver.dto.bundle.GetNotInstalledAppsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppOwnerShipController {

    @Value("${bundle.current.list.version}")
    private String currentListVersion;

    @Value("${bundle.current.data}")
    private String currentData;

    @Value("${bundle.current.checksum}")
    private String currentChecksum;

    @PostMapping("/ao/v1/bundle/ids")
    public GetBundleResponse getBundleList(@RequestBody GetBundleRequest bundleRequest){
        if (bundleRequest.getMyListVersion().equals(currentListVersion)){
            return GetBundleResponse.builder()
                    .code(HttpStatus.NOT_MODIFIED.value())
                    .data(null)
                    .domain(HttpStatus.NOT_MODIFIED.getReasonPhrase())
                    .listVersion(currentListVersion)
                    .build();
        }
        return GetBundleResponse.builder()
                .code(HttpStatus.OK.value())
                .listVersion(currentListVersion)
                .data(currentData)
                .domain(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    @PostMapping("/ao/v1/not/installed/bundles")
    public GetNotInstalledAppsResponse getNotInstalledApps(@RequestBody GetNotInstalledAppsRequest getNotInstalledAppsRequest){
        return GetNotInstalledAppsResponse.builder()
                .code(HttpStatus.OK.value())
                .checksum(getNotInstalledAppsRequest.getChecksum() == null ? currentChecksum : getNotInstalledAppsRequest.getChecksum())
                .domain(HttpStatus.OK.getReasonPhrase())
                .build();
    }

}
