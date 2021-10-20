package br.com.cwi.reset.andersonbruno.controllers;
import br.com.cwi.reset.andersonbruno.domain.Diretor;
import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
import br.com.cwi.reset.andersonbruno.FakeDatabase;
import br.com.cwi.reset.andersonbruno.request.DiretorRequest;
import br.com.cwi.reset.andersonbruno.service.DiretorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    private DiretorService diretorService;

    public DiretorController() {
        this.diretorService = new DiretorService(FakeDatabase.getInstance());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarDiretor(@RequestBody DiretorRequest diretorRequest) throws customExceptions {
        this.diretorService.cadastrarDiretor(diretorRequest);
    }

//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<Diretor> listarDiretores() throws customExceptions{
//        return this.diretorService.listarDiretores();
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Diretor> listarDiretores(String filtroNome) throws customExceptions{
        final List<Diretor> diretores = this.diretorService.listarDiretores(filtroNome);
        return diretores;
    }

}
