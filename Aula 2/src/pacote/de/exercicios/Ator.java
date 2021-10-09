package pacote.de.exercicios;

public class Ator {
    private String nome;
    private Integer idade;
    private Integer numeroDeOscars;
    private Genero genero;

    public Ator(String nome, Integer idade, Integer numeroDeOscars, Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.numeroDeOscars = numeroDeOscars;
        this.genero = genero;
    }
    public void apresentar() {
        System.out.println("Nome : " + this.nome);
        System.out.println("Idade : " + this.idade + " anos");
        System.out.println("Gênero : " + this.genero.getDescricao());
    }
}
