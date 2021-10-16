package br.com.cwi.reset.primeiroprojetospring.controller;

import br.com.cwi.reset.primeiroprojetospring.domain.AvaliacaoForaDoPadraoException;
import br.com.cwi.reset.primeiroprojetospring.domain.Diretor;
import br.com.cwi.reset.primeiroprojetospring.domain.Filme;
import br.com.cwi.reset.primeiroprojetospring.domain.Genero;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/filme")

public class FilmeController {

    @GetMapping
    public Filme getFilme() {
        Diretor diretor1 = new Diretor("Anderson", LocalDate.parse("1992-02-01"),1,Genero.MASCULINO);

        Filme filme = null;
        try {
            filme = new Filme("Nome qualquer","Descricao aleatoria",120,1999,5.0,diretor1);
        } catch (AvaliacaoForaDoPadraoException e) {
            e.printStackTrace();
        }
        return filme;
    }
}
