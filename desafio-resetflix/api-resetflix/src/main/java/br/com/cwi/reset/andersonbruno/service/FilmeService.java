package br.com.cwi.reset.andersonbruno.service;

import br.com.cwi.reset.andersonbruno.FakeDatabase;
import br.com.cwi.reset.andersonbruno.domain.*;
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

    }
}
