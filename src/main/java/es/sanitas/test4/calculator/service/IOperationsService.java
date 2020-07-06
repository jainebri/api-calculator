package es.sanitas.test4.calculator.service;

import es.sanitas.test4.calculator.api.v1.dto.ArithmeticOperation;
import es.sanitas.test4.calculator.arch.factory.IDecoupledFactorySupportable;

/**
 * 
 * @author Jaidermes Nebrijo Duarte (jnebrijo@atsistemas.com)
 *
 */
public interface IOperationsService extends IDecoupledFactorySupportable<String>{

	public Double calculate(ArithmeticOperation operation);
}
