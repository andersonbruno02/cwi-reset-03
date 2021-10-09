package pacote.de.exercicios;

public class Pessoa {

    private String nome;
    private Integer idade;
    private Genero genero;

    public Pessoa(String nome, Integer idade, Genero genero) {
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
    }
     public void apresentar() {
         System.out.println("Nome: " + getNome());
         System.out.println("Idade: " + this.idade);
         System.out.println("Genero: " + this.genero.getDescricao());
     }

    public String getNome() {
        return nome;
    }
}
