package lt.mano.currency.scheduler;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Update database every 24 h
 */
@Configuration
public class ConfigureQuartz {

    @Bean
    public JobDetail jobADetails() {
        return JobBuilder.newJob(UpdateDataBase.class).withIdentity("updateDB")
                .storeDurably().build();
    }

    @Bean
    public Trigger jobATrigger(JobDetail jobADetails) {

        return TriggerBuilder.newTrigger().forJob(jobADetails)
                .withIdentity("Trigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInHours(24).repeatForever()).build();
    }
}
