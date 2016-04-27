package br.com.softplan.ungp.supdev.calculo.test.cucumber;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.softplan.ungp.supdev.calculo.controllers.ColaboradorController;
import br.com.softplan.ungp.supdev.calculo.entity.Colaborador;
import br.com.softplan.ungp.supdev.calculo.test.configuracao.SpringTest;
import cucumber.api.Format;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class IncluirColaboradorEmLoteStep extends SpringTest {
    
    @Autowired
    private ColaboradorController colaboradorController;
    
    private List<Colaborador> colaboradores;

    @Dado("^os seguintes colaboradores:$")
    public void osSeguintesColaboradores(@Format("dd/MM/yyyy") List<Colaborador> colaboradores) throws Throwable {
        this.colaboradores = colaboradores;
    }

    @Quando("^realizo o cadastro de colaboradores em lote$")
    public void realizoOCadastroDeColaboradoresEmLote() throws Throwable {
        colaboradorController.saveEmLote(colaboradores);
    }
    
    @Entao("^o colaborador com nome \"([^\"]*)\" será cadastrado com sucesso$")
    public void oColaboradorComNomeSeráCadastradoComSucesso(List<String> nome) throws Throwable {
        List<Colaborador> colaboradores = colaboradorRepository.findByNome(nome);
        Colaborador colaborador2 = colaboradores.get(0);
        org.junit.Assert.assertEquals("Sucesso", nome, colaborador2.getNome());
    }
    
}
