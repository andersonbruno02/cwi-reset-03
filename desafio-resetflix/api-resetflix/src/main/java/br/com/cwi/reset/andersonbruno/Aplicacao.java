package br.com.cwi.reset.andersonbruno;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Aplicacao {

    public static void main(String[] args) {
        FakeDatabase fakeDatabase = new FakeDatabase();

        AtorService atorService = new AtorService(fakeDatabase);

        String nome = "Will Smith";
        LocalDate dataNascimento = LocalDate.of(1968, Month.SEPTEMBER, 25);
        StatusCarreira statusCarreira = StatusCarreira.EM_ATIVIDADE;
        Integer anoInicioAtividade = 1986;
        AtorRequest atorRequest = new AtorRequest(nome, dataNascimento, statusCarreira, anoInicioAtividade);

        atorService.criarAtor(atorRequest);
        nome = "João";
        dataNascimento = LocalDate.of(2000, Month.SEPTEMBER, 12);
        statusCarreira = StatusCarreira.EM_ATIVIDADE;
        anoInicioAtividade = 2019;

        atorRequest = new AtorRequest(nome, dataNascimento, statusCarreira, anoInicioAtividade);

        atorService.criarAtor(atorRequest);


        List<Ator> atores = fakeDatabase.recuperaAtores();

        System.out.println("Deve conter atores com ids diferentes, quantidade encontrada: " + atores.size());
        System.out.println(atores.get(0).getNome() + " tem id " + atores.get(0).getId());
        System.out.println(atores.get(1).getNome() + " tem id " + atores.get(1).getId());
    }
}