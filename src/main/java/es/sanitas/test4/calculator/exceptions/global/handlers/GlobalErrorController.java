package es.sanitas.test4.calculator.exceptions.global.handlers;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Jaidermes Nebrijo Duarte (JNEDU0C)
 *
 */
@RestController
@RequestMapping({ GlobalErrorController.ERROR_PATH })
public class GlobalErrorController extends AbstractErrorController {

	/**
	 * Error path
	 */
	protected static final String ERROR_PATH = "/error";

	/**
	 * 
	 * @param errorAttributes
	 */
	public GlobalErrorController(final ErrorAttributes errorAttributes) {
		super(errorAttributes, Collections.emptyList());
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Map<String, Object> body = this.getErrorAttributes(request, false);
		HttpStatus status = this.getStatus(request);
		return new ResponseEntity<>(body, status);
	}

	/**
	 * 
	 */
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

}
