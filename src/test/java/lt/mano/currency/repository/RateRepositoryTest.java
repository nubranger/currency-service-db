package lt.mano.currency.repository;

import lt.mano.currency.model.Rate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RateRepositoryTest {

    @Autowired
    private RateRepository rateRepository;

    @Test
    public void testSaveRate() {

        Rate rate = new Rate("2020-07-27", "MyCurrency", 1.777);
        rateRepository.save(rate);
        Rate rate2 = rateRepository.findByCurrency("MyCurrency");
        assertNotNull(rate);
        assertEquals(rate2.getCurrency(), rate.getCurrency());
        assertEquals(rate2.getRate(), rate.getRate());
    }

    @Test
    public void testGetRate() {

        Rate rate = new Rate("2020-07-27", "MyCurrency", 1.777);
        rateRepository.save(rate);
        Rate rate2 = rateRepository.findByCurrency("MyCurrency");
        assertNotNull(rate);
        assertEquals(rate2.getCurrency(), rate.getCurrency());
        assertEquals(rate2.getRate(), rate.getRate());
    }

    @Test
    public void testDeleteRate() {
        Rate rate = new Rate("2020-07-27", "MyCurrency", 1.777);
        rateRepository.save(rate);
        rateRepository.delete(rate);
    }

    @Test
    public void testFindAllRates() {
        Rate rate = new Rate("2020-07-27", "MyCurrency", 1.777);
        rateRepository.save(rate);
        assertNotNull(rateRepository.findAll());
    }


    @Test
    public void testDeleteByRateId() {
        Rate rate = new Rate("2020-07-27", "MyCurrency", 1.777);
        Rate emp = rateRepository.save(rate);
        rateRepository.deleteById(emp.getId());
    }
}