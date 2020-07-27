package lt.mano.currency.scheduler;

import lt.mano.currency.model.Rate;
import lt.mano.currency.repository.RateRepository;
import lt.mano.currency.services.XMLService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Update database if its not empty
 */
public class UpdateDataBase implements Job {
    XMLService xmlService = new XMLService();
    List<Rate> ratesXML = xmlService.getCurrentFxRates();

    @Autowired
    private RateRepository rateRepository;

    @Override
    public void execute(JobExecutionContext context) {

        int count = 0;
        System.out.println("Updating DB :: " + new Date());

        if (rateRepository.count() != 0) {

            System.out.println("DB is not empty, updating rates");
            for (Rate ratesDB : rateRepository.findAll()) {

                ratesDB.setCurrency(ratesXML.get(count).getCurrency());
                ratesDB.setDate(ratesXML.get(count).getDate());
                ratesDB.setRate(ratesXML.get(count).getRate());
                rateRepository.save(ratesDB);

                if (count <= rateRepository.count()) {
                    count++;
                }
            }
        }
    }
}
