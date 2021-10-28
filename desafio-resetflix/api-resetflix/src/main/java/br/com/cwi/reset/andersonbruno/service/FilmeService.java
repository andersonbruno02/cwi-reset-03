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

//    public List<Filme> consultarFilmes(String nomeFilme, String nomeDiretor, String nomePersonagem, String nomeAtor) throws customExceptions {
//        final List<Filme> filmes = fakeDatabase.recuperaFilmes();
//
//        if (filmes.isEmpty()) {
//            throw new customExceptions("Nenhum filme cadastrado, favor cadastrar filmes.");
//        }
//
//        if (nomeFilme != null) {
//            final List<Filme> filtroNome = new ArrayList<>();
//            for (Filme filme : filmes) {
//                if (filme.getNome().toLowerCase(Locale.ROOT).contains(nomeFilme.toLowerCase(Locale.ROOT))) {
//                    filtroNome.add(new Filme(filme.getId(), filme.getNome(), filme.getAnoLancamento(), filme.getCapaFilme(), filme.getGeneros(), filme.getIdDiretor(), filme.getIdDstudio(), filme.getPersonagens(), filme.getResumo()));
//                }
//            }
//            if (filtroNome.isEmpty()) {
//                throw new customExceptions("Filme não encontrado com o filtro nomeFilme=" + nomeFilme + ", favor informar outro filtro.");
//            }
//            return filtroNome;
//        }
//
//
//        if (nomeDiretor != null) {
//            final List<Filme> filtroDiretor = new ArrayList<>();
//            final List<Diretor> diretorTemporario = diretorService.listarDiretores(nomeDiretor);
//            for (Filme filme : filmes) {
//                for (Diretor diretor : diretorTemporario) {
//                    if (filme.getIdDiretor() == diretor.getId()) {
//                        filtroDiretor.add(new Filme(filme.getId(), filme.getNome(), filme.getAnoLancamento(), filme.getCapaFilme(), filme.getGeneros(), filme.getIdDiretor(), filme.getIdDstudio(), filme.getPersonagens(), filme.getResumo()));
//                    }
//                }
//            }
//            if (filtroDiretor.isEmpty()) {
//                throw new customExceptions("Filme não encontrado com o filtro nomeDiretor=" + nomeDiretor + ", favor informar outro filtro.");
//            }
//            return filtroDiretor;
//        }
//
//        if (nomePersonagem != null) {
//            final List<Filme> filtroPersonagem = new ArrayList<>();
//            for (Filme filme : filmes) {
//                for (PersonagemAtor personagemAtor : filme.getPersonagens()) {
//                    if (personagemAtor.getNomePersonagem().toLowerCase(Locale.ROOT).contains(nomePersonagem.toLowerCase(Locale.ROOT))) {
//                        filtroPersonagem.add(new Filme(filme.getId(), filme.getNome(), filme.getAnoLancamento(), filme.getCapaFilme(), filme.getGeneros(), filme.getIdDiretor(), filme.getIdDstudio(), filme.getPersonagens(), filme.getResumo()));
//                    }
//                }
//            }
//            if (filtroPersonagem.isEmpty()) {
//                throw new customExceptions("Filme não encontrado com o filtro nomePersonagem=" + nomePersonagem + ", favor informar outro filtro.");
//            }
//            return filtroPersonagem;
//        }
//
//        if (nomeAtor != null) {
//            final List<Filme> filtroAtor = new ArrayList<>();
//            final List<Ator> atores = atorService.consultarAtores(nomeAtor);
//
//            for (Filme filme : filmes) {
//                for (PersonagemAtor personagemAtor : filme.getPersonagens()) {
//                    for (Ator ator : atores) {
//                        if (personagemAtor.getIdAtor() == ator.getId()) {
//                            filtroAtor.add(new Filme(filme.getId(), filme.getNome(), filme.getAnoLancamento(), filme.getCapaFilme(), filme.getGeneros(), filme.getIdDiretor(), filme.getIdDstudio(), filme.getPersonagens(), filme.getResumo()));
//                        }
//                    }
//
//                }
//            }
//            if (filtroAtor.isEmpty()) {
//                throw new customExceptions("Filme não encontrado com o filtro nomeAtor=" + nomeAtor + ", favor informar outro filtro.");
//            }
//
//            return filtroAtor;
//        }
//
//
//        return filmes;
//    }
}
