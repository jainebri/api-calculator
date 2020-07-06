package es.sanitas.test4.calculator.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.sanitas.test4.calculator.api.v1.dto.ArithmeticOperation;
import es.sanitas.test4.calculator.factory.OperationServiceFactory;
import es.sanitas.test4.calculator.service.ICalculatorService;

/**
 * 
 * @author Jaidermes Nebrijo Duarte (jnebrijo@atsistemas.com)
 *
 */
@Service
public class CalculatorService implements ICalculatorService {

	/**
	 * 
	 */
	@Autowired
	private OperationServiceFactory operationServiceFactory;
	
	/**
	 * 
	 */
	@Override
	public Double calculate(ArithmeticOperation operation) {
		return operationServiceFactory.getInstance(operation.getGroup()).calculate(operation);
	}

}
