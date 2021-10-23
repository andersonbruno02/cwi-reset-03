package br.com.cwi.reset.andersonbruno.service;

import br.com.cwi.reset.andersonbruno.FakeDatabase;
import br.com.cwi.reset.andersonbruno.domain.*;
import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
import br.com.cwi.reset.andersonbruno.request.FilmeRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FilmeService {

    private Integer id = 0;
    private FakeDatabase fakeDatabase;
    private DiretorService diretorService;
    private AtorService atorService;
    private EstudioService estudioService;
    private PersonagemService personagemService;

    public FilmeService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.diretorService = new DiretorService(FakeDatabase.getInstance());
        this.estudioService = new EstudioService(FakeDatabase.getInstance());
        this.personagemService = new PersonagemService(FakeDatabase.getInstance());
    }

    public void criarFilme(FilmeRequest filmeRequest) throws customExceptions {
        if (filmeRequest.getNome() == null || filmeRequest.getNome().equals("")) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo nome");
        }
        if (filmeRequest.getAnoLancamento() == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo anoLancamento");
        }
        if (filmeRequest.getCapaFilme() == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo capaFilme");
        }
        if (filmeRequest.getGeneros() == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo generos");
        }
        if (filmeRequest.getIdDiretor() == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo idDiretor");
        }
        if (filmeRequest.getIdEstudio() == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo idEstudio");
        }
        if (filmeRequest.getResumo() == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo resumo");
        }
        if (filmeRequest.getPersonagens() == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo personagens");
        }

        diretorService.consultarDiretor(filmeRequest.getIdDiretor());
        personagemService.criarPersonagem(filmeRequest.getPersonagens());
        estudioService.consultarEstudio(filmeRequest.getIdEstudio());
        List<Genero> generosRequest = filmeRequest.getGeneros();
        if (filmeRequest.getGeneros().isEmpty()) {
            throw new customExceptions("Deve ser informado pelo menos um gênero para o cadastro do filme.");
        }
        for (int i = 0; i < generosRequest.size(); i++) {
            Genero genero1 = generosRequest.get(i);
            for (int j = i + 1; j < generosRequest.size(); j++) {
                Genero genero2 = generosRequest.get(j);
                if (genero1.equals(genero2)) {
                    throw new customExceptions("Não é permitido informar o mesmo gênero mais de uma vez para o mesmo filme.");
                }
            }
        }
        this.id++;
        Filme filme = new Filme(this.id, filmeRequest.getNome(), filmeRequest.getAnoLancamento(), filmeRequest.getCapaFilme(), filmeRequest.getGeneros(), filmeRequest.getIdDiretor(), filmeRequest.getIdEstudio(), filmeRequest.getPersonagens(), filmeRequest.getResumo());
        fakeDatabase.persisteFilme(f);

    }

    public List<Filme> consultarFilmes(String nomeFilme, String nomeDiretor, String nomePersonagem, String nomeAtor) throws customExceptions {
        final List<Filme> filmes = fakeDatabase.recuperaFilmes();

        if (filmes.isEmpty()) {
            throw new customExceptions("Nenhum filme cadastrado, favor cadastrar filmes.");
        }

        if (nomeFilme != null) {
            final List<Filme> filtroNome = new ArrayList<>();
            for (Filme filme : filmes) {
                if (filme.getNome().toLowerCase(Locale.ROOT).contains(nomeFilme.toLowerCase(Locale.ROOT))) {
                    filtroNome.add(new Filme(filme.getId(), filme.getNome(), filme.getAnoLancamento(), filme.getCapaFilme(), filme.getGeneros(), filme.getIdDiretor(), filme.getIdDstudio(), filme.getPersonagens(), filme.getResumo()));
                }
            }
            if (filtroNome.isEmpty()) {
                throw new customExceptions("Filme não encontrado com o filtro nomeFilme=" + nomeFilme + ", favor informar outro filtro.");
            }
            return filtroNome;
        }


        if (nomeDiretor != null) {
            final List<Filme> filtroDiretor = new ArrayList<>();
            final List<Diretor> diretorTemporario = diretorService.listarDiretores(nomeDiretor);
            for (Filme filme : filmes) {
                for (Diretor diretor : diretorTemporario) {
                    if (filme.getIdDiretor() == diretor.getId()) {
                        filtroDiretor.add(new Filme(filme.getId(), filme.getNome(), filme.getAnoLancamento(), filme.getCapaFilme(), filme.getGeneros(), filme.getIdDiretor(), filme.getIdDstudio(), filme.getPersonagens(), filme.getResumo()));
                    }
                }
            }
            if (filtroDiretor.isEmpty()) {
                throw new customExceptions("Filme não encontrado com o filtro nomeDiretor=" + nomeDiretor + ", favor informar outro filtro.");
            }
            return filtroDiretor;
        }

        if (nomePersonagem != null) {
            final List<Filme> filtroPersonagem = new ArrayList<>();
            for (Filme filme : filmes) {
                for (PersonagemAtor personagemAtor : filme.getPersonagens()) {
                    if (personagemAtor.getNomePersonagem().toLowerCase(Locale.ROOT).contains(nomePersonagem.toLowerCase(Locale.ROOT))) {
                        filtroPersonagem.add(new Filme(filme.getId(), filme.getNome(), filme.getAnoLancamento(), filme.getCapaFilme(), filme.getGeneros(), filme.getIdDiretor(), filme.getIdDstudio(), filme.getPersonagens(), filme.getResumo()));
                    }
                }
            }
            if (filtroPersonagem.isEmpty()) {
                throw new customExceptions("Filme não encontrado com o filtro nomePersonagem=" + nomePersonagem + ", favor informar outro filtro.");
            }
            return filtroPersonagem;
        }

        if (nomeAtor != null) {
            final List<Filme> filtroAtor = new ArrayList<>();
            final List<Ator> atores = atorService.consultarAtores(nomeAtor);

            for (Filme filme : filmes) {
                for (PersonagemAtor personagemAtor : filme.getPersonagens()) {
                    for (Ator ator : atores) {
                        if (personagemAtor.getIdAtor() == ator.getId()) {
                            filtroAtor.add(new Filme(filme.getId(), filme.getNome(), filme.getAnoLancamento(), filme.getCapaFilme(), filme.getGeneros(), filme.getIdDiretor(), filme.getIdDstudio(), filme.getPersonagens(), filme.getResumo()));
                        }
                    }

                }
            }
            if (filtroAtor.isEmpty()) {
                throw new customExceptions("Filme não encontrado com o filtro nomeAtor=" + nomeAtor + ", favor informar outro filtro.");
            }

            return filtroAtor;
        }


        return filmes;
    }
}
