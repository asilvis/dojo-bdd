package br.com.softplan.ungp.supdev.calculo.exception;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 2132402326741326170L;
    private final Set<String> details;

    public BusinessException() {
        this.details = new LinkedHashSet<>();
    }

    public BusinessException(Set<String> details) {
        this.details = details;
    }

    public void addMsg(String msg) {
        details.add(msg);
    }

    public void addMsg(Set<String> msgs) {
        details.addAll(msgs);
    }

    public Set<String> getDetails() {
        return details;
    }

    public String getMessage() {
        return details.stream().collect(Collectors.joining(System.lineSeparator()));
    }

}
