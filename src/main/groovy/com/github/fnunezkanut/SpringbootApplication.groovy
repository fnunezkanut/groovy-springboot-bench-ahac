package com.github.fnunezkanut

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Configuration

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
class SpringbootApplication {

	static void main(String[] args) {
		SpringApplication.run SpringbootApplication, args
	}
}
