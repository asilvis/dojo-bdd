package br.com.softplan.ungp.supdev.calculo.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "baseCalculo")
public class FaixaIRRF {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal valorInicial;
    private BigDecimal valorFinal;
    private BigDecimal aliquota;
    private BigDecimal deducao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public BigDecimal getAliquota() {
        return aliquota;
    }

    public void setAliquota(BigDecimal aliquota) {
        this.aliquota = aliquota;
    }

    public BigDecimal getDeducao() {
        return deducao;
    }

    public void setDeducao(BigDecimal deducao) {
        this.deducao = deducao;
    }

    public boolean isOnInterval(BigDecimal valor) {
        return (valorInicial == null || valorInicial.compareTo(valor) <= 0) && (valorFinal == null || valorFinal.compareTo(valor) > 0);
    }
}
