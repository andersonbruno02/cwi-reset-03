package br.com.cwi.reset.andersonbruno.repository;

import br.com.cwi.reset.andersonbruno.domain.Diretor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiretorRepositoryBd extends CrudRepository<Diretor, Integer> {
    List<Diretor> findByNomeContains(String nome);

    Diretor findByNome(String nome);

    List<Diretor> findAll();

}
