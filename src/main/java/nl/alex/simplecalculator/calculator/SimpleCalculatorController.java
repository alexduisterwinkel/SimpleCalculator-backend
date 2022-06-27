package nl.alex.simplecalculator.calculator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;


@RestController
public class SimpleCalculatorController {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Autowired
    private CalculationRepository calculationepository;

    Logger logger = LoggerFactory.getLogger(SimpleCalculatorController.class);

    @RequestMapping("/getHistory")
    public List<Calculation> getCalculations() {
        return (List<Calculation>) calculationepository.findAll();
    }

    @PostMapping("/calculate")
    public Calculation calculate(@RequestBody String calc) {
        logger.info("Received Post:" + calc);

       try {
            Calculation calc1 = new ObjectMapper().readValue(calc, Calculation.class);
            calc1.set_id(generateUniqueId());
            calc1 = doCalculate(calc1);
            calculationepository.save(calc1);
            return calc1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Calculation doCalculate(Calculation calc) {
        double  result;
        switch(calc.getDivider()) {
            case "+": result = add(calc); break;
            case "-": result =  substract(calc); break;
            case "*": result =  multiply(calc); break;
            default: result =  divide(calc);
        }

        result = Math.round(result * 100.0) / 100.0;
        calc.setResult(result);
        return calc;
    }

    public double add(Calculation calc) {
        return calc.getValue1()+calc.getValue2();
    }

    public double substract(Calculation calc) {
        return calc.getValue1()-calc.getValue2();
    }

    public double multiply(Calculation calc) {
        return calc.getValue1()*calc.getValue2();

    }

    public double divide(Calculation calc) {
        return calc.getValue1()/calc.getValue2();
    }

    private int generateUniqueId() {
        return Integer.parseInt(RandomStringUtils.randomNumeric(6));
    }
}
