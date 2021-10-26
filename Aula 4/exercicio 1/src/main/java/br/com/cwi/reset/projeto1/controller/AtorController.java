package br.com.cwi.reset.projeto1.controller;

import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.exception.AtorJaExisteException;
import br.com.cwi.reset.projeto1.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ator")
public class AtorController {

    @Autowired
    private AtorService atorService;

    @GetMapping
    public List<Ator> getAtores() {
        return atorService.listarAtores();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ator cadastrarAtor(@RequestBody Ator ator) throws AtorJaExisteException {
        return atorService.cadastrarAtor(ator);
    }


}
