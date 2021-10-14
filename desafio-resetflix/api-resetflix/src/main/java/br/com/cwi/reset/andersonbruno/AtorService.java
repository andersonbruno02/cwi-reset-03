package br.com.cwi.reset.andersonbruno;

import java.time.LocalDate;

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
            throw  new AtorExceptions("Não é possível cadastrar atores não nascidos.");
        }

        this.id++;
        Ator ator = new Ator(this.id, atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
        fakeDatabase.persisteAtor(ator);
    }
}