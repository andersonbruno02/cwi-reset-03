package br.com.cwi.reset.andersonbruno.repository;

import br.com.cwi.reset.andersonbruno.domain.Ator;
import br.com.cwi.reset.andersonbruno.domain.PersonagemAtor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonagemRepositoryBd extends CrudRepository<PersonagemAtor, Integer> {
    List<PersonagemAtor> findAll();
    List<PersonagemAtor> findByAtor(Ator ator);
}
