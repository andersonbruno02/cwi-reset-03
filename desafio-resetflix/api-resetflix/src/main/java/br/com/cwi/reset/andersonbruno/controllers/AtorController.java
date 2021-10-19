package br.com.cwi.reset.andersonbruno.controllers;

import br.com.cwi.reset.andersonbruno.FakeDatabase;
import br.com.cwi.reset.andersonbruno.domain.Ator;
import br.com.cwi.reset.andersonbruno.domain.AtorEmAtividade;
import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
import br.com.cwi.reset.andersonbruno.request.AtorRequest;
import br.com.cwi.reset.andersonbruno.service.AtorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atores")
public class AtorController {

    private AtorService atorService;

    public AtorController() {
        this.atorService = new AtorService(FakeDatabase.getInstance());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAtor(@RequestBody AtorRequest atorRequest) throws customExceptions {
        this.atorService.criarAtor(atorRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Ator> consultarAtores() throws customExceptions {
        return this.atorService.consultarAtores();
    }

    @GetMapping("/em_atividade")
    @ResponseStatus(HttpStatus.OK)
    public List<AtorEmAtividade> listarAtoresEmAtividade() throws customExceptions {
        return this.atorService.listarAtoresEmAtividade();
    }

    @GetMapping("/em_atividade/{filtroNome}")
    @ResponseStatus(HttpStatus.OK)
    public List<Ator> listarAtoresEmAtividade(@RequestParam String filtroNome) throws customExceptions {
        return this.atorService.listarAtoresEmAtividade(filtroNome);
    }
}
