package br.com.cwi.reset.andersonbruno.service;

import br.com.cwi.reset.andersonbruno.domain.Ator;
import br.com.cwi.reset.andersonbruno.domain.AtorEmAtividade;
import br.com.cwi.reset.andersonbruno.domain.PersonagemAtor;
import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
import br.com.cwi.reset.andersonbruno.repository.AtorRepositoryBd;
import br.com.cwi.reset.andersonbruno.repository.PersonagemRepositoryBd;
import br.com.cwi.reset.andersonbruno.request.AtorRequest;
import br.com.cwi.reset.andersonbruno.domain.StatusCarreira;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class AtorService {

    @Autowired
    private AtorRepositoryBd repository;
    @Autowired
    private PersonagemRepositoryBd personagemRepositoryBd;

    public Ator criarAtor(AtorRequest atorRequest) throws customExceptions {

        if (!atorRequest.getNome().contains(" ")) {
            throw new customExceptions("Deve ser informado no mínimo nome e sobrenome para o ator.");
        }
        if (atorRequest.getDataNascimento().getYear() > atorRequest.getAnoInicioAtividade()) {
            throw new customExceptions("Ano de início de atividade inválido para o ator cadastrado.");
        }


        Ator atorExistente = repository.findByNome(atorRequest.getNome());
        if (atorExistente != null) {
            throw new customExceptions("Já existe um ator cadastrado para o nome " + atorRequest.getNome());
        }
        Ator novoAtor = new Ator(atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
        return repository.save(novoAtor);
    }

    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) throws customExceptions {
        List<Ator> atores = repository.findAll();

        if (atores.isEmpty()) {
            throw new customExceptions("Nenhum ator cadastrado, favor cadastar atores.");
        }
        final List<AtorEmAtividade> atorFiltroNome = new ArrayList<>();
        if (filtroNome != null) {
            for (Ator ator : atores) {
                if (ator.getStatusCarreira() == StatusCarreira.EM_ATIVIDADE) {
                    if (ator.getNome().toLowerCase(Locale.ROOT).contains(filtroNome.toLowerCase(Locale.ROOT))) {
                        atorFiltroNome.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
                    }
                }
            }
        } else {
            for (Ator ator : atores) {
                if (ator.getStatusCarreira() == StatusCarreira.EM_ATIVIDADE) {
                    atorFiltroNome.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
                }
            }
        }
        if (atorFiltroNome.isEmpty()) {
            throw new customExceptions("Ator não encontrato com o filtro " + StatusCarreira.EM_ATIVIDADE + ", favor informar outro filtro");
        }
        return atorFiltroNome;
    }

    public Ator consultarAtor(Integer id) throws customExceptions {
        if (id == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo Id");
        }
        boolean verificaAtor = repository.existsById(id);
        if (!verificaAtor) {
            throw new customExceptions("Nenhum ator encontrado com o parâmetro id= " + id + ", favor verifique os parâmetros informados.");
        }
        Ator ator = repository.findById(id).get();
        return ator;
    }

    public List<Ator> consultarAtores(String filtroNome) throws customExceptions {
        List<Ator> atores = repository.findAll();
        if (atores.isEmpty()) {
            throw new customExceptions("Nenhum ator cadastrado, favor cadastar atores.");
        }
        if (filtroNome == null) {
            return repository.findAll();
        } else {
            List<Ator> atoresFiltroNome = repository.findByNomeContains(filtroNome);
            if (atoresFiltroNome.isEmpty()) {
                throw new customExceptions("Ator não encontrato com o filtro " + filtroNome + ", favor informar outro filtro");
            }

            return atoresFiltroNome;
        }
    }

    public void atualizarAtor(Integer id, AtorRequest atorRequest) throws customExceptions {
        if (id == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo id");
        }
        boolean verificaAtor = repository.existsById(id);
        if (!verificaAtor) {
            throw new customExceptions("Nenhum ator encontrado com o parâmetro id " + id + " favor verifique os parâmetros informados");
        }
        Ator novoAtor = new Ator(atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
        novoAtor.setId(id);
        repository.save(novoAtor);
    }

    public void removerAtor(Integer id) throws customExceptions {
        if (id == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo id");
        }
        Ator ator = consultarAtor(id);
        List<PersonagemAtor> verificaPersonagens = personagemRepositoryBd.findByAtor(ator);
        if (verificaPersonagens.isEmpty()) {
            ator.setId(ator.getId()-1);
            repository.delete(ator);

        } else {
            throw new customExceptions("Este ator está vinculado a um ou mais personagens, para remover o ator é necessário remover os seus personagens de atuação.");
        }
    }
}