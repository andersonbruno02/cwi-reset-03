package br.com.cwi.reset.andersonbruno.service;

import br.com.cwi.reset.andersonbruno.domain.Ator;
import br.com.cwi.reset.andersonbruno.domain.PersonagemAtor;
import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
import br.com.cwi.reset.andersonbruno.repository.PersonagemRepositoryBd;
import br.com.cwi.reset.andersonbruno.request.PersonagemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PersonagemService {

    @Autowired
    private AtorService atorService;
    @Autowired
    private PersonagemRepositoryBd personagemRepositoryBd;

    public PersonagemAtor cadastrarPersonagemAtor(PersonagemRequest personagemRequest) throws customExceptions {

        Ator ator = atorService.consultarAtor(personagemRequest.getIdAtor());
        if (atorService.consultarAtor(personagemRequest.getIdAtor()) == null) {
            throw new customExceptions("Nenhum ator encontrado com o parâmetro id=" + personagemRequest.getIdAtor() + ", favor verifique os parâmetros informados.");
        }

        PersonagemAtor personagemAtor = new PersonagemAtor(ator, personagemRequest.getNomePersonagem(), personagemRequest.getDescricaoPersonagem(), personagemRequest.getTipoAtuacao());
        personagemRepositoryBd.save(personagemAtor);
        return personagemAtor;
    }

    public List<PersonagemAtor> consultarPersonagemAtor(String nome) throws customExceptions {
        return personagemRepositoryBd.findAll();
    }

    public void validarPersonagemAtoresFilme(final List<PersonagemRequest> personagens) throws customExceptions {
        if (personagens.isEmpty()) {
            throw new customExceptions("Informe os personagens");
        }
        final Set<PersonagemRequest> personagemRequestSet = new HashSet<>();

        for (PersonagemRequest personagemRequest : personagens) {
            if (personagemRequestSet.contains(personagemRequest)) {
                throw new customExceptions("Não é permitido informar o mesmo ator/personagem mais de uma vez para o mesmo filme.");
            } else {
                personagemRequestSet.add(personagemRequest);
            }
        }
    }

    public List<PersonagemAtor> cadastrarPersonagemFilme(final List<PersonagemRequest> personagens) throws customExceptions {
        validarPersonagemAtoresFilme(personagens);
        final List<PersonagemAtor> personagemAtores = new ArrayList<>();
        for (PersonagemRequest request : personagens) {
            personagemAtores.add(cadastrarPersonagemAtor(request));
        }
        return personagemAtores;
    }

    public void deletarPersonagens(List<PersonagemAtor> personagensAtor) throws customExceptions{
        if (personagensAtor == null || personagensAtor.isEmpty()) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo personagens");
        }
        for (PersonagemAtor personagem : personagensAtor) {
            personagemRepositoryBd.delete(personagem);
        }
    }
}
