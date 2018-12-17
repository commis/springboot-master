package com.commis.framework.druid;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Druid配置
 */
@Data
@ConfigurationProperties(prefix = "druid.monitor")
public class DruidMonitorProperties {

    private String DruidStatView;
    private String DruidWebStatFilter;

    private String allow;
    private String deny;
    private String loginUsername;
    private String loginPassword;

    private String exclusions;
    private String resetEnable;
}
