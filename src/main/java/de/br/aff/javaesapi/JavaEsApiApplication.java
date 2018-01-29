package de.br.aff.javaesapi;

import de.br.aff.javaesapi.config.IndexConfiguration;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.net.InetAddress;

@EnableConfigurationProperties(IndexConfiguration.class)
@SpringBootApplication
public class JavaEsApiApplication {

    @Value("${elasticsearch.host}")
    private String elasticsearchHost;

    @Value("${elasticsearch.port}")
    private int elasticsearchPort;

    @Value("${elasticsearch.clustername}")
    private String elasticsearchClustername;


    public static void main(String[] args) {
        SpringApplication.run(JavaEsApiApplication.class, args);
    }

    //elasticsearch client
    @Bean
    public Client client() throws Exception {

        Settings esSettings = Settings.builder()
                .put("cluster.name", elasticsearchClustername)
                .build();

        return new PreBuiltTransportClient(esSettings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(elasticsearchHost), elasticsearchPort));
    }

}
