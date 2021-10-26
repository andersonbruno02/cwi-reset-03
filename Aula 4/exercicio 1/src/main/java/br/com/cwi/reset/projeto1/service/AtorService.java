package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.exception.AtorJaExisteException;
import br.com.cwi.reset.projeto1.exception.AtorNaoExisteException;
import br.com.cwi.reset.projeto1.repository.AtorRepositoryBd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtorService {

    @Autowired
    private AtorRepositoryBd repository;

    public Ator cadastrarAtor(Ator ator) throws AtorJaExisteException{
        Ator atorExistente = repository.findByNome(ator.getNome());
        if (atorExistente != null) {
            throw new AtorJaExisteException("Esse Ator já esta cadastrado");
        }
        repository.save(ator);
        return atorExistente;
    }

    public List<Ator> listarAtores() {
        return repository.findAll();
    }

    public Ator buscarPorNome(String nome) throws AtorNaoExisteException {
        Ator ator = repository.findByNome(nome);
        if (ator == null) {
            throw new AtorNaoExisteException("Esse ator não esta cadastrado");
        }
        return ator;
    }

    public void deletar(String nome) throws AtorNaoExisteException{
        Ator ator = buscarPorNome(nome);
        if (ator == null) {
            throw new AtorNaoExisteException("Esse ator não esta cadastrado");
        }
        repository.delete(ator);
    }

    public Ator atualizar(Ator ator) throws AtorNaoExisteException{
        Ator atorVerifica = buscarPorNome(ator.getNome());
        if (atorVerifica == null) {
            throw new AtorNaoExisteException("Ator não cadastrado");
        }
        return repository.save(ator);
    }


}
