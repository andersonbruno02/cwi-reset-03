package br.com.cwi.reset.andersonbruno.service;

import br.com.cwi.reset.andersonbruno.domain.Ator;
import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
import br.com.cwi.reset.andersonbruno.FakeDatabase;
import br.com.cwi.reset.andersonbruno.request.AtorRequest;
import br.com.cwi.reset.andersonbruno.domain.StatusCarreira;

import java.time.LocalDate;
import java.util.List;

public class AtorService {

    private Integer id = 0;
    private FakeDatabase fakeDatabase;

    public AtorService(FakeDatabase fakeDatabase) {

        this.fakeDatabase = fakeDatabase;
    }

    public void criarAtor(AtorRequest atorRequest) throws customExceptions {

        if (!atorRequest.getNome().contains(" ")) {
            throw new customExceptions("Deve ser informado no mínimo nome e sobrenome para o ator.");
        }

        if (atorRequest.getDataNascimento().isAfter(LocalDate.now())) {
            throw new customExceptions("Não é possível cadastrar atores não nascidos.");
        }

        if (atorRequest.getDataNascimento().getYear() > atorRequest.getAnoInicioAtividade()) {
            throw new customExceptions("Ano de início de atividade inválido para o ator cadastrado.");
        }
        List<Ator> atores = fakeDatabase.recuperaAtores();
        String nomeIgual = atorRequest.getNome();
        for (Ator ator : atores) {
            if (ator.getNome().equals(nomeIgual)) {
                throw new customExceptions("Já existe um ator cadastrado para o nome " + nomeIgual);
            }
        }

        if (atorRequest.getNome() == null || atorRequest.getNome().equals("")) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo Nome");
        }

        if (atorRequest.getDataNascimento() == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo Data de Nascimento");
        }

        if (atorRequest.getAnoInicioAtividade() == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo AnoInicioAtividade");
        }

        if (atorRequest.getStatusCarreira() == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo StatusCarreira");
        }

        this.id++;
        Ator ator = new Ator(this.id, atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
        fakeDatabase.persisteAtor(ator);
    }

    public List<Ator> listarAtoresEmAtividade() throws customExceptions {
        List<Ator> atores = fakeDatabase.recuperaAtores();
        List<Ator> atoresEmAtividade = null;
        if (atores.isEmpty()) {
            throw new customExceptions("Nenhum ator cadastrado, favor cadastar atores.");
        }
        for (Ator ator : atores) {
            if (ator.getStatusCarreira() == StatusCarreira.EM_ATIVIDADE) {
                atoresEmAtividade.add(ator);
            }
        }
        if (atoresEmAtividade.isEmpty()) {
            throw new customExceptions("Ator não encontrato com o filtro " + StatusCarreira.EM_ATIVIDADE + ", favor informar outro filtro");
        }
        return atoresEmAtividade;
    }

    public List<Ator> listarAtoresEmAtividade(String filtroNome) throws customExceptions {
        List<Ator> atores = fakeDatabase.recuperaAtores();
        List<Ator> atoresEmAtividade = null;
        if (atores.isEmpty()) {
            throw new customExceptions("Nenhum ator cadastrado, favor cadastar atores.");
        } else {
            for (Ator ator : atores) {
                if (ator.getStatusCarreira() == StatusCarreira.EM_ATIVIDADE && ator.getNome().equals(filtroNome)) {
                    atoresEmAtividade.add(ator);
                }
            }
            if (atoresEmAtividade.isEmpty()) {
                throw new customExceptions("Ator não encontrato com o filtro " + filtroNome + ", favor informar outro filtro");
            }
        }

        return atoresEmAtividade;
    }

    public Ator consultarAtor(Integer id) throws customExceptions {
        if (id == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo Id");
        }
        List<Ator> atores = fakeDatabase.recuperaAtores();
        if (id > atores.size() || id < 0 ) {
            throw new customExceptions("Nenhum ator encontrado com o parâmetro id= " + id + ", favor verifique os parâmetros informados.");
        }

        Ator atorId = null;
        for (Ator ator : atores) {
            if (ator.getId().equals(id)) {
                atorId = ator;
            }
        }
        return atorId;
    }
    public List<Ator> consultarAtores() throws customExceptions {
        List<Ator> atores = fakeDatabase.recuperaAtores();
        if (atores.isEmpty()) {
            throw new customExceptions("Nenhum ator cadastrado, favor cadastar atores.");
        }
        return atores;
    }
}