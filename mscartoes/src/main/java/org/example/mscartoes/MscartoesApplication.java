package org.example.mscartoes;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.example")
@EnableRabbit
public class MscartoesApplication {

	public static void main(String[] args) {
        SpringApplication.run(MscartoesApplication.class, args);
    }

}
