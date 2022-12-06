package com.example.daonv_onemoung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication()
@PropertySources({
		@PropertySource("classpath:application.yml")
})
public class DaonvOnemoungApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaonvOnemoungApplication.class, args);
	}

}
