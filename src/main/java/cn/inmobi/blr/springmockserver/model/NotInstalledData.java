package cn.inmobi.blr.springmockserver.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class NotInstalledData {
    private Map<String, String> uidMap;
    private List<String> appNotInstalled;
}
