package es.sanitas.test4.calculator.exceptions.global.exceptions;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import es.sanitas.test4.calculator.api.v1.dto.errors.ErrorMessage;
import es.sanitas.test4.calculator.api.v1.dto.errors.ErrorResponse;
import lombok.Getter;

/**
 * 
 * @author Jaidermes Nebrijo Duarte (jnebrijo@atsistemas.com)
 *
 */
@Getter
public abstract class AbstractAPIException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7473388272493250071L;

	/**
	 * HTTP Status
	 */
	protected HttpStatus httpStatus;

	/**
	 * Error response
	 */
	protected ErrorResponse errorResponse;

	/**
	 * 
	 * @param httpStatus
	 * @param mensajes
	 */
	public AbstractAPIException(HttpStatus httpStatus, ErrorResponse errorResponse) {
		super();
		this.httpStatus = httpStatus;
		this.errorResponse = errorResponse;
	}

	/**
	 * 
	 * @param httpStatus
	 * @param mensajes
	 */
	public AbstractAPIException(HttpStatus httpStatus, String message, Throwable cause) {
		super(message, cause);
		createMensajesFromException(httpStatus, message, cause);
	}

	/**
	 * 
	 * @param httpStatus
	 * @param mensajes
	 */
	public AbstractAPIException(HttpStatus httpStatus, String message) {
		this(httpStatus, message, null);
	}

	/**
	 * 
	 * @param httpStatus
	 * @param mensajes
	 */
	public AbstractAPIException(HttpStatus httpStatus, Throwable cause) {
		this(httpStatus, StringUtils.EMPTY, cause);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public AbstractAPIException(String message, Throwable cause) {
		this(HttpStatus.INTERNAL_SERVER_ERROR, message, cause);
	}

	/**
	 * 
	 * @param message
	 */
	public AbstractAPIException(String message) {
		this(HttpStatus.INTERNAL_SERVER_ERROR, message);
	}

	/**
	 * 
	 * @param cause
	 */
	public AbstractAPIException(Throwable cause) {
		this(HttpStatus.INTERNAL_SERVER_ERROR, StringUtils.EMPTY, cause);
	}

	/**
	 * 
	 * @param status
	 * @param message
	 * @param cause
	 */
	private void createMensajesFromException(HttpStatus status, String message, Throwable cause) {
		this.httpStatus = status;
		this.errorResponse = new ErrorResponse();
		this.errorResponse.getErrrorMessages()
					.add(new ErrorMessage(this.httpStatus.toString(), StringUtils.EMPTY, message, (cause == null ? StringUtils.EMPTY : cause.toString())));
	}

}
