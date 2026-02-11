package br.com.cezaravila.msclientes;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled("CI não sobe infraestrutura (Postgres/RabbitMQ/Keycloak) ainda")
class MsclientesApplicationTests {

	@Test
	void contextLoads() {
	}

}
