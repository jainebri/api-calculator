package es.sanitas.test4.calculator.tracer.impl;

import org.springframework.stereotype.Component;

import io.corp.calculator.TracerAPI;
import io.corp.calculator.TracerImpl;

/**
 * 
 * @author Jaidermes Nebrijo Duarte (jnebrijo@atsistemas.com)
 *
 */
@Component
public class TracerAPIImpl implements TracerAPI {

	/**
	 * 
	 */
	private TracerImpl tracerImpl;

	/**
	 * 
	 */
	public TracerAPIImpl() {
		tracerImpl = new TracerImpl();
	}

	/**
	 * 
	 */
	@Override
	public <T> void trace(T result) {
		this.tracerImpl.trace(result);
	}

}
