package br.com.cwi.reset.projeto1.repository;

import br.com.cwi.reset.projeto1.domain.Pet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PetRepositoryImp implements PetRepository {

    private static List<Pet> pets = new ArrayList<>();

    public List<Pet> getPets() {
        return pets;
    }

    public Pet cadastrarPet(Pet pet) {
        pets.add(pet);
        return pet;
    }

    public Pet nomePet(String nome) {
        for (Pet pet : pets) {
            if (pet.getNome().equals(nome)) {
                return pet;
            }
        }
        return null;
    }

    public void deletarPet(Pet pet) {
        pets.remove(pet);
    }

    public Pet atualizar(Pet pet) {
        Pet petAntigo = nomePet(pet.getNome());

        if (petAntigo != null) {
            pets.remove(petAntigo);
            pets.add(pet);
            return pet;
        } else {
            return null;
        }
    }
}

