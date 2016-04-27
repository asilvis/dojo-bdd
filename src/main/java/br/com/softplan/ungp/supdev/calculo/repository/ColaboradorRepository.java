package br.com.softplan.ungp.supdev.calculo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.softplan.ungp.supdev.calculo.entity.Colaborador;

public interface ColaboradorRepository extends CrudRepository<Colaborador, Long>{

    List<Colaborador> findByNome(String nome);
}
