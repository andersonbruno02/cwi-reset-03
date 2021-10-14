package br.com.cwi.reset.andersonbruno;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Aplicacao {

    public static void main(String[] args) {
        FakeDatabase fakeDatabase = new FakeDatabase();

        AtorService atorService = new AtorService(fakeDatabase);
        DiretorService diretorService = new DiretorService(fakeDatabase);
        //atorService.listarAtoresEmAtividade();

        AtorRequest atorRequest;
        DiretorRequest diretorRequest;
        atorRequest = new AtorRequest("Will Smith", LocalDate.of(1968, Month.SEPTEMBER, 25), StatusCarreira.EM_ATIVIDADE, 1986);
        try {
            atorService.criarAtor(atorRequest);
        } catch (AtorExceptions e) {
            System.out.println(e.getMessage());
        }

        diretorRequest = new DiretorRequest("David Fincher", LocalDate.of(1962, Month.AUGUST, 28), 1984);

        try {
            diretorService.cadastrarDiretor(diretorRequest);
        } catch (AtorExceptions e) {
            System.out.println(e.getMessage());
        }

        atorRequest = new AtorRequest("Maria Lucia", LocalDate.of(2003, Month.APRIL, 12), StatusCarreira.EM_ATIVIDADE, 2020);
        try {
            atorService.criarAtor(atorRequest);
        } catch (AtorExceptions e) {
            System.out.println(e.getMessage());
        }

        atorRequest = new AtorRequest("Maria Clara", LocalDate.of(2001, Month.APRIL, 12), StatusCarreira.EM_ATIVIDADE, 2010);
        try {
            atorService.criarAtor(atorRequest);
        } catch (AtorExceptions e) {
            System.out.println(e.getMessage());
        }

        List<Ator> atores = null;
        try {
            atores = atorService.consultarAtores();
        } catch (AtorExceptions e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Deve conter atores com ids diferentes, quantidade encontrada: " + atores.size());
        System.out.println(atores.get(0).getNome() + " tem id " + atores.get(0).getId());
        System.out.println(atores.get(1).getNome() + " tem id " + atores.get(1).getId());

        try {
            System.out.println(atorService.consultarAtor(4).getNome());
        } catch (AtorExceptions e) {
            System.out.println(e.getMessage());
        }
    }
}