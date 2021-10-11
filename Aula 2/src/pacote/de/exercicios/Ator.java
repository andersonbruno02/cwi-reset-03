package pacote.de.exercicios;

import java.time.LocalDate;

public class Ator extends Pessoa {

    private Integer numeroDeOscars;

    public Ator(String nome, LocalDate dataNascimento, Genero genero, Integer numeroDeOscars) {
        super(nome, dataNascimento, genero);
        this.numeroDeOscars = numeroDeOscars;
    }
}
