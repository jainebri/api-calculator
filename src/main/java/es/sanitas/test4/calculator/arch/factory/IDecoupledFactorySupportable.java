package es.sanitas.test4.calculator.arch.factory;

/**
 * 
 * @author Jaidermes Nebrijo Duarte (JNEDU0C)
 *
 */
public interface IDecoupledFactorySupportable<T> {

	/**
	 * 
	 * @param context
	 * @return
	 */
	public boolean isSupported(T factoryContext);
}