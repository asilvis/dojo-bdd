package br.com.softplan.ungp.supdev.calculo.repository;

import br.com.softplan.ungp.supdev.calculo.entity.TabelaIRRF;
import org.springframework.data.repository.CrudRepository;

public interface TabelaIRRFRepository extends CrudRepository<TabelaIRRF, Long> {
    TabelaIRRF findBySigla(String sigla);
}
