package lt.mano.currency.repository;

import lt.mano.currency.model.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends CrudRepository<Rate, Integer> {

    Rate findByCurrency(String currency);
}
