package com.example.loginapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class LoginappApplication {

	public static void main(String[] args) {

		SpringApplication.run(LoginappApplication.class, args);
	}



//
//	// ✅ Add this bean to increase max POST size (20MB)
//	@Bean
//	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> containerCustomizer() {
//		return factory -> factory.addConnectorCustomizers((Connector connector) -> {
//			connector.setMaxPostSize(20971520);        // 20MB
//			connector.setMaxSavePostSize(20971520);    // 20MB
//		});
//	}

}
