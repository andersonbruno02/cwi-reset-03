package pacote.de.exercicios;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class Pessoa {

    private String nome;
    private LocalDate dataNascimento;
    private Genero genero;

    public Pessoa(String nome, LocalDate dataNascimento, Genero genero) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
    }
     public void apresentar() {
         System.out.println("Nome: " + getNome());
         calcularIdade(this.dataNascimento);
         System.out.println("Genero: " + this.genero.getDescricao());
     }

     public void calcularIdade(LocalDate dataNascimento) {
        long tempo = ChronoUnit.YEARS.between(dataNascimento, LocalDate.now());
         System.out.println("Idade: " + tempo + " anos");
     }
    public String getNome() {
        return nome;
    }
}
