package br.com.cwi.reset.andersonbruno;

import java.time.LocalDate;
import java.util.List;

public class AtorService {

    private Integer id = 0;
    private FakeDatabase fakeDatabase;

    public AtorService(FakeDatabase fakeDatabase) {

        this.fakeDatabase = fakeDatabase;
    }

    public void criarAtor(AtorRequest atorRequest) throws AtorExceptions {

        if (!atorRequest.getNome().contains(" ")) {
            throw new AtorExceptions("Deve ser informado no mínimo nome e sobrenome para o ator.");
        }

        if (atorRequest.getDataNascimento().isAfter(LocalDate.now())) {
            throw new AtorExceptions("Não é possível cadastrar atores não nascidos.");
        }

        if (atorRequest.getDataNascimento().getYear() > atorRequest.getAnoInicioAtividade()) {
            throw new AtorExceptions("Ano de início de atividade inválido para o ator cadastrado.");
        }
        List<Ator> atores = fakeDatabase.recuperaAtores();
        String nomeIgual = atorRequest.getNome();
        for (Ator ator : atores) {
            if (ator.getNome().equals(nomeIgual)) {
                throw new AtorExceptions("Já existe um ator cadastrado para o nome " + nomeIgual);
            }
        }

        if (atorRequest.getNome() == null || atorRequest.getNome().equals("")) {
            throw new AtorExceptions("Campo obrigatório não informado. Favor informar o campo Nome");
        }

        if (atorRequest.getDataNascimento() == null) {
            throw new AtorExceptions("Campo obrigatório não informado. Favor informar o campo Data de Nascimento");
        }

        if (atorRequest.getAnoInicioAtividade() == null) {
            throw new AtorExceptions("Campo obrigatório não informado. Favor informar o campo AnoInicioAtividade");
        }

        if (atorRequest.getStatusCarreira() == null) {
            throw new AtorExceptions("Campo obrigatório não informado. Favor informar o campo StatusCarreira");
        }

        this.id++;
        Ator ator = new Ator(this.id, atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
        fakeDatabase.persisteAtor(ator);
    }
}