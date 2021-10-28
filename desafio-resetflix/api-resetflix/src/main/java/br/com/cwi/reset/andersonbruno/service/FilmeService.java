package br.com.cwi.reset.andersonbruno.service;

import br.com.cwi.reset.andersonbruno.domain.*;
import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
import br.com.cwi.reset.andersonbruno.repository.FilmeRepositoryBd;
import br.com.cwi.reset.andersonbruno.request.FilmeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepositoryBd filmeRepositoryBd;
    @Autowired
    private DiretorService diretorService;
    @Autowired
    private EstudioService estudioService;
    @Autowired
    private PersonagemService personagemService;

    public void criarFilme(FilmeRequest filmeRequest) throws customExceptions {

        final Filme filme = new Filme(
                filmeRequest.getNome(),
                filmeRequest.getAnoLancamento(),
                filmeRequest.getCapaFilme(),
                filmeRequest.getGeneros(),
                estudioService.consultarEstudio(filmeRequest.getIdEstudio()),
                diretorService.consultarDiretor(filmeRequest.getIdDiretor()),
                personagemService.cadastrarPersonagemFilme(filmeRequest.getPersonagens()),
                filmeRequest.getResumo()
        );

        if (filme.getGeneros().isEmpty()) {
            throw new customExceptions("Deve ser informado pelo menos um gênero para o cadastro do filme.");
        }

        final Set<Genero> generoSet = new HashSet<>();
        for (Genero genero : filme.getGeneros()) {
            if (generoSet.contains(genero)) {
                throw new customExceptions("Não é permitido informar o mesmo gênero mais de uma vez para o mesmo filme.");
            } else {
                generoSet.add(genero);
            }
        }
        filmeRepositoryBd.save(filme);
    }

    public List<Filme> consultarFilmes(
            String nomeFilme,
            String nomeDiretor,
            String nomePersonagem,
            String nomeAtor) throws customExceptions {
        List<Filme> filmesCadastrados = filmeRepositoryBd.findAll();
        if (filmesCadastrados.isEmpty()) {
            throw new customExceptions("Nenhum filme cadastrado, favor cadastar filmes.");
        }
        final List<Filme> filtrarNomePersonagem = filtrarNomePersonagem(filmesCadastrados, nomePersonagem);
        final List<Filme> filtrarNomeAtor = filtrarNomeAtor(filtrarNomePersonagem, nomeAtor);
        final List<Filme> filtrarNomeDiretor = filtrarNomeDiretor(filtrarNomeAtor, nomeDiretor);
        final List<Filme> filtroFinal = filtrarNomeFilme(filtrarNomeDiretor, nomeFilme);
        if (filtroFinal.isEmpty()) {
            throw new customExceptions(String.format("Filme não encontrado com os filtros nomeFilme=%s, nomeDiretor=%s, nomePersonagem=%s, nomeAtor=%s, favor informar outros filtros.",
                    nomeFilme,
                    nomeDiretor,
                    nomePersonagem,
                    nomeAtor));
        }
        return filtroFinal;
    }

    private List<Filme> filtrarNomePersonagem(final List<Filme> listaOriginal, final String nome) {
        if (nome == null) {
            return listaOriginal;
        }
        final List<Filme> filmeFiltrado = new ArrayList<>();
        for (Filme filme : listaOriginal) {
            for (PersonagemAtor personagens : filme.getPersonagens()) {
                if (personagens.getNomePersonagem().toLowerCase(Locale.ROOT).contains(nome.toLowerCase(Locale.ROOT))) {
                    filmeFiltrado.add(filme);
                }
            }
        }
        return filmeFiltrado;
    }

    private List<Filme> filtrarNomeAtor(final List<Filme> listaOriginal, final String nome) {
        if (nome == null) {
            return listaOriginal;
        }
        final List<Filme> filmeFiltrado = new ArrayList<>();
        for (Filme filme : listaOriginal) {
            for (PersonagemAtor personagens : filme.getPersonagens()) {
                if (personagens.getAtor().getNome().toLowerCase(Locale.ROOT).contains(nome.toLowerCase(Locale.ROOT))) {
                    filmeFiltrado.add(filme);
                }
            }
        }
        return filmeFiltrado;
    }

    private List<Filme> filtrarNomeDiretor(final List<Filme> listaOriginal, final String nome) {
        if (nome == null) {
            return listaOriginal;
        }
        final List<Filme> filmeFiltrado = new ArrayList<>();
        for (Filme filme : listaOriginal) {
            if (filme.getDiretor().getNome().toLowerCase(Locale.ROOT).contains(nome.toLowerCase(Locale.ROOT))) {
                filmeFiltrado.add(filme);
            }
        }
        return filmeFiltrado;
    }

    private List<Filme> filtrarNomeFilme(final List<Filme> listaOriginal, final String nome) {
        if (nome == null) {
            return listaOriginal;
        }
        final List<Filme> filmeFiltrado = new ArrayList<>();
        for (Filme filme : listaOriginal) {
            if (filme.getNome().toLowerCase(Locale.ROOT).contains(nome.toLowerCase(Locale.ROOT))) {
                filmeFiltrado.add(filme);
            }
        }
        return filmeFiltrado;
    }
}
