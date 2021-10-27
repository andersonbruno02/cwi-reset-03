package br.com.cwi.reset.andersonbruno.controllers;

import br.com.cwi.reset.andersonbruno.domain.Diretor;
import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
import br.com.cwi.reset.andersonbruno.request.DiretorRequest;
import br.com.cwi.reset.andersonbruno.service.DiretorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    @Autowired
    private DiretorService diretorService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarDiretor(@RequestBody @Valid DiretorRequest diretorRequest) throws customExceptions {
        diretorService.cadastrarDiretor(diretorRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Diretor> listarDiretores(String filtroNome) throws customExceptions {
        return diretorService.listarDiretores(filtroNome);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Diretor> consultarDiretor(@PathVariable Integer id) throws customExceptions {
        return diretorService.consultarDiretor(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarDiretor(@PathVariable Integer id, @RequestBody @Valid DiretorRequest diretorRequest) throws customExceptions {
        diretorService.atualizarDiretor(id, diretorRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removerDiretores(@PathVariable Integer id) throws customExceptions {
        diretorService.removerDiretores(id);
    }

}
