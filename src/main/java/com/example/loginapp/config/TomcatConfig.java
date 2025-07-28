package com.example.loginapp.config;

import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {

    private static final int MAX_POST_SIZE = 20 * 1024 * 1024; // 20MB
    private static final int MAX_FILE_COUNT = 20; // ⬅️ Add this

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> containerCustomizer() {
        return factory -> factory.addConnectorCustomizers(connector -> {
            connector.setMaxPostSize(MAX_POST_SIZE);
            connector.setMaxSavePostSize(MAX_POST_SIZE);

            // Set multipart config (including file count limit)
            connector.setProperty("maxParameterCount", "10000"); // Optional, increase form field count
            connector.setProperty("maxFileCount", String.valueOf(MAX_FILE_COUNT)); // ✅ This fixes the error

            System.out.println("✅ TomcatConfig loaded");
        });
    }
}
