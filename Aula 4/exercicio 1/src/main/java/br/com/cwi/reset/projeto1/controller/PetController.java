package br.com.cwi.reset.projeto1.controller;

import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.PetJaExistenteException;
import br.com.cwi.reset.projeto1.exception.PetNaoExistenteException;
import br.com.cwi.reset.projeto1.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService = new PetService();

    @GetMapping
    public List<Pet> getPet() {
        return petService.listarPets();
    }

    @GetMapping("/{nome}")
    public Pet getByNome(@PathVariable String nome) throws PetNaoExistenteException{
        return petService.buscarPorNome(nome);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet cadastrarPet(@RequestBody Pet pet) throws PetJaExistenteException {
        return petService.cadastrarPet(pet);
    }

    @PutMapping
    public Pet atualizarPet(@RequestBody Pet pet) throws PetNaoExistenteException {
        return petService.atualizar(pet);
    }

    @DeleteMapping("/{nome}")
    public void deletarPet(@PathVariable String nome) throws PetNaoExistenteException{
        petService.deletar(nome);
    }

}
