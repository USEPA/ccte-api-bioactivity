package gov.epa.ccte.api.bioactivity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BioactivityApplicationTests {

	@Container
	@ServiceConnection
	static PostgreSQLContainer<?> pgsqldb = new PostgreSQLContainer<>("postgres:13-alpine");

	@Test
	void contextLoads() {
	}

	@Test
	public void applicationStarts(){
		assertThatCode(() -> BioactivityApplication.main(new String[]{})).doesNotThrowAnyException();
	}
}
