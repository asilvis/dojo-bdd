package br.com.softplan.ungp.supdev.calculo.controllers;

import br.com.softplan.ungp.supdev.calculo.entity.Colaborador;
import br.com.softplan.ungp.supdev.calculo.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/colaborador")
public class ColaboradorController {

    @Autowired
    private ColaboradorService colaboradorService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void salvar(@RequestBody Colaborador colaborador) {
        colaboradorService.salvar(colaborador);
    }


    @RequestMapping(value = "/lote", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void salvarEmLote(@RequestBody List<Colaborador> colaboradores) {
        colaboradorService.salvarEmLote(colaboradores);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Colaborador> findByNome(@RequestParam("nome") String nome) {
        return colaboradorService.findByNome(nome);
    }
}
