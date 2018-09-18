package com.ymlinks.cloud;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableWebMvc
@EnableCaching
@ComponentScan("com.ymlinks")
public class CloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudApplication.class, args);
    }

    @Bean
    public TransportClient transportClient() throws UnknownHostException {
        return new PreBuiltXPackTransportClient(Settings.builder()
                .put("cluster.name", "docker-cluster")
                .put("client.transport.ignore_cluster_name", false)
                .put("xpack.security.user", "elastic:123456")
                .build())
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.141.160"), 9300));
    }
}
