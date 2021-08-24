package cn.inmobi.blr.springmockserver.dto.bundle;

import cn.inmobi.blr.springmockserver.model.BundleData;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetBundleResponse {
    private int code;
    private String domain;
    private String listVersion;
    private BundleData data;
}
