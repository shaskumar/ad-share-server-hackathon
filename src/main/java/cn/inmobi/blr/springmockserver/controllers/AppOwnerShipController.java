package cn.inmobi.blr.springmockserver.controllers;

import cn.inmobi.blr.springmockserver.dto.bundle.GetBundleRequest;
import cn.inmobi.blr.springmockserver.dto.bundle.GetBundleResponse;
import cn.inmobi.blr.springmockserver.dto.bundle.GetNotInstalledAppsRequest;
import cn.inmobi.blr.springmockserver.dto.bundle.GetNotInstalledAppsResponse;
import cn.inmobi.blr.springmockserver.model.BundleData;
import cn.inmobi.blr.springmockserver.model.Bundles;
import cn.inmobi.blr.springmockserver.util.MD5Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AppOwnerShipController {

    @Value("${bundle.current.list.version}")
    private String currentListVersion;

    @Value("${bundle.current.data}")
    private String currentData;

    @Value("${bundle.current.checksum}")
    private String currentChecksum;

    @PostMapping(path = "/ao/v1/bundle/ids", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GetBundleResponse getBundleList(@RequestBody GetBundleRequest bundleRequest){
//        List<String> list= new ArrayList<>();
//        list.add("net.one97.paytm");
//        list.add("com.phonepe.app");
//        list.add("com.alibaba.intl.android.apps.poseidon");
        BundleData bundleData = BundleData.builder()
                .bundleIds(Bundles.bundles)
                .build();
        String md5Lower = MD5Util.MD5Lower(MD5Util.sortListToString(Bundles.bundles));
        System.out.println("result:" + md5Lower);
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
                .listVersion(md5Lower)
                .data(bundleData)
                .domain(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    @PostMapping(path = "/ao/v1/not/installed/bundles", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GetNotInstalledAppsResponse getNotInstalledApps(@RequestBody GetNotInstalledAppsRequest getNotInstalledAppsRequest){
//        try{
//            String res = new ObjectMapper().writeValueAsString(getNotInstalledAppsRequest);
//            System.out.println(res);
//        }catch (Exception e){
//            System.out.println("error");
//        }
        String checkSum = getNotInstalledAppsRequest.getChecksum();
        if (getNotInstalledAppsRequest.getCode() == 200) {
            List<String> appNotInstalled = getNotInstalledAppsRequest.getData().getAppNotInstalled();
            List<String> allApps = Bundles.bundles;
            List<String> differences = new ArrayList<>(allApps);
            differences.removeAll(appNotInstalled);
            checkSum = MD5Util.MD5Lower(MD5Util.sortListToString(differences));
        }
        return GetNotInstalledAppsResponse.builder()
                .code(HttpStatus.OK.value())
                .checksum(checkSum)
                .domain(HttpStatus.OK.getReasonPhrase())
                .build();
    }

}
