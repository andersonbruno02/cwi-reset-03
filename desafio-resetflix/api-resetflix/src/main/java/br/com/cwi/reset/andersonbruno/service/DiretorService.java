package br.com.cwi.reset.andersonbruno.service;

import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
import br.com.cwi.reset.andersonbruno.domain.Diretor;
import br.com.cwi.reset.andersonbruno.FakeDatabase;
import br.com.cwi.reset.andersonbruno.request.DiretorRequest;

import java.time.LocalDate;
import java.util.List;

public class DiretorService {
    private Integer id=0;
    private FakeDatabase fakeDatabase;

    public DiretorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void cadastrarDiretor(DiretorRequest diretorRequest) throws customExceptions {
        if (!diretorRequest.getNome().contains(" ")) {
            throw new customExceptions("Deve ser informado no mínimo nome e sobrenome para o diretor.");
        }

        if (diretorRequest.getDataNascimento().isAfter(LocalDate.now())) {
            throw new customExceptions("Não é possível cadastrar diretores não nascidos.");
        }

        if (diretorRequest.getDataNascimento().getYear() > diretorRequest.getAnoInicioAtividade()) {
            throw new customExceptions("Ano de início de atividade inválido para o diretor cadastrado.");
        }
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        String nomeIgual = diretorRequest.getNome();
        for (Diretor diretor : diretores) {
            if (diretor.getNome().equals(nomeIgual)) {
                throw new customExceptions("Já existe um diretor cadastrado para o nome " + nomeIgual);
            }
        }

        if (diretorRequest.getNome() == null || diretorRequest.getNome().equals("")) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo Nome");
        }

        if (diretorRequest.getDataNascimento() == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo Data de Nascimento");
        }

        if (diretorRequest.getAnoInicioAtividade() == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo AnoInicioAtividade");
        }
        this.id++;
        Diretor diretor = new Diretor(this.id,diretorRequest.getNome(),diretorRequest.getDataNascimento(),diretorRequest.getAnoInicioAtividade());
        fakeDatabase.persisteDiretor(diretor);
    }

    public List<Diretor> listarDiretores(String filtroNome) throws customExceptions {
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        List<Diretor> diretorFiltoNome = null;
        if (diretores.isEmpty()) {
            throw new customExceptions("Nenhum diretor cadastrado, favor cadastar diretores.");
        } else {
            for (Diretor diretor : diretores) {
                if (diretor.getNome().equals(filtroNome)) {
                    diretorFiltoNome.add(diretor);
                }
            }
            if (diretorFiltoNome.isEmpty()) {
                throw new customExceptions("Diretor não encontrato com o filtro " + filtroNome + ", favor informar outro filtro");
            }
        }
        return diretorFiltoNome;
    }

    public List<Diretor> listarDiretores() throws customExceptions {
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        if (diretores.isEmpty()) {
            throw new customExceptions("Nenhum diretor cadastrado, favor cadastre diretores.");
        }
        return diretores;
    }

    public Diretor consultarDiretor(Integer id) throws customExceptions {
        if (id == null) {
            throw new customExceptions("Campo obrigatório não informado. Favor informar o campo Id");
        }
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        if (id > diretores.size() || id < 0 ) {
            throw new customExceptions("Nenhum diretor encontrado com o parâmetro id= " + id + ", favor verifique os parâmetros informados.");
        }

        Diretor diretorId = null;
        for (Diretor diretor : diretores) {
            if (diretor.getId().equals(id)) {
                diretorId = diretor;
            }
        }
        return diretorId;
    }
}
