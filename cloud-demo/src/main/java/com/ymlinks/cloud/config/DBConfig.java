//package com.ymlinks.cloud.config;
//
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//@Configuration
//public class DBConfig {
//
//    @Bean
//    public TransportClient transportClient() throws UnknownHostException {
//        return new PreBuiltXPackTransportClient(Settings.builder()
//                .put("cluster.name", "docker-cluster")
//                .put("client.transport.ignore_cluster_name", false)
//                .put("xpack.security.user", "elastic:123456")
//                .build())
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.141.160"), 9300));
//    }
//}
