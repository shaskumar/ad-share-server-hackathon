package cn.inmobi.blr.springmockserver.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BundleData {
    private List<String> bundleIds;
}
