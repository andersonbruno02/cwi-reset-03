package pacote.de.exercicios;

public class Aplicacao {
    public static void main(String[] args) {

        Diretor diretor1 = new Diretor("Anderson",29,2);
        Filme filme = new Filme("Macacos me mordam","Um filme sobre macacos mordedores",120,2021,diretor1,5);

        filme.reproduzir();

    }
}
