package pacote.de.exercicios;

public class Diretor {
    private String nome;
    private Integer idade;
    private Integer quantidadeFilmes;
    private Genero genero;

    public Diretor(String nome, Integer idade, Integer quantidadeFilmes, Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.quantidadeFilmes = quantidadeFilmes;
        this.genero = genero;
    }

    public void apresentar() {
        System.out.println("Nome : " + this.nome);
        System.out.println("Idade : " + this.idade + " anos");
        System.out.println("Gênero : " + this.genero.getDescricao());
    }

    public String getNome() {
        return nome;
    }
}
