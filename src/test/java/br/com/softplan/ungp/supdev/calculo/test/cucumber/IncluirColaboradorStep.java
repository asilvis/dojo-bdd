package br.com.softplan.ungp.supdev.calculo.test.cucumber;

import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import br.com.softplan.ungp.supdev.calculo.controllers.ColaboradorController;
import br.com.softplan.ungp.supdev.calculo.entity.Cargo;
import br.com.softplan.ungp.supdev.calculo.entity.Colaborador;
import br.com.softplan.ungp.supdev.calculo.repository.CargoRepository;
import br.com.softplan.ungp.supdev.calculo.repository.ColaboradorRepository;
import br.com.softplan.ungp.supdev.calculo.test.configuracao.SpringTest;
import cucumber.api.Format;
import cucumber.api.PendingException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class IncluirColaboradorStep extends SpringTest {
    
    private Exception msgErro;
    
    @Autowired
    private CargoRepository cargoRepository;
    
    @Autowired
    private ColaboradorController colaboradorController;
    
    @Autowired
    private ColaboradorRepository colaboradorRepository;
    
    private Colaborador colaborador;

    @Dado("^que existe o cargo com sigla \"([^\"]*)\" e descricao \"([^\"]*)\"$")
    public void queExisteOCargoComSiglaEDescricao(String sigla, String descricao) throws Throwable {
        Cargo cargo = new Cargo();
        cargo.setSigla(sigla);
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
    }

    @Dado("^um colaborador com nome \"([^\"]*)\"$")
    public void umColaboradorComNome(String nome) throws Throwable {
        colaborador = new Colaborador();
        colaborador.setNome(nome);
    }

    @Dado("^que a data de nascimento é \"([^\"]*)\"$")
    public void queADataDeNascimentoÉ(@Format("dd/MM/yyyy") Date dataDeNascimento) throws Throwable {
        colaborador.setDataNascimento(dataDeNascimento);
    }

    @Quando("^cadastro o colaborador$")
    public void cadastroOColaborador() throws Throwable {
        try{
            colaboradorController.save(colaborador);
        } catch(Exception e){
            this.msgErro = e;
        }
    }

    @Entao("^o colaborador com nome \"([^\"]*)\" será cadastrado com sucesso$")
    public void oColaboradorComNomeSeráCadastradoComSucesso(String nome) throws Throwable {
        List<Colaborador> colaboradores = colaboradorRepository.findByNome(nome);
        Colaborador colaborador2 = colaboradores.get(0);
        org.junit.Assert.assertEquals("Sucesso", nome, colaborador2.getNome());
    }
    
    @Dado("^que o CPF é \"([^\"]*)\"$")
    public void queOCPFÉ(String arg1) throws Throwable {
        colaborador.setNumeroCPF(arg1);
    }

    @Entao("^o colaborador \"([^\"]*)\" não será cadastrado$")
    public void oColaboradorNãoSeráCadastrado(String nome) throws Throwable {
        List<Colaborador> colaboradores = colaboradorRepository.findByNome(nome);
        org.junit.Assert.assertEquals("Sucesso", true, colaboradores.isEmpty());
    }

    @Entao("^deverá apresentar a seguinte mensagem \"([^\"]*)\"$")
    public void deveráApresentarASeguinteMensagem(String msgEsperada) throws Throwable {
        if(msgErro instanceof ConstraintViolationException){
            ConstraintViolationException eCV = (ConstraintViolationException) msgErro;
            String msg = eCV.getConstraintViolations().iterator().next().getMessage();
            org.junit.Assert.assertEquals("Sucesso", msgEsperada, msg);
        }
    }
}
