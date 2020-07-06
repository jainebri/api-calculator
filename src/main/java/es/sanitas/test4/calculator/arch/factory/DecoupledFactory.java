package es.sanitas.test4.calculator.arch.factory;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.GenericTypeResolver;
import org.springframework.http.HttpStatus;

import es.sanitas.test4.calculator.exceptions.global.exceptions.ArchitectureComponentException;


/**
 * 
 * @author Jaidermes Nebrijo Duarte (JNEDU0C)
 *
 */
public abstract class DecoupledFactory<T extends IDecoupledFactorySupportable<F>, F> {
	/**
	 * 
	 */
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 
	 */
	private static final String INSTANCE_NOT_FOUND_FORMAT = "Implementación de la clase [%s] no disponible para el contexto [%s] en la factoría [%s].";

	/**
	 * 
	 */
	private List<T> decoupledFactoryInstances;
	
	/**
	 * 
	 * @param supportableServices
	 */
	public DecoupledFactory(List<T> decoupledFactoryInstances) {
		this.decoupledFactoryInstances = decoupledFactoryInstances;
		for (IDecoupledFactorySupportable<F> instance : decoupledFactoryInstances) {
			logger.debug(">> Loading decoupled factory instance [{}] ...", instance );
		}
	}

	/**
	 * 
	 * @param factoryContext
	 * @return
	 */
	public T getInstance(F factoryContext) {
		T instanceObject = decoupledFactoryInstances.stream().filter(instance -> instance.isSupported(factoryContext)).findFirst().orElse(null);
		
		if(instanceObject == null) {
			Class<?>[] validatedType = (Class<?>[]) GenericTypeResolver.resolveTypeArguments(getClass(), DecoupledFactory.class);
			throw new ArchitectureComponentException(HttpStatus.NOT_FOUND, String.format(INSTANCE_NOT_FOUND_FORMAT, validatedType[0], factoryContext, getClass()));
		}
		
		return instanceObject;
	}
}
