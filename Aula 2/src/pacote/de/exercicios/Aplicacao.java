package pacote.de.exercicios;

public class Aplicacao {
    public static void main(String[] args) {

        Diretor diretor1 = new Diretor("Anderson",29,Genero.NAO_BINARIO,1);
        Ator ator1 = new Ator("Bruno",18,Genero.MASCULINO,3);
        Filme filme = new Filme("Macacos me mordam","Um filme sobre macacos mordedores",120,2021,diretor1,5.0);
        filme.reproduzir();

        ator1.apresentar();
        diretor1.apresentar();

    }
}
