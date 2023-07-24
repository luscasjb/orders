package com.store.orders;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableCaching
@EnableWebMvc
public class OrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersApplication.class, args);
	}

	@Bean
	public CachingConnectionFactory connectionFactory() {
		return new CachingConnectionFactory();
	}

}
