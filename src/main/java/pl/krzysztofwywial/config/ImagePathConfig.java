package pl.krzysztofwywial.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "path")
@Component
@NoArgsConstructor
@Getter
@Setter
public class ImagePathConfig {
    private String imageSave;
    private String imageGet;
}
