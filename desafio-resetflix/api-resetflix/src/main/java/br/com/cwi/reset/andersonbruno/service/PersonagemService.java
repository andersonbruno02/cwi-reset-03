package br.com.cwi.reset.andersonbruno.service;

import br.com.cwi.reset.andersonbruno.FakeDatabase;
import br.com.cwi.reset.andersonbruno.domain.Ator;
import br.com.cwi.reset.andersonbruno.domain.PersonagemAtor;
import br.com.cwi.reset.andersonbruno.request.PersonagemRequest;

import java.util.ArrayList;
import java.util.List;

public class PersonagemService {

    private Integer id = 0;
    private FakeDatabase fakeDatabase;
    private AtorService atorService;

    public PersonagemService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.atorService = new AtorService(FakeDatabase.getInstance());
    }

    public void criarPersonagem(PersonagemRequest personagemRequests) {
        List<PersonagemAtor> personagens = fakeDatabase.recuperaPersonagens();
        List<Ator> atores = fakeDatabase.recuperaAtores();
        this.id++;
        PersonagemAtor personagemAtor = new PersonagemAtor(this.id,personagemRequests.getIdAtor(), personagemRequests.getNomePersonagem(), personagemRequests.getDescricaoPersonagem(), personagemRequests.getTipoAtuacao());
        fakeDatabase.persistePersonagem(personagemAtor);
    }

}
