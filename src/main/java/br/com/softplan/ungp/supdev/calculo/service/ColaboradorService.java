package br.com.softplan.ungp.supdev.calculo.service;

import br.com.softplan.ungp.supdev.calculo.entity.Colaborador;
import br.com.softplan.ungp.supdev.calculo.exception.BusinessException;
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
        try {
            colaboradorRepository.save(colaborador);
        } catch (ConstraintViolationException cve) {
            Set<String> violations = cve.getConstraintViolations().stream()
                    .map(constraintViolation -> constraintViolation.getMessage())
                    .collect(Collectors.toSet());

            throw new BusinessException(violations);
        }
    }

    public void salvarEmLote(List<Colaborador> colaboradores) {
        BusinessException businessException = new BusinessException();
        colaboradores.forEach(colaborador -> {
            try {
                colaboradorRepository.save(colaborador);
            } catch (ConstraintViolationException cve) {
                Set<String> violations = cve.getConstraintViolations().stream()
                        .map(constraintViolation -> colaborador.getNome() + " - " + constraintViolation.getMessage())
                        .collect(Collectors.toSet());
                businessException.addMsg(violations);
            }
        });

        if(!businessException.getDetails().isEmpty()) {
            throw businessException;
        }
    }

    public List<Colaborador> findByNome(String nome) {
        return colaboradorRepository.findByNome(nome);
    }

}
