package br.com.softplan.ungp.supdev.calculo.exception;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class InfoException extends RuntimeException {

	private static final long serialVersionUID = 2132402326741326170L;
	private String title;
	private String message;
	private final Set<String> constraintViolations;

	public InfoException(final String message){
		this.message = message;
		this.title = "";
		constraintViolations = new HashSet<>();
	}

	public InfoException(final String message, final String title){
		this.message = message;
		this.title = title;
		constraintViolations = new LinkedHashSet<>();
	}

	public InfoException(String message, Set<String> constraintViolations) {
		super(message);
		this.constraintViolations = constraintViolations;
    }



    public Set<String> getConstraintViolations() {
		return constraintViolations;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

}
