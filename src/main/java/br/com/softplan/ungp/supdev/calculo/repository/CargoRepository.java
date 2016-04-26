package br.com.softplan.ungp.supdev.calculo.repository;

import br.com.softplan.ungp.supdev.calculo.entity.Cargo;
import org.springframework.data.repository.CrudRepository;

public interface CargoRepository extends CrudRepository<Cargo, Long>{

    Cargo findBySigla(String sigla);
}
