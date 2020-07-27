package lt.mano.currency;

import lt.mano.currency.model.Rate;
import lt.mano.currency.repository.RateRepository;
import lt.mano.currency.services.XMLService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootDataBase implements ApplicationListener<ContextRefreshedEvent> {


    private RateRepository rateRepository;

    public BootDataBase(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    /**
     * Get data from xml to database
     */
    private void initData() {

        XMLService xmlService = new XMLService();
        List<Rate> ratesXML = xmlService.getCurrentFxRates();

        if (rateRepository.count() == 0) {
            System.out.println("DB is empty, getting data from XML");
            for (Rate tmp : ratesXML) {

                Rate rate = new Rate();
                rate.setCurrency(tmp.getCurrency());
                rate.setDate(tmp.getDate());
                rate.setRate(tmp.getRate());
                rateRepository.save(rate);
            }
        }
    }
}