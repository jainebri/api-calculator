package es.sanitas.test4.calculator.editors;

import java.beans.PropertyEditorSupport;

/**
 * 
 * @author Jaidermes Nebrijo Duarte (JNEDU0C)
 *
 */
public class StringToArrayPropertyEditor extends PropertyEditorSupport {

	/**
	 * Separtor ccharacter by default 
	 */
	private String separator = ";";

	/**
	 *
	 */
	public StringToArrayPropertyEditor() {
	}

	/**
	 * @param separator .
	 */
	public StringToArrayPropertyEditor(String separator) {
		this.separator = separator;
	}

	/**
	 * 
	 */
	@Override
	public void setAsText(String text) {
		if (text == null) {
			setValue(null);
		} else {
			String[] split = text.split(separator);
			setValue(split);
		}
	}
}
