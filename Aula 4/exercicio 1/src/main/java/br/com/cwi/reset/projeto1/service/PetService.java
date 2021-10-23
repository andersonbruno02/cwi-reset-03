package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.PetJaExistenteException;
import br.com.cwi.reset.projeto1.exception.PetNaoExistenteException;
import br.com.cwi.reset.projeto1.repository.PetRepository;

import java.util.List;

public class PetService {

    private PetRepository repository = new PetRepository();

    public Pet cadastrarPet(Pet pet) throws PetJaExistenteException{
        Pet petJaExistente = repository.nomePet(pet.getNome());
        if (petJaExistente != null) {
            throw new PetJaExistenteException("Pet com o nome " + pet.getNome() + " já existe");
        }
        repository.cadastrarPet(pet);
        return pet;
    }

    public List<Pet> listarPets() {
        return repository.getPets();
    }

    public Pet buscarPorNome(String nome) throws PetNaoExistenteException{
        Pet pet = repository.nomePet(nome);
        if (pet == null) {
            throw new PetNaoExistenteException("Pet com nome " + nome + " não existe");
        }
        return pet;
    }

    public void deletar(String nome) throws PetNaoExistenteException{
        Pet pet = buscarPorNome(nome);
        if (pet == null) {
            throw new PetNaoExistenteException("Pet com nome " + nome + " não existe");
        }
        repository.deletarPet(pet);
    }
    public Pet atualizar(Pet pet) throws PetNaoExistenteException{
        Pet petVerifica = buscarPorNome(pet.getNome());
        if(petVerifica == null) {
            throw new PetNaoExistenteException("Pet com nome " + pet.getNome() + " não existe");
        }
        return repository.atualizar(pet);
    }

}
