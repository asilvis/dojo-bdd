package br.com.softplan.ungp.supdev.calculo.service;

import br.com.softplan.ungp.supdev.calculo.entity.Colaborador;
import br.com.softplan.ungp.supdev.calculo.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public void salvar(Colaborador colaborador) {
        colaboradorRepository.save(colaborador);
    }

    public List<Colaborador> findByNome(String nome) {
        return colaboradorRepository.findByNome(nome);
    }

}
