package br.com.softplan.ungp.supdev.calculo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody InfoException validatorException(ConstraintViolationException e) {
        Set<String> violations = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
        return new InfoException(e.getMessage(), violations);
	}
}
