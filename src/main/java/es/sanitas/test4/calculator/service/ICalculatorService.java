package es.sanitas.test4.calculator.service;

import es.sanitas.test4.calculator.api.v1.dto.ArithmeticOperation;

/**
 * 
 * @author Jaidermes Nebrijo Duarte (jnebrijo@atsistemas.com)
 *
 */
public interface ICalculatorService {

	/**
	 * 
	 * @param operation
	 * @return
	 */
	public Double calculate(ArithmeticOperation operation); 
	
}
