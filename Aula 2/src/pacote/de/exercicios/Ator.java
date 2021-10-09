package pacote.de.exercicios;

public class Ator extends Pessoa {

    private Integer numeroDeOscars;

    public Ator(String nome, Integer idade, Genero genero, Integer numeroDeOscars) {
        super(nome, idade, genero);
        this.numeroDeOscars = numeroDeOscars;
    }
}
