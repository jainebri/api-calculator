package es.sanitas.test4.calculator.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.sanitas.test4.calculator.api.v1.dto.ArithmeticOperation;
import es.sanitas.test4.calculator.api.v1.dto.errors.ErrorResponse;
import es.sanitas.test4.calculator.service.ICalculatorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author Jaidermes Nebrijo Duarte (JNEDU0C)
 *
 */
@RestController
public class CalculatorController extends AbstractBaseRestController{

	/**
	 * 
	 */
	private static final String TAG_CALCULATOR = "Calculator"; 
	
	
	@Autowired
	private ICalculatorService calculatorService;
	
	/**
	 * 
	 * @param datosSolicitud
	 * @return
	 */
	@ApiOperation(value = "Calculate Arimethics Operations", nickname = "calculate", notes = "", response = Double.class, tags = { TAG_CALCULATOR })
	@ApiResponses(value = {
				@ApiResponse(code = 201, message = "200 - Operation execute successfully", response = Double.class),
				@ApiResponse(code = 400, message = "400 Bad Request", response = ErrorResponse.class),
				@ApiResponse(code = 404, message = "404 Not Found", response = ErrorResponse.class),
				@ApiResponse(code = 500, message = "500 Internal Error", response = ErrorResponse.class) })
	@PostMapping(value = "/v1/calculate", produces = { "application/json" })
	public ResponseEntity<Double> calculate(
				@ApiParam(value = "Operation to calculate") @Valid @RequestBody ArithmeticOperation operation) {
		
		return ResponseEntity.ok(calculatorService.calculate(operation)  );
	}

}
