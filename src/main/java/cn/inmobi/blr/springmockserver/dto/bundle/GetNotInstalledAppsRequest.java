package cn.inmobi.blr.springmockserver.dto.bundle;

import lombok.Data;

@Data
public class GetNotInstalledAppsRequest {
    private String myListVersion;
    private String data;
    private int code;
    private String checksum;
}
