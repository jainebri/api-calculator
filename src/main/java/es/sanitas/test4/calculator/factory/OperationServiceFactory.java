package es.sanitas.test4.calculator.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.sanitas.test4.calculator.arch.factory.DecoupledFactory;
import es.sanitas.test4.calculator.service.IOperationsService;

/**
 * 
 * @author Jaidermes Nebrijo Duarte (JNEDU0C)
 *
 */
@Component
public class OperationServiceFactory extends DecoupledFactory<IOperationsService, String> {

	/**
	 * 
	 * @param supportableInstances
	 */
	public OperationServiceFactory(@Autowired List<IOperationsService> supportableInstances) {
		super(supportableInstances);
	}

}
