package lt.mano.currency.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("lt.mano.currency.repository")
public class JPAConfig {

}
