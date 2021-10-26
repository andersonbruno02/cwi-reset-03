package br.com.cwi.reset.andersonbruno.service;

import br.com.cwi.reset.andersonbruno.domain.Ator;
import br.com.cwi.reset.andersonbruno.domain.AtorEmAtividade;
import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
import br.com.cwi.reset.andersonbruno.repository.AtorRepositoryBd;
import br.com.cwi.reset.andersonbruno.request.AtorRequest;
import br.com.cwi.reset.andersonbruno.domain.StatusCarreira;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class AtorService {

    @Autowired
    private AtorRepositoryBd repository;

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

    public Optional<Ator> consultarAtor(Integer id) throws customExceptions {
        if (id == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo Id");
        }
        List<Ator> atores = repository.findAll();
        if (id > atores.size() || id <= 0) {
            throw new customExceptions("Nenhum ator encontrado com o parâmetro id= " + id + ", favor verifique os parâmetros informados.");
        }
        Optional<Ator> ator = repository.findById(id);
        return ator;
    }

    public List<Ator> consultarAtores(String filtroNome) throws customExceptions {
        if (filtroNome == null) {
            return repository.findAll();
        } else {
            List<Ator> atores = repository.findByNomeContains(filtroNome);
            if (atores.isEmpty()) {
                throw new customExceptions("Nenhum ator cadastrado, favor cadastar atores.");
            }

            return atores;
        }
    }

    public void atualizarAtor(Integer id, AtorRequest atorRequest) throws customExceptions {
        if (id == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo id");
        }
        Optional<Ator> ator = repository.findById(id);
        if (ator == null) {
            throw new customExceptions("Nenhum ator encontrado com o parâmetro id " + id + " favor verifique os parâmetros informados");
        }
        Ator novoAtor = new Ator(atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
        novoAtor.setId(id);
        repository.save(novoAtor);
    }
    public void removerAtor(Integer id) throws customExceptions{
        if (id == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo id");
        }
        Optional<Ator> ator = consultarAtor(id);
        Ator atorDeletado = ator.get();
        repository.delete(atorDeletado);
    }
}