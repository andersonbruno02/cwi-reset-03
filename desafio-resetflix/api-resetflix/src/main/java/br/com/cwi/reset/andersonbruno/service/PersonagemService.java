package br.com.cwi.reset.andersonbruno.service;

import br.com.cwi.reset.andersonbruno.FakeDatabase;
import br.com.cwi.reset.andersonbruno.domain.Ator;
import br.com.cwi.reset.andersonbruno.domain.PersonagemAtor;
import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
import br.com.cwi.reset.andersonbruno.request.PersonagemRequest;

import java.util.List;

public class PersonagemService {

    private Integer id = 0;
    private FakeDatabase fakeDatabase;
    private AtorService atorService;

    public PersonagemService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.atorService = new AtorService(FakeDatabase.getInstance());
    }

    public void criarPersonagem(List<PersonagemRequest> requests) throws customExceptions {

        List<Ator> atores = fakeDatabase.recuperaAtores();

        for (PersonagemRequest request : requests) {
            List<PersonagemAtor> personagens = fakeDatabase.recuperaPersonagens();
            if (request.getNomePersonagem() == null || request.getNomePersonagem().equals("")) {
                throw new customExceptions("Campo obrigatório não informado. Favor informar o campo nomePersonagem");
            }
            if (request.getDescricaoPersonagem() == null || request.getDescricaoPersonagem().equals("")) {
                throw new customExceptions("Campo obrigatório não informado. Favor informar o campo descricaoPersonagem");
            }
            if (request.getIdAtor() == null) {
                throw new customExceptions("Campo obrigatório não informado. Favor informar o campo idAtor");
            }
            if (request.getTipoAtuacao() == null) {
                throw new customExceptions("Campo obrigatório não informado. Favor informar o campo tipoAtuacao");
            }
            if (atorService.consultarAtor(request.getIdAtor()) == null) {
                throw new customExceptions("Nenhum ator encontrado com o parâmetro id=" + request.getIdAtor() + ", favor verifique os parâmetros informados.");
            }
            for (PersonagemAtor personagem : personagens) {
                if (personagem.getIdAtor().equals(request.getIdAtor())) {
                    if (personagem.getNomePersonagem().equals(request.getNomePersonagem())) {
                        throw new customExceptions("Não é permitido informar o mesmo ator/personagem mais de uma vez para o mesmo filme.");
                    }
                }
            }
            
            this.id++;
            PersonagemAtor personagemAtor = new PersonagemAtor(this.id, request.getIdAtor(), request.getNomePersonagem(), request.getDescricaoPersonagem(), request.getTipoAtuacao());
            fakeDatabase.persistePersonagem(personagemAtor);
        }
    }
}
