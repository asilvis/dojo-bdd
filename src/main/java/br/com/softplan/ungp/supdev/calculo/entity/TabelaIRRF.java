package br.com.softplan.ungp.supdev.calculo.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "baseCalculo")
public class TabelaIRRF {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String sigla;
    private String descricao;
    private BigDecimal valorDeducaoDependente;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<FaixaIRRF> faixas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorDeducaoDependente() {
        return valorDeducaoDependente;
    }

    public void setValorDeducaoDependente(BigDecimal valorDeducaoDependente) {
        this.valorDeducaoDependente = valorDeducaoDependente;
    }

    public List<FaixaIRRF> getFaixas() {
        return faixas;
    }

    public void setFaixas(List<FaixaIRRF> faixas) {
        this.faixas = faixas;
    }

    public Optional<FaixaIRRF> getFaixaPorValor(BigDecimal remuneracao) {
        Optional<FaixaIRRF> first = getFaixas().stream().filter(faixaIRRF -> faixaIRRF.isOnInterval(remuneracao)).findFirst();
        return first;
    }
}
