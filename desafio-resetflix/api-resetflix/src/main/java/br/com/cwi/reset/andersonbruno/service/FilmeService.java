package br.com.cwi.reset.andersonbruno.service;

import br.com.cwi.reset.andersonbruno.FakeDatabase;
import br.com.cwi.reset.andersonbruno.domain.Ator;
import br.com.cwi.reset.andersonbruno.domain.Diretor;
import br.com.cwi.reset.andersonbruno.domain.Filme;
import br.com.cwi.reset.andersonbruno.domain.PersonagemAtor;
import br.com.cwi.reset.andersonbruno.request.PersonagemRequest;
import br.com.cwi.reset.andersonbruno.service.DiretorService;
import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
import br.com.cwi.reset.andersonbruno.request.FilmeRequest;

import java.util.List;

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
        

    }
}
