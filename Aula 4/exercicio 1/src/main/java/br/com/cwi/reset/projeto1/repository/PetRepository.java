package br.com.cwi.reset.projeto1.repository;

import br.com.cwi.reset.projeto1.domain.Pet;

import java.util.List;

public interface PetRepository {
    List<Pet> getPets();
    Pet cadastrarPet(Pet pet);
    Pet nomePet(String nome);
    void deletarPet(Pet pet);
    Pet atualizar(Pet pet);
}
