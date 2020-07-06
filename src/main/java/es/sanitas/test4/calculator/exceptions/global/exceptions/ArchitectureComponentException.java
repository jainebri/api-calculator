package es.sanitas.test4.calculator.exceptions.global.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Setter;

@Setter
public class ArchitectureComponentException extends AbstractAPIException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6334849313418035768L;

	/**
	 * 
	 * @param httpStatus
	 * @param message
	 * @param cause
	 */
	public ArchitectureComponentException(HttpStatus httpStatus, String message, Throwable cause) {
		super(httpStatus, message, cause);
	}

	/**
	 * 
	 * @param httpStatus
	 * @param message
	 */
	public ArchitectureComponentException(HttpStatus httpStatus, String message) {
		super(httpStatus, message);
	}

	/**
	 * 
	 * @param httpStatus
	 * @param cause
	 */
	public ArchitectureComponentException(HttpStatus httpStatus, Throwable cause) {
		super(httpStatus, cause);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public ArchitectureComponentException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param message
	 */
	public ArchitectureComponentException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public ArchitectureComponentException(Throwable cause) {
		super(cause);
	}

}