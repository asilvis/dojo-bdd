package br.com.softplan.ungp.supdev.calculo.test.cucumber.features;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.softplan.ungp.supdev.calculo.controllers.ColaboradorController;
import br.com.softplan.ungp.supdev.calculo.entity.Cargo;
import br.com.softplan.ungp.supdev.calculo.entity.Colaborador;
import br.com.softplan.ungp.supdev.calculo.exception.BusinessException;
import br.com.softplan.ungp.supdev.calculo.repository.CargoRepository;
import br.com.softplan.ungp.supdev.calculo.repository.ColaboradorRepository;
import cucumber.api.PendingException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class IncluirColaboradorStep {
    
    @Autowired
    private CargoRepository cargoRepository;
    
    private Colaborador colaborador;
    
    @Autowired
    private ColaboradorController colaboradorController;
    
    @Autowired
    private ColaboradorRepository colaboradorRepository;
    
    private String menssagem;

    @Dado("^que exista o cargo de sigla \"([^\"]*)\" e descrição \"([^\"]*)\"$")
    public void queExistaOCargoDeSiglaEDescrição(String sigla, String descricao) throws Throwable {
        Cargo cargo = new Cargo();
        cargo.setSigla(sigla);
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
    }
    
    @Dado("^que existam os cargos:$")
    public void queExistamOsCargos(List<Cargo> cargos) throws Throwable {
        cargoRepository.save(cargos);
    }

    @Dado("^que seja informado o nome \"([^\"]*)\"$")
    public void queSejaInformadoONome(String arg1) throws Throwable {
        colaborador = new Colaborador();
        colaborador.setNome(arg1);
    }

    @Dado("^que seja informado o CPF \"([^\"]*)\"$")
    public void queSejaInformadoOCPF(String arg1) throws Throwable {
        colaborador.setNumeroCPF(arg1);
    }

    @Dado("^que seja informado a data de nascimento \"([^\"]*)\"$")
    public void queSejaInformadoADataDeNascimento(String dataDeNascimento) throws Throwable {
        colaborador.setDataNascimento(dataDeNascimento);
    }

    @Dado("^que seja informado o cargo de sigla \"([^\"]*)\"$")
    public void queSejaInformadoOCargoDeSigla(String arg1) throws Throwable {
        Cargo cargo = cargoRepository.findBySigla(arg1);
        colaborador.setCargo(cargo);
    }

    @Dado("^que seja informado o salário \"([^\"]*)\"$")
    public void queSejaInformadoOSalário(BigDecimal arg1) throws Throwable {
        colaborador.setRemuneracao(arg1);
    }

    @Dado("^que seja informado o número de dependente \"([^\"]*)\"$")
    public void queSejaInformadoONúmeroDeDependente(Integer nuDependentes) throws Throwable {
        colaborador.setDependentes(nuDependentes);
    }

    @Quando("^cadastrar um colaborador$")
    public void cadastrarUmColaborador() throws Throwable {
        
        try {
            colaboradorController.salvar(colaborador);
        } catch (BusinessException e) {
            menssagem = e.getMessage();
        }
    }

    @Então("^o colaborador de nome \"([^\"]*)\" e CPF \"([^\"]*)\" deve ser cadastrado$")
    public void oColaboradorDeNomeDeveSerCadastrado(String arg1, String arg2) throws Throwable {
        List<Colaborador> list = colaboradorRepository.findByNome(arg1);
        Assert.assertFalse(list.isEmpty());
        Assert.assertEquals(arg1, list.get(0).getNome());
        Assert.assertEquals(arg2, list.get(0).getNumeroCPF());
    }
    
    @Então("^o sistema tem que retornar erro informando que o \"([^\"]*)\"$")
    public void oSistemaTemQueRetornarErroInformandoQueO(String arg1) throws Throwable {
        Assert.assertEquals(menssagem, arg1);
    }
    
    @Então("^deve retornar erro \"([^\"]*)\"$")
    public void deveRetornarErro(String arg1) throws Throwable {
        Assert.assertEquals(menssagem, arg1);
    }

}
