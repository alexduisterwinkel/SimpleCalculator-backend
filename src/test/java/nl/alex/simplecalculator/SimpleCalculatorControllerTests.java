package nl.alex.simplecalculator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import nl.alex.simplecalculator.calculator.Calculation;
import nl.alex.simplecalculator.calculator.SimpleCalculatorController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SimpleCalculatorControllerTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private SimpleCalculatorController simpleCalculatorController;

	@Test
	public void testAdd() {
		Calculation calc = createCalculation("+");
		double result = simpleCalculatorController.add(calc);
		assert(result == 10);
	}

	@Test
	public void testSubstract() {
		Calculation calc = createCalculation("-");
		double result = simpleCalculatorController.substract(calc);
		assert(result == 6);
	}

	@Test
	public void testMultiply() {
		Calculation calc = createCalculation("*");
		double result = simpleCalculatorController.multiply(calc);
		assert(result == 16);
	}

	@Test
	public void testDivide() {
		Calculation calc = createCalculation("/");
		double result = simpleCalculatorController.divide(calc);
		assert(result == 4);
	}

	@Test
	public void addCalculationTest() throws Exception {
		Calculation calc = createCalculation("+");
		mvc.perform(post("/calculate")
						.contentType(MediaType.APPLICATION_JSON)
						.content(toJson(calc)))
				.andExpect(status().isOk());
	}

	private Calculation createCalculation(String operand) {
		return new Calculation(1, 8, operand, 2, 0);
	}

	private String toJson(Object calc) {
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(calc);
			return json;
		} catch (JsonProcessingException jpe) {
			return null;
		}
	}

}
