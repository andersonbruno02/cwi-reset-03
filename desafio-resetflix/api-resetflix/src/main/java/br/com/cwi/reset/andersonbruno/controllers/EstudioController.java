//package br.com.cwi.reset.andersonbruno.controllers;
//
//import br.com.cwi.reset.andersonbruno.FakeDatabase;
//import br.com.cwi.reset.andersonbruno.domain.Estudio;
//import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
//import br.com.cwi.reset.andersonbruno.request.EstudioRequest;
//import br.com.cwi.reset.andersonbruno.service.EstudioService;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/estudios")
//public class EstudioController {
//
//    private EstudioService estudioService;
//
//    public EstudioController() {
//        this.estudioService = new EstudioService(FakeDatabase.getInstance());
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void criarEstudio(@RequestBody EstudioRequest estudioRequest) throws customExceptions {
//        this.estudioService.criarEstudio(estudioRequest);
//    }
//
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<Estudio> consultarEstudios(String filtroNome) throws customExceptions {
//        return this.estudioService.consultarEstudios(filtroNome);
//    }
//
//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Estudio consultarEstudio(@PathVariable Integer id) throws customExceptions {
//        return this.estudioService.consultarEstudio(id);
//    }
//
//}
