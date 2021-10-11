package pacote.de.exercicios;

import java.time.LocalDate;

public class Diretor extends Pessoa {

    private Integer quantidadeFilmes;

    public Diretor(String nome, LocalDate dataNascimento, Genero genero, Integer quantidadeFilmes) {
        super(nome, dataNascimento, genero);
        this.quantidadeFilmes = quantidadeFilmes;
    }
}
