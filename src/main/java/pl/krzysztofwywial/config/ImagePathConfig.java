package pl.krzysztofwywial.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "path")
@Component
@ConstructorBinding
@Getter
@Setter
public class ImagePathConfig {
    private String imageSave;
    private String imageGet;
}
