package br.com.cwi.reset.andersonbruno;

import java.time.LocalDate;
import java.util.List;

public class DiretorService {
    private Integer id=0;
    private FakeDatabase fakeDatabase;

    public DiretorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void cadastrarDiretor(DiretorRequest diretorRequest) throws AtorExceptions {
        if (!diretorRequest.getNome().contains(" ")) {
            throw new AtorExceptions("Deve ser informado no mínimo nome e sobrenome para o diretor.");
        }

        if (diretorRequest.getDataNascimento().isAfter(LocalDate.now())) {
            throw new AtorExceptions("Não é possível cadastrar diretores não nascidos.");
        }

        if (diretorRequest.getDataNascimento().getYear() > diretorRequest.getAnoInicioAtividade()) {
            throw new AtorExceptions("Ano de início de atividade inválido para o diretor cadastrado.");
        }
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        String nomeIgual = diretorRequest.getNome();
        for (Diretor diretor : diretores) {
            if (diretor.getNome().equals(nomeIgual)) {
                throw new AtorExceptions("Já existe um diretor cadastrado para o nome " + nomeIgual);
            }
        }

        if (diretorRequest.getNome() == null || diretorRequest.getNome().equals("")) {
            throw new AtorExceptions("Campo obrigatório não informado. Favor informar o campo Nome");
        }

        if (diretorRequest.getDataNascimento() == null) {
            throw new AtorExceptions("Campo obrigatório não informado. Favor informar o campo Data de Nascimento");
        }

        if (diretorRequest.getAnoInicioAtividade() == null) {
            throw new AtorExceptions("Campo obrigatório não informado. Favor informar o campo AnoInicioAtividade");
        }
        this.id++;
        Diretor diretor = new Diretor(this.id,diretorRequest.getNome(),diretorRequest.getDataNascimento(),diretorRequest.getAnoInicioAtividade());
        fakeDatabase.persisteDiretor(diretor);
    }

    public List<Diretor> listarDiretores(String filtroNome) throws AtorExceptions {
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        List<Diretor> diretorFiltoNome = null;
        if (diretores.isEmpty()) {
            throw new AtorExceptions("Nenhum diretor cadastrado, favor cadastar diretores.");
        } else {
            for (Diretor diretor : diretores) {
                if (diretor.getNome().equals(filtroNome)) {
                    diretorFiltoNome.add(diretor);
                }
            }
            if (diretorFiltoNome.isEmpty()) {
                throw new AtorExceptions("Diretor não encontrato com o filtro " + filtroNome + ", favor informar outro filtro");
            }
        }
        return diretorFiltoNome;
    }

    public List<Diretor> listarDiretores() throws AtorExceptions {
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        if (diretores.isEmpty()) {
            throw new AtorExceptions("Nenhum diretor cadastrado, favor cadastre diretores.");
        }
        return diretores;
    }

    public Diretor consultarDiretor(Integer id) throws AtorExceptions {
        if (id == null) {
            throw new AtorExceptions("Campo obrigatório não informado. Favor informar o campo Id");
        }
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        if (id > diretores.size() || id < 0 ) {
            throw new AtorExceptions("Nenhum diretor encontrado com o parâmetro id= " + id + ", favor verifique os parâmetros informados.");
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
