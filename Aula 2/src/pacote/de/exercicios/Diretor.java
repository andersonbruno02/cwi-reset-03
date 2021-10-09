package pacote.de.exercicios;

public class Diretor extends Pessoa {

    private Integer quantidadeFilmes;

    public Diretor(String nome, Integer idade, Genero genero, Integer quantidadeFilmes) {
        super(nome, idade, genero);
        this.quantidadeFilmes = quantidadeFilmes;
    }
}
