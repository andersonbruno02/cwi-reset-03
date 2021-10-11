package pacote.de.exercicios;

import java.time.LocalDate;

public class Aplicacao {
    public static void main(String[] args) {

        Diretor diretor1 = new Diretor("Anderson", LocalDate.parse("1992-02-01"),Genero.NAO_BINARIO,1);
        Ator ator1 = new Ator("Bruno",LocalDate.parse("2001-12-25"),Genero.MASCULINO,3);
        Filme filme = null;
        try {
            filme = new Filme("Macacos me mordam","Um filme sobre macacos mordedores",120,2021,diretor1,1.0);
        } catch (AvaliacaoForaDoPadraoException e) {
            e.printStackTrace();
        }
        filme.reproduzir();

        ator1.apresentar();
        diretor1.apresentar();

    }
}
