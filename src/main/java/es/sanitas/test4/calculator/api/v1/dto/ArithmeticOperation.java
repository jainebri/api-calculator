package es.sanitas.test4.calculator.api.v1.dto;

import java.util.List;

import lombok.Data;

/**
 * 
 * @author Jaidermes Nebrijo Duarte (jnebrijo@atsistemas.com)
 *
 */
@Data
public class ArithmeticOperation {

	private String group;
	
	private String operation;
	
	private List<Double> parameters;
}
