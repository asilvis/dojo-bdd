package br.com.softplan.ungp.supdev.calculo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.softplan.ungp.supdev.calculo.dto.ResultadoCalculoIRRF;
import br.com.softplan.ungp.supdev.calculo.entity.Colaborador;
import br.com.softplan.ungp.supdev.calculo.entity.FaixaIRRF;
import br.com.softplan.ungp.supdev.calculo.entity.TabelaIRRF;

@Service
public class CalculoService {

    private static final BigDecimal CENTENA = new BigDecimal(100);

    public ResultadoCalculoIRRF calculaIRRF(Colaborador colaborador, TabelaIRRF tabelaIRRF) {
        Optional<FaixaIRRF> faixaPorValor = tabelaIRRF.getFaixaPorValor(colaborador.getRemuneracao());
        ResultadoCalculoIRRF resultadoCalculoIRRF = new ResultadoCalculoIRRF();

        BigDecimal deducaoDependentes = tabelaIRRF.getValorDeducaoDependente().multiply(BigDecimal.valueOf(colaborador.getDependentes()));
        BigDecimal remuneracao = colaborador.getRemuneracao().subtract(deducaoDependentes);
        faixaPorValor.ifPresent(faixaIRRF -> {
            BigDecimal aliquota = getSafeAliquota(faixaIRRF);

            BigDecimal remuneracaoComAliquota = remuneracao.multiply(aliquota).divide(CENTENA);
            BigDecimal resultado = remuneracaoComAliquota.subtract(getSafeDeducao(faixaIRRF));
            BigDecimal roundedResult = resultado.setScale(2, RoundingMode.HALF_UP);
            resultadoCalculoIRRF.setValor(roundedResult);
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
