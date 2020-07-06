package es.sanitas.test4.calculator.exceptions.global.handlers;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import es.sanitas.test4.calculator.api.v1.dto.errors.ErrorMessage;
import es.sanitas.test4.calculator.api.v1.dto.errors.ErrorResponse;
import es.sanitas.test4.calculator.exceptions.global.exceptions.AbstractAPIException;
import es.sanitas.test4.calculator.exceptions.global.exceptions.ArchitectureComponentException;
import es.sanitas.test4.calculator.exceptions.global.exceptions.DatabaseClientException;
import es.sanitas.test4.calculator.exceptions.global.exceptions.RESTClientException;
import es.sanitas.test4.calculator.exceptions.global.exceptions.ServiceException;
import es.sanitas.test4.calculator.exceptions.global.exceptions.TransformationException;
import es.sanitas.test4.calculator.exceptions.global.exceptions.ValidationServiceException;
import es.sanitas.test4.calculator.exceptions.global.exceptions.WSSOAPClientException;


/**
 * 
 * @author Jaidermes Nebrijo Duarte (jnebrijo@atsistemas.com)
 *
 */
@RestControllerAdvice
public class GlobalErrorsExceptionsHandler extends ResponseEntityExceptionHandler {

	/**
	 * 
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalErrorsExceptionsHandler.class);

	/**
	 * 
	 */
	private static final String MORE_INFORMATION_FORMAT = "Timestamp: %s. Path: %s. Objetivo: %s, Argumentos: %s";

	/**
	 * 
	 */
	@Autowired
	private MessageSource messageSource;
	

	/**
	 * Gestiona los errores
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status,
				WebRequest request) {

		logCompleteException(exception);

		ErrorResponse errorResponse = buildMensajesError(exception.getBindingResult(), request);

		return new ResponseEntity<Object>(errorResponse, headers, HttpStatus.BAD_REQUEST);
	}


	/**
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException exception) {

		logCompleteException(exception);
		ErrorResponse errorResponse = new ErrorResponse();
		exception.getConstraintViolations().stream().forEach(constrationViolation -> {
			errorResponse.getErrrorMessages().add(
						new ErrorMessage(
									HttpStatus.BAD_REQUEST.toString(), 
									constrationViolation.getPropertyPath().toString(), 
									constrationViolation.getMessage(),
									String.format(MORE_INFORMATION_FORMAT, 
												Calendar.getInstance().getTime(), 
												constrationViolation.getPropertyPath().toString(),
												constrationViolation.getInvalidValue(), 
												exception.toString())));
		});

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 
	 */
	@Override
	protected ResponseEntity<Object> handleBindException(BindException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
		logCompleteException(exception);

		ErrorResponse mensajes = buildMensajesError(exception.getBindingResult(), request);

		return new ResponseEntity<Object>(mensajes, headers, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status,
				WebRequest request) {
		logCompleteException(exception);
		
		return new ResponseEntity<Object>( buildMensajeErrorGenerico(exception, request, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 */
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
		logCompleteException(exception);

		return new ResponseEntity<Object>( buildMensajeErrorGenerico(exception, request, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ ArchitectureComponentException.class, ServiceException.class, DatabaseClientException.class, RESTClientException.class,
				WSSOAPClientException.class, TransformationException.class, ValidationServiceException.class })
	public ResponseEntity<ErrorResponse> handleServiceException(AbstractAPIException serviceException, WebRequest request) {
		logCompleteException(serviceException);

		return new ResponseEntity<ErrorResponse>(serviceException.getErrorResponse(), serviceException.getHttpStatus());
	}

	/**
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception exception) {

		logCompleteException(exception);

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.getErrrorMessages()
					.add(new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.toString(), StringUtils.EMPTY, exception.getMessage(), exception.toString()));

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 
	 * @param exception
	 */
	private void logCompleteException(Exception exception) {
		LOGGER.info("Global Error and Exception Handler: ", exception);
	}
	
	/**
	 * 
	 * @param exception
	 * @param request
	 * @return
	 */
	private ErrorResponse buildMensajeErrorGenerico(Exception exception, WebRequest request, HttpStatus status) {
		
		ServletWebRequest servletWebRequest = (ServletWebRequest)request;

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.getErrrorMessages()
					.add(new ErrorMessage(
								status.toString(), 
								servletWebRequest.getRequest().getRequestURI(),
								exception.getMessage(), 
								String.format(MORE_INFORMATION_FORMAT, Calendar.getInstance().getTime(), servletWebRequest.getRequest().getRequestURI(), StringUtils.EMPTY, exception.toString())
								)
							);
		return errorResponse;
	}

	/**
	 * 
	 * @param validationErrors
	 * @param request
	 * @return
	 */
	private ErrorResponse buildMensajesError(BindingResult validationErrors, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse();
        if (validationErrors != null && validationErrors.getAllErrors() != null) {
    
			
			for (ObjectError objectError : validationErrors.getAllErrors()) {
				String field = (objectError instanceof FieldError) ? ((FieldError) objectError).getField() : objectError.getCode();
				String message = messageSource.getMessage(objectError.getCode(), objectError.getArguments(), objectError.getDefaultMessage(), Locale.getDefault());
				errorResponse.getErrrorMessages().add(new ErrorMessage(
							HttpStatus.BAD_REQUEST.toString(), 
							field, 
							message,
							String.format(MORE_INFORMATION_FORMAT, Calendar.getInstance().getTime(), ((ServletWebRequest)request).getRequest().getRequestURI(), validationErrors.getTarget(), Arrays.toString(objectError.getArguments()) )));
			}
        }
        return errorResponse;

	}

}
