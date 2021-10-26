package br.com.cwi.reset.andersonbruno.controllers;


import br.com.cwi.reset.andersonbruno.domain.Ator;
import br.com.cwi.reset.andersonbruno.domain.AtorEmAtividade;
import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
import br.com.cwi.reset.andersonbruno.request.AtorRequest;
import br.com.cwi.reset.andersonbruno.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atores")
public class AtorController {

    @Autowired
    private AtorService atorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAtor(@RequestBody @Valid AtorRequest atorRequest) throws customExceptions {
       atorService.criarAtor(atorRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Ator> consultarAtores(String filtroNome) throws customExceptions {
        return atorService.consultarAtores(filtroNome);
    }

    @GetMapping("/em_atividade")
    @ResponseStatus(HttpStatus.OK)
    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) throws customExceptions {
        List<AtorEmAtividade> atoresEmAtividade = atorService.listarAtoresEmAtividade(filtroNome);
        return atoresEmAtividade;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Ator> consultarAtor(@PathVariable Integer id) throws customExceptions{
        return atorService.consultarAtor(id);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarAtor(@PathVariable Integer id, @RequestBody @Valid AtorRequest atorRequest) throws customExceptions {
        atorService.atualizarAtor(id,atorRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removerAtor(@PathVariable Integer id) throws customExceptions {
        atorService.removerAtor(id);
    }
}
