package br.com.softplan.ungp.supdev.calculo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.ungp.supdev.calculo.entity.Colaborador;
import br.com.softplan.ungp.supdev.calculo.service.ColaboradorService;

@RestController
@RequestMapping(value = "/colaborador")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void save(@RequestBody Colaborador colaborador) {
        colaboradorService.salvar(colaborador);
    }


    @RequestMapping(value = "/lote", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void saveEmLote(@RequestBody List<Colaborador> colaboradores) {
        colaboradorService.salvarEmLote(colaboradores);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Colaborador> findByNome(@RequestParam("nome") String nome) {
        return colaboradorService.findByNome(nome);
    }
}
