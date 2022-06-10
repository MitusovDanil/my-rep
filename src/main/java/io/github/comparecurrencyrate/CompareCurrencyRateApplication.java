package io.github.comparecurrencyrate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CompareCurrencyRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompareCurrencyRateApplication.class, args);
	}

}
