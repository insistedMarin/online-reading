package org.wy.online_reading.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author 89589
 */
@ConfigurationProperties(prefix = "novel.cors")
@Data
public class CorsProperties {
    private List<String> allowOrigins;
}
