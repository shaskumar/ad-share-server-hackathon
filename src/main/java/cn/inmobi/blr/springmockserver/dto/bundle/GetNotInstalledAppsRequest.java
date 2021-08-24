package cn.inmobi.blr.springmockserver.dto.bundle;

import cn.inmobi.blr.springmockserver.model.NotInstalledData;
import lombok.Data;

@Data
public class GetNotInstalledAppsRequest {

    private String myListVersion;
    private NotInstalledData data;
    private int code;
    private String checksum;
}
