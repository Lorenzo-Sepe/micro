package it.cgmconsulting.tag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsTagApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTagApplication.class, args);
	}

}
