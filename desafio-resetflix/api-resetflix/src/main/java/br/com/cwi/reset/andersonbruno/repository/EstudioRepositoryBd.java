package br.com.cwi.reset.andersonbruno.repository;

import br.com.cwi.reset.andersonbruno.domain.Estudio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudioRepositoryBd extends CrudRepository<Estudio, Integer> {
    List<Estudio> findAll();
    Estudio findByNome(String nome);

    List<Estudio> findByNomeContains(String nome);
}
