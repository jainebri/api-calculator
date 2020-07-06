package es.sanitas.test4.calculator.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import es.sanitas.test4.calculator.api.v1.dto.ArithmeticOperation;
import es.sanitas.test4.calculator.exceptions.global.exceptions.ServiceException;
import es.sanitas.test4.calculator.service.IOperationsService;

/**
 * 
 * @author Jaidermes Nebrijo Duarte (jnebrijo@atsistemas.com)
 *
 */
@Service
public class ArithmeticOperationsServiceImpl implements IOperationsService {

	/**
	 * 
	 */
	private static final String ARITHMETIC_OPERATIONS = "ARITMETICAS";

	private static final String PLUS_OPERATION = "+";
	private static final String SUBTRACT_OPERATIONS = "-";

	/**
	 * 
	 */
	@Override
	public Double calculate(ArithmeticOperation operation) {
		Double result = Double.POSITIVE_INFINITY;
		switch (operation.getOperation()) {
		case PLUS_OPERATION: {
			result = plus(operation.getParameters());
		}
			break;

		case SUBTRACT_OPERATIONS: {
			result = subtract(operation.getParameters());
		}
			break;
		default:
			throw new ServiceException(HttpStatus.NOT_FOUND,
						String.format("Operación [%s] no implementada para las operaciones de tipo [%s]", operation.getOperation(), ARITHMETIC_OPERATIONS));
		}

		return result;
	}

	/**
	 * 
	 * @param parameters
	 * @return
	 */
	private Double plus(List<Double> parameters) {

		if (parameters == null || parameters.size() <=0) {
			throw new ServiceException(HttpStatus.BAD_REQUEST, String.format("Operación %s requiere al menos 1 parametro de entrada", PLUS_OPERATION) );
		}
		Double result = 0.0;
		for(Double value : parameters) {
			result += value;
		}
		return result;
	}

	/**
	 * 
	 * @param parameters
	 * @return
	 */
	private Double subtract(List<Double> parameters) {

		if (parameters == null || parameters.size() <= 0) {
			throw new ServiceException(HttpStatus.BAD_REQUEST, String.format("Operación %s requiere al menos 1 parametro de entrada", SUBTRACT_OPERATIONS) );
		}
		Double result = parameters.get(0);
		for(int i = 1; i<=(parameters.size()-1); i++) {
			result -= parameters.get(i);
		}
		return result;
	}

	/**
	 * 
	 */
	@Override
	public boolean isSupported(String factoryContext) {
		return ARITHMETIC_OPERATIONS.equals(factoryContext);
	}

}
