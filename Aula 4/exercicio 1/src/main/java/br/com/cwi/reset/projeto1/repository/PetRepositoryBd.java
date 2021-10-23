package br.com.cwi.reset.projeto1.repository;
import br.com.cwi.reset.projeto1.domain.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepositoryBd extends CrudRepository<Pet, Integer> {
    Pet findByNome(String name);
    List<Pet> findAll();
}
