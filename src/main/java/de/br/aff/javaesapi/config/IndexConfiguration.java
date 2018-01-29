package de.br.aff.javaesapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("search.index")
@Getter
@Setter
public class IndexConfiguration {

    private String name;
    private String type;
}
