package br.com.softplan.ungp.supdev.calculo.repository;

import br.com.softplan.ungp.supdev.calculo.entity.Colaborador;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ColaboradorRepository extends CrudRepository<Colaborador, Long>{

    List<Colaborador> findByNome(String nome);
}
