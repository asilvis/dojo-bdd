package br.com.softplan.ungp.supdev.calculo.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.ungp.supdev.calculo.entity.Colaborador;
import br.com.softplan.ungp.supdev.calculo.exception.BusinessException;
import br.com.softplan.ungp.supdev.calculo.repository.ColaboradorRepository;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public void salvar(Colaborador colaborador) {
        try {
            validarData(colaborador.getDataNascimento());
            colaboradorRepository.save(colaborador);
        } catch (ConstraintViolationException cve) {
            Set<String> violations = cve.getConstraintViolations().stream()
                    .map(constraintViolation -> constraintViolation.getMessage())
                    .collect(Collectors.toSet());

            throw new BusinessException(violations);
        }
    }
    
    private void validarData(String dataNascimento) {
       String[] diamesano = dataNascimento.split("/");
       if (Integer.parseInt(diamesano[2]) <= 1900) {
           throw new BusinessException("O ano de nascimento esta incorreto");
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
