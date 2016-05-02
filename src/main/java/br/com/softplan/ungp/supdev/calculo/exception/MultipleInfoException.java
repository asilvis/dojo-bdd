package br.com.softplan.ungp.supdev.calculo.exception;

import java.util.LinkedHashSet;
import java.util.Set;

public class MultipleInfoException extends RuntimeException {

    private static final long serialVersionUID = 2132402326741326170L;
    private String title;
    private String message;
    private final Set<InfoException> infoExceptions;

    public MultipleInfoException(String message) {
        super(message);
        this.infoExceptions = new LinkedHashSet<>();
    }

    public void addException(InfoException e) {
        infoExceptions.add(e);
    }

    public Set<InfoException> getInfoExceptions() {
        return infoExceptions;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

}
