package br.com.cwi.reset.andersonbruno.controllers;


import br.com.cwi.reset.andersonbruno.domain.Estudio;
import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
import br.com.cwi.reset.andersonbruno.request.EstudioRequest;
import br.com.cwi.reset.andersonbruno.service.EstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estudios")
public class EstudioController {

    @Autowired
    private EstudioService estudioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarEstudio(@RequestBody @Valid EstudioRequest estudioRequest) throws customExceptions {
        estudioService.criarEstudio(estudioRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Estudio> consultarEstudios(String filtroNome) throws customExceptions {
        return estudioService.consultarEstudios(filtroNome);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Estudio consultarEstudio(@PathVariable Integer id) throws customExceptions {
        return estudioService.consultarEstudio(id);
    }

}
