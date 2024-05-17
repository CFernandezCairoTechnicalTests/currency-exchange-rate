package cu.avangenio.technicaltest.cfernandezcairo.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.config.EnableNeo4jAuditing;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jAuditing
@EnableNeo4jRepositories(basePackages = {"cu.avangenio.technicaltest.cfernandezcairo.neo4j.repository"})
public class CurrencyExchangeRateNeo4jStorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeRateNeo4jStorageApplication.class, args);
	}

}
