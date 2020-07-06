package es.sanitas.test4.calculator.exceptions.global.exceptions;

import org.springframework.http.HttpStatus;

import es.sanitas.test4.calculator.api.v1.dto.errors.ErrorResponse;
import lombok.Setter;

@Setter
public class ServiceException extends AbstractAPIException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6334849313418035768L;

	/**
	 * 
	 * @param httpStatus
	 * @param mensajes
	 */
	public ServiceException(HttpStatus httpStatus, ErrorResponse errorResponse) {
		super(httpStatus, errorResponse);
	}
	
	/**
	 * 
	 * @param httpStatus
	 * @param message
	 * @param cause
	 */
	public ServiceException(HttpStatus httpStatus, String message, Throwable cause) {
		super(httpStatus, message, cause);
	}

	/**
	 * 
	 * @param httpStatus
	 * @param message
	 */
	public ServiceException(HttpStatus httpStatus, String message) {
		super(httpStatus, message);
	}

	/**
	 * 
	 * @param httpStatus
	 * @param cause
	 */
	public ServiceException(HttpStatus httpStatus, Throwable cause) {
		super(httpStatus, cause);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

}
