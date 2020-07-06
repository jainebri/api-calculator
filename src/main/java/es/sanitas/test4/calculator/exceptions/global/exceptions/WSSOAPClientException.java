package es.sanitas.test4.calculator.exceptions.global.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Setter;

@Setter
public class WSSOAPClientException extends AbstractAPIException {

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
	public WSSOAPClientException(HttpStatus httpStatus, String message, Throwable cause) {
		super(httpStatus, message, cause);
	}

	/**
	 * 
	 * @param httpStatus
	 * @param message
	 */
	public WSSOAPClientException(HttpStatus httpStatus, String message) {
		super(httpStatus, message);
	}

	/**
	 * 
	 * @param httpStatus
	 * @param cause
	 */
	public WSSOAPClientException(HttpStatus httpStatus, Throwable cause) {
		super(httpStatus, cause);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public WSSOAPClientException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param message
	 */
	public WSSOAPClientException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public WSSOAPClientException(Throwable cause) {
		super(cause);
	}

}