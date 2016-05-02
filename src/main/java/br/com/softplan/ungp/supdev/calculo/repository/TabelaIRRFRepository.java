package br.com.softplan.ungp.supdev.calculo.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.softplan.ungp.supdev.calculo.entity.TabelaIRRF;

public interface TabelaIRRFRepository extends CrudRepository<TabelaIRRF, Long> {
    TabelaIRRF findBySigla(String sigla);
}
