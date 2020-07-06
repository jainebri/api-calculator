package es.sanitas.test4.calculator.exceptions.global.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.WebRequest;

import es.sanitas.test4.calculator.api.v1.dto.errors.ErrorMessage;


/**
 * 
 * @author Jaidermes Nebrijo Duarte (JNEDU0C)
 *
 */
public class GlobalErrorAttributes extends DefaultErrorAttributes {

	/**
	 * 
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalErrorAttributes.class);

	/**
	 * 
	 */
	private static final String SINGLE_FORMAT = "%s";

	/**
	 * 
	 */
	private static final String MORE_INFORMATION_FORMAT = "Timestamp: %s. Status : %s. Error: %s. Message: %s. Path: %s";

	/**
	 * 
	 */
	private static final String DESCRIPTION_FORMAT = "Field error in object [%s] on field [%s]: rejected value [%s]. %s";


	
	/**
	 * 
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
		// Lets Spring handle the error
		Map<String, Object> defaultErrorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

		for (String key : defaultErrorAttributes.keySet()) {
			Object objectValue = defaultErrorAttributes.get(key);
			LOGGER.info(">> {} = {} = {} = {}", key, objectValue, objectValue.getClass(), includeStackTrace);
		}

		// Create custom object
		List<ErrorMessage> datosMensajeList = new ArrayList<ErrorMessage>(0);

		if (defaultErrorAttributes.containsKey(GlobalExceptionConstants.ATTRIBUTE_ERRORS)) {
			mapsFieldErrors(defaultErrorAttributes, datosMensajeList);
		} else {
			mapSimpleMensaje(defaultErrorAttributes, datosMensajeList);
		}

		Map<String, Object> customAttributes = new HashMap<String, Object>(0);
		customAttributes.put(GlobalExceptionConstants.ATTRIBUTE_MENSAJE, datosMensajeList);

		return customAttributes;

	}

	/**
	 * 
	 * @param defaultErrorAttributes
	 * @param datosMensajeList
	 */
	private void mapSimpleMensaje(Map<String, Object> defaultErrorAttributes, List<ErrorMessage> datosMensajeList) {
		datosMensajeList
					.add(new ErrorMessage(
								String.format(SINGLE_FORMAT, defaultErrorAttributes.get(GlobalExceptionConstants.ATTRIBUTE_STATUS)),
								String.format(SINGLE_FORMAT,
											defaultErrorAttributes.getOrDefault(GlobalExceptionConstants.ATTRIBUTE_PATH,
														GlobalExceptionConstants.DEFAULT_ATTRIBUTE_PATH)),
								String.format(SINGLE_FORMAT,
											defaultErrorAttributes.getOrDefault(GlobalExceptionConstants.ATTRIBUTE_MESSAGE,
														GlobalExceptionConstants.DEFAULT_ATTRIBUTE_MESSAGE)),
								createAmpliacionInformacion(defaultErrorAttributes)));
	}

	/**
	 * 
	 * @param defaultErrorAttributes
	 * @param datosMensajeList
	 */
	private void mapsFieldErrors(Map<String, Object> defaultErrorAttributes, List<ErrorMessage> datosMensajeList) {

		Collection<?> fieldErrors = (Collection<?>) defaultErrorAttributes.get(GlobalExceptionConstants.ATTRIBUTE_ERRORS);

		for (Object error : fieldErrors) {
			FieldError fieldError = (FieldError) error;

			datosMensajeList.add(new ErrorMessage(
						fieldError.getCode(), 
						String.format("%s.%s", fieldError.getObjectName(), fieldError.getField()), 
						String.format(DESCRIPTION_FORMAT,
									fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage()),
						createAmpliacionInformacion(defaultErrorAttributes)));

		}

	}

	/**
	 * 
	 * @param defaultErrorAttributes
	 * @return
	 */
	private String createAmpliacionInformacion(Map<String, Object> defaultErrorAttributes) {
		return String.format(MORE_INFORMATION_FORMAT, defaultErrorAttributes.get(GlobalExceptionConstants.ATTRIBUTE_TIMESTAMP),
					defaultErrorAttributes.get(GlobalExceptionConstants.ATTRIBUTE_STATUS),
					defaultErrorAttributes.getOrDefault(GlobalExceptionConstants.ATTRIBUTE_ERROR, GlobalExceptionConstants.DEFAULT_ATTRIBUTE_ERROR),
					defaultErrorAttributes.getOrDefault(GlobalExceptionConstants.ATTRIBUTE_MESSAGE, GlobalExceptionConstants.DEFAULT_ATTRIBUTE_MESSAGE),
					defaultErrorAttributes.getOrDefault(GlobalExceptionConstants.ATTRIBUTE_PATH, GlobalExceptionConstants.DEFAULT_ATTRIBUTE_PATH));
	}


}
