package br.com.softplan.ungp.supdev.calculo.service;

import br.com.softplan.ungp.supdev.calculo.dto.ResultadoCalculoIRRF;
import br.com.softplan.ungp.supdev.calculo.entity.Colaborador;
import br.com.softplan.ungp.supdev.calculo.entity.FaixaIRRF;
import br.com.softplan.ungp.supdev.calculo.entity.TabelaIRRF;
import br.com.softplan.ungp.supdev.calculo.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CalculoService {

    private static final BigDecimal CENTENA = new BigDecimal(100);

    public ResultadoCalculoIRRF calculaIRRF(Colaborador colaborador, TabelaIRRF tabelaIRRF) {
        Optional<FaixaIRRF> faixaPorValor = tabelaIRRF.getFaixaPorValor(colaborador.getRemuneracao());
        ResultadoCalculoIRRF resultadoCalculoIRRF = new ResultadoCalculoIRRF();
        faixaPorValor.ifPresent(faixaIRRF -> {
            BigDecimal aliquota = getSafeAliquota(faixaIRRF);
            BigDecimal remuneracaoComAliquota = colaborador.getRemuneracao().multiply(aliquota).divide(CENTENA);
            BigDecimal resultado = remuneracaoComAliquota.subtract(getSafeDeducao(faixaIRRF));
            resultadoCalculoIRRF.setValor(resultado);
        });

        return resultadoCalculoIRRF;
    }

    private BigDecimal getSafeAliquota(FaixaIRRF faixaIRRF) {
        return faixaIRRF.getAliquota() == null ? BigDecimal.ZERO :  faixaIRRF.getAliquota();
    }

    private BigDecimal getSafeDeducao(FaixaIRRF faixaIRRF) {
        return faixaIRRF.getDeducao() == null ? BigDecimal.ZERO :  faixaIRRF.getDeducao();
    }

}
