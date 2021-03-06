package br.com.cwi.reset.andersonbruno.repository;

import br.com.cwi.reset.andersonbruno.domain.Ator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtorRepositoryBd extends CrudRepository<Ator, Integer> {
    List<Ator> findByNomeContains(String nome);

    Ator findByNome(String nome);

    List<Ator> findAll();

}
