package es.sanitas.test4.calculator.api.v1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import es.sanitas.test4.calculator.editors.StringToArrayPropertyEditor;


/**
 * Base class for all the controllers in the API. It's simplify the configuration of validators 
 * using the Validation Framework of Spring 
 * 
 * 
 * @author Jaidermes Nebrijo Duarte (jnebrijo@atsistemas.com)
 *
 */
@RequestMapping("/api")
public abstract class AbstractBaseRestController {

	/**
	 * Logger Instance shared by all the controllers
	 */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * Conversion Service
     */
	@Autowired(required = false)
    private ConversionService conversionService;
    
	/**
	 * Default implementation of validators. 
	 * 
	 * All controllers should override this method to provide all the validators in the  Spring Validation FrameWork. 
	 *
	 * @return
	 */
	public List<Validator> configureValidators(){
		return new ArrayList<Validator>(0);
	}
	
	/**
	 * Links the corresponding validator's instance in the controller for the operation consumed. 
	 * 
	 * @param binder The Web binder object of spring
	 */
	@InitBinder
    protected void restInitBinder(WebDataBinder binder) {
        if (binder.getConversionService() == null) {
            binder.setConversionService(conversionService);
        }
        binder.registerCustomEditor(String[].class, new StringToArrayPropertyEditor());
        
        List<Validator> validators = configureValidators();
        
        if(validators != null) {
			List<Validator> selectedValidators = validators.stream()
						.filter(v -> binder.getTarget() != null && v.supports(binder.getTarget().getClass())).collect(Collectors.toList());
			if(!selectedValidators.isEmpty()){
				binder.addValidators(selectedValidators.toArray(new Validator[selectedValidators.size()]));
			}
        }
    }    
    
}
