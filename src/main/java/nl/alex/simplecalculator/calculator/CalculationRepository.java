package nl.alex.simplecalculator.calculator;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepository extends CrudRepository<Calculation, Integer> {
}
