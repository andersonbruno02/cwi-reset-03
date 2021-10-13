package br.com.cwi.reset.andersonbruno;

import java.time.LocalDate;

public class Ator {

    private Integer id = 0;
    private String nome;
    private LocalDate dataNascimento;
    private StatusCarreira statusCarreira;
    private Integer anoInicioAtividade;

    public Ator(String nome, LocalDate dataNascimento, StatusCarreira statusCarreira, Integer anoInicioAtividade) {

        this.id++;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.statusCarreira = statusCarreira;
        this.anoInicioAtividade = anoInicioAtividade;

    }

    public String getNome() {
        return nome;
    }
}
