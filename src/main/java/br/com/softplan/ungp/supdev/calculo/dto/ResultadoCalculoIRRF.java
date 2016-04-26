package br.com.softplan.ungp.supdev.calculo.dto;

import java.math.BigDecimal;

/**
 * Created by andre.lima on 26/04/2016.
 */
public class ResultadoCalculoIRRF {

    private BigDecimal valor;
    private Integer dependentes;
    private Integer faixaUtilizada;

    public ResultadoCalculoIRRF() {
    }

    public ResultadoCalculoIRRF(BigDecimal valor, Integer dependentes, Integer faixaUtilizada) {
        this.valor = valor;
        this.dependentes = dependentes;
        this.faixaUtilizada = faixaUtilizada;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getDependentes() {
        return dependentes;
    }

    public void setDependentes(Integer dependentes) {
        this.dependentes = dependentes;
    }

    public Integer getFaixaUtilizada() {
        return faixaUtilizada;
    }

    public void setFaixaUtilizada(Integer faixaUtilizada) {
        this.faixaUtilizada = faixaUtilizada;
    }
}
