package pacote.de.exercicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Aplicacao {
    public static void main(String[] args) {

        Ator ator1 = new Ator("Bruno", LocalDate.parse("2001-12-25"), Genero.MASCULINO, 3);

        //ator1.apresentar();
        //diretor1.apresentar();
        List<Filme> filmes = getListaFilmes();
        for (Filme filme: filmes) {
            filme.reproduzir();
        }


    }

    public static List<Filme> getListaFilmes() {
        Diretor diretor1 = new Diretor("Anderson", LocalDate.parse("1992-02-01"), Genero.NAO_BINARIO, 1);


        List<Filme> listaFilmes = new ArrayList<>();
        Filme filmeUm = null;
        Filme filmeDois = null;
        Filme filmeTres = null;
        Filme filmeQuatro = null;
        try {
            filmeUm = new Filme("Macacos me Mordam", "Um filme sobre macacos mordedores", 120, 2020, diretor1, 5.0);

        } catch (AvaliacaoForaDoPadraoException e) {
            e.printStackTrace();
        }

        try {
            filmeDois = new Filme("Formiga espelhada", "O reflexo de uma trabalhadora", 90, 1992, diretor1, 4.5);

        } catch (AvaliacaoForaDoPadraoException e) {
            e.printStackTrace();
        }

        try {
            filmeTres = new Filme("Dinossauros espaciais", "Empolgante aventura no espaço!", 200, 2005, diretor1, 4.9);

        } catch (AvaliacaoForaDoPadraoException e) {
            e.printStackTrace();
        }

        try {
            filmeQuatro = new Filme("Sol submarino", "Um enigma ou um mistério?", 120, 2020, diretor1, 5.0);

        } catch (AvaliacaoForaDoPadraoException e) {
            e.printStackTrace();
        }

        listaFilmes.add(filmeUm);
        listaFilmes.add(filmeDois);
        listaFilmes.add(filmeTres);
        listaFilmes.add(filmeQuatro);

        return listaFilmes;

    }

}
