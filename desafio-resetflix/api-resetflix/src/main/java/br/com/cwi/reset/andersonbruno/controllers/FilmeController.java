//package br.com.cwi.reset.andersonbruno.controllers;
//
//import br.com.cwi.reset.andersonbruno.FakeDatabase;
//import br.com.cwi.reset.andersonbruno.domain.Filme;
//import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
//import br.com.cwi.reset.andersonbruno.request.FilmeRequest;
//import br.com.cwi.reset.andersonbruno.service.FilmeService;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/filmes")
//public class FilmeController {
//
//    private FilmeService filmeService;
//
//    public FilmeController() {
//        this.filmeService = new FilmeService(FakeDatabase.getInstance());
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void criarFilme(@RequestBody FilmeRequest filmeRequest) throws customExceptions {
//        this.filmeService.criarFilme(filmeRequest);
//    }
//
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<Filme> consultarFilmes(String nomeFilme, String nomeDiretor, String nomePersonagem, String nomeAtor) throws customExceptions {
//        return this.filmeService.consultarFilmes(nomeFilme, nomeDiretor, nomePersonagem, nomeAtor);
//    }
//}
