package es.sanitas.test4.calculator.exceptions.global.exceptions;

import org.springframework.http.HttpStatus;

import es.sanitas.test4.calculator.api.v1.dto.errors.ErrorResponse;
import lombok.Setter;

@Setter
public class ValidationServiceException extends AbstractAPIException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6334849313418035768L;

	/**
	 * 
	 * @param httpStatus
	 * @param mensajes
	 */
	public ValidationServiceException(HttpStatus httpStatus, ErrorResponse errorResponse) {
		super(httpStatus, errorResponse);
	}

	/**
	 * 
	 * @param httpStatus
	 * @param message
	 * @param cause
	 */
	public ValidationServiceException(HttpStatus httpStatus, String message, Throwable cause) {
		super(httpStatus, message, cause);
	}

	/**
	 * 
	 * @param httpStatus
	 * @param message
	 */
	public ValidationServiceException(HttpStatus httpStatus, String message) {
		super(httpStatus, message);
	}

	/**
	 * 
	 * @param httpStatus
	 * @param cause
	 */
	public ValidationServiceException(HttpStatus httpStatus, Throwable cause) {
		super(httpStatus, cause);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public ValidationServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param message
	 */
	public ValidationServiceException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause
	 */
	public ValidationServiceException(Throwable cause) {
		super(cause);
	}

}
