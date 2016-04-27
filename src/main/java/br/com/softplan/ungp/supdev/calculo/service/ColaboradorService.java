package br.com.softplan.ungp.supdev.calculo.service;

import br.com.softplan.ungp.supdev.calculo.entity.Colaborador;
import br.com.softplan.ungp.supdev.calculo.exception.InfoException;
import br.com.softplan.ungp.supdev.calculo.exception.MultipleInfoException;
import br.com.softplan.ungp.supdev.calculo.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public void salvar(Colaborador colaborador) {
        colaboradorRepository.save(colaborador);
    }

    public void salvarEmLote(List<Colaborador> colaboradores) {
        MultipleInfoException multipleInfoException = new MultipleInfoException("Inconsistências");
        colaboradores.forEach(colaborador -> {
            try {
                salvar(colaborador);
            } catch (ConstraintViolationException cve) {
                Set<String> violations = cve.getConstraintViolations().stream()
                        .map(constraintViolation -> colaborador.getNome() + " - " + constraintViolation.getMessage())
                        .collect(Collectors.toSet());
                multipleInfoException.addException(new InfoException(colaborador.getNome(), violations));
            }
        });

        if(!multipleInfoException.getInfoExceptions().isEmpty()) {
            throw multipleInfoException;
        }
    }

    public List<Colaborador> findByNome(String nome) {
        return colaboradorRepository.findByNome(nome);
    }

}
