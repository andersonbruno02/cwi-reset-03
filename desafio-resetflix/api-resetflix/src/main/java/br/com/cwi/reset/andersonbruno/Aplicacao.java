package br.com.cwi.reset.andersonbruno;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Aplicacao {

    public static void main(String[] args) {
        FakeDatabase fakeDatabase = new FakeDatabase();

        AtorService atorService = new AtorService(fakeDatabase);
        //atorService.listarAtoresEmAtividade();

        AtorRequest atorRequest;

        atorRequest = new AtorRequest("Will Smith", LocalDate.of(1968, Month.SEPTEMBER, 25), StatusCarreira.EM_ATIVIDADE, 1986);
        try {
            atorService.criarAtor(atorRequest);
        } catch (AtorExceptions e) {
            System.out.println(e.getMessage());
        }


        atorRequest = new AtorRequest("Maria Lucia", LocalDate.of(2003, Month.APRIL, 12), StatusCarreira.EM_ATIVIDADE, 2020);
        try {
            atorService.criarAtor(atorRequest);
        } catch (AtorExceptions e) {
            System.out.println(e.getMessage());
        }

        atorRequest = new AtorRequest("Maria Lucia", LocalDate.of(2001, Month.APRIL, 12), StatusCarreira.EM_ATIVIDADE, 2010);
        try {
            atorService.criarAtor(atorRequest);
        } catch (AtorExceptions e) {
            System.out.println(e.getMessage());
        }

        List<Ator> atores = fakeDatabase.recuperaAtores();

        System.out.println("Deve conter atores com ids diferentes, quantidade encontrada: " + atores.size());
        System.out.println(atores.get(0).getNome() + " tem id " + atores.get(0).getId());
        System.out.println(atores.get(1).getNome() + " tem id " + atores.get(1).getId());

    }
}