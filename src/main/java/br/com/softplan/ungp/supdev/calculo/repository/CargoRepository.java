package br.com.softplan.ungp.supdev.calculo.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.softplan.ungp.supdev.calculo.entity.Cargo;

public interface CargoRepository extends CrudRepository<Cargo, Long>{

    Cargo findBySigla(String sigla);
}
