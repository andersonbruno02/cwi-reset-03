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
            if (diretorRequest.getNome().equals(nomeIgual)) {
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
}
