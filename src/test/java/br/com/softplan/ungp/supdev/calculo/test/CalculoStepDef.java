package br.com.softplan.ungp.supdev.calculo.test;

import br.com.softplan.ungp.supdev.calculo.dto.ResultadoCalculoIRRF;
import br.com.softplan.ungp.supdev.calculo.entity.Cargo;
import br.com.softplan.ungp.supdev.calculo.entity.Colaborador;
import br.com.softplan.ungp.supdev.calculo.entity.FaixaIRRF;
import br.com.softplan.ungp.supdev.calculo.entity.TabelaIRRF;
import br.com.softplan.ungp.supdev.calculo.repository.CargoRepository;
import br.com.softplan.ungp.supdev.calculo.repository.ColaboradorRepository;
import br.com.softplan.ungp.supdev.calculo.repository.TabelaIRRFRepository;
import br.com.softplan.ungp.supdev.calculo.service.CalculoService;
import br.com.softplan.ungp.supdev.calculo.test.configuracao.SpringTest;
import cucumber.api.PendingException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by andre.lima on 25/04/2016.
 */
public class CalculoStepDef extends SpringTest {

    @Autowired
    ColaboradorRepository colaboradorRepository;
    @Autowired
    CargoRepository cargoRepository;
    @Autowired
    CalculoService calculoService;

    @Autowired
    TabelaIRRFRepository tabelaIRRFRepository;

    private ResultadoCalculoIRRF resultadoCalculoIRRF;

    @Dado("^que existe um colaborador com nome \"([^\"]*)\" no cargo \"([^\"]*)\" com remuneração de \"([^\"]*)\"$")
    public void que_existe_um_colaborador_com_nome_no_cargo_com_remuneração_de(String nome, String siglaCargo, BigDecimal remuneracao) throws Throwable {
        que_existe_um_colaborador_com_nome_no_cargo_com_remuneração_de_e_dependentes(nome, siglaCargo, remuneracao, 0);
    }

    @Quando("^calculo o imposto de renda do \"([^\"]*)\" usando a tabela do IRRF \"([^\"]*)\"$")
    public void calculo_o_imposto_de_renda_do(String nome, String siglaTabela) throws Throwable {
        List<Colaborador> colaborador = colaboradorRepository.findByNome(nome);
        TabelaIRRF tabelaIRRF = tabelaIRRFRepository.findBySigla(siglaTabela);
        colaborador.stream().findFirst().ifPresent(colaborador1 -> resultadoCalculoIRRF = calculoService.calculaIRRF(colaborador1, tabelaIRRF));

    }

    @Então("^o valor deve ser \"([^\"]*)\"$")
    public void o_valor_deve_ser(BigDecimal valor) throws Throwable {
        assertThat(resultadoCalculoIRRF.getValor()).isEqualByComparingTo(valor);
    }

    @E("^exista a tabela do IRRF com sigla \"([^\"]*)\" com dedução para dependentes de \"([^\"]*)\" e as faixas:$")
    public void exista_a_tabela_do_IRRF_com_sigla_com_dedução_para_dependentes_de_e_as_faixas(String sigla, BigDecimal deducaoDependente, List<FaixaIRRF> faixas) throws Throwable {
        TabelaIRRF tabelaIRRF = new TabelaIRRF();
        tabelaIRRF.setFaixas(faixas);
        tabelaIRRF.setSigla(sigla);
        tabelaIRRF.setValorDeducaoDependente(deducaoDependente);
        tabelaIRRFRepository.save(tabelaIRRF);
    }

    @Dado("^que existe um colaborador com nome \"([^\"]*)\" no cargo \"([^\"]*)\" com remuneração de \"([^\"]*)\" e \"([^\"]*)\" dependentes$")
    public void que_existe_um_colaborador_com_nome_no_cargo_com_remuneração_de_e_dependentes(String nome, String siglaCargo, BigDecimal remuneracao, Integer dependentes) throws Throwable {
        Cargo cargo = cargoRepository.findBySigla(siglaCargo);
        Colaborador colaborador = new Colaborador();
        colaborador.setNome(nome);
        colaborador.setNumeroCPF("871.934.776-68");
        colaborador.setDataNascimento(new Date());
        colaborador.setCargo(cargo);
        colaborador.setDependentes(dependentes);
        colaborador.setRemuneracao(remuneracao);
        colaboradorRepository.save(colaborador);
    }
}
