package br.com.softplan.ungp.supdev.calculo.test.pessoa;

import br.com.softplan.ungp.supdev.calculo.entity.Cargo;
import br.com.softplan.ungp.supdev.calculo.entity.Colaborador;
import br.com.softplan.ungp.supdev.calculo.exception.InfoException;
import br.com.softplan.ungp.supdev.calculo.repository.CargoRepository;
import br.com.softplan.ungp.supdev.calculo.test.configuracao.SpringTest;
import cucumber.api.Format;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import gherkin.deps.com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by andre.lima on 20/04/2016.
 */
public class ColaboradorStepDef extends SpringTest {

    @Autowired
    CargoRepository cargoRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Colaborador colaborador;
    private Exception thrownException;

    @Dado("^uma pessoa com nome \"([^\"]*)\"$")
    public void umaPessoaComNome(String nome) throws Throwable {
        colaborador = new Colaborador();
        colaborador.setNome(nome);
    }

    @Dado("^a data de nascimento é \"([^\"]*)\"$")
    public void aDataDeNascimentoE(@Format("dd/MM/yyyy") Date dataNascimento) throws Throwable {
        getColaborador().setDataNascimento(dataNascimento);
    }

    private Colaborador getColaborador() {
        return this.colaborador;
    }

    @Dado("^o número do CPF é \"([^\"]*)\"$")
    public void oNúmeroDoCPFE(String numeroCpf) throws Throwable {
        getColaborador().setNumeroCPF(numeroCpf);
    }

    @Quando("^realizo o cadastro do colaborador$")
    public void realizoOCadastroDaColaborador() throws Throwable {
        configureUrl("/colaborador");
        try {
            restTemplate.postForLocation(url, getColaborador());
        } catch (Exception e) {
            this.thrownException = e;
        }
    }

    @Então("^o colaborador com nome \"([^\"]*)\" será cadastrado com sucesso$")
    public void oColaboradorComNomeSeraCadastradaComSucesso(String nome) throws Throwable {
        List<Colaborador> colaboradors = findColaboradorByNome(nome);
        assertThat(colaboradors).isNotEmpty();
    }

    private List<Colaborador> findColaboradorByNome(String nome) {
        configureUrl("/colaborador");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("nome", nome);

        return restTemplate.getForObject(builder.build().encode().toUri(), List.class);
    }

    @Dado("^o número do CPF não é informado$")
    public void oNúmeroDoCPFNaoEInformado() throws Throwable {
        getColaborador().setNumeroCPF(null);
    }

    @Então("^a pessoa com nome \"([^\"]*)\" não será cadastrada$")
    public void aPessoaComNomeNaoSeraCadastrada(String nome) throws Throwable {
        List<Colaborador> colaboradors = findColaboradorByNome(nome);
        assertThat(colaboradors).isEmpty();
        //statusCode
    }

    @Então("^será apresentada uma mensagem:$")
    public void seráApresentadaUmaMensagem(String msg) throws Throwable {
        HttpClientErrorException recuperar = (HttpClientErrorException) this.thrownException;
        Gson gson = new Gson();
        InfoException ex = gson.fromJson(recuperar.getResponseBodyAsString(), InfoException.class);
        assertThat(ex.getConstraintViolations()).hasSize(1).containsExactly(msg);
    }

    @Dado("^a data de nascimento não é informada$")
    public void aDataDeNascimentoNaoEInformada() throws Throwable {
        getColaborador().setDataNascimento(null);
    }


    @Dado("^que exista o cargo \"([^\"]*)\" com sigla \"([^\"]*)\"$")
    public void existe_o_cargo_com_sigla(String descricao, String sigla) throws Throwable {
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargo.setSigla(sigla);
        cargoRepository.save(cargo);
    }

    @E("^o cargo é \"([^\"]*)\"$")
    public void o_cargo_é(String sigla) throws Throwable {
        Cargo cargo = cargoRepository.findBySigla(sigla);
        getColaborador().setCargo(cargo);
    }

    @E("^a remuneração é \"([^\"]*)\"$")
    public void a_remuneração_é(BigDecimal valor) throws Throwable {
        getColaborador().setRemuneracao(valor);
    }
}
