package br.com.cwi.reset.primeiroprojetospring.controller;

import br.com.cwi.reset.primeiroprojetospring.domain.AvaliacaoForaDoPadraoException;
import br.com.cwi.reset.primeiroprojetospring.domain.Diretor;
import br.com.cwi.reset.primeiroprojetospring.domain.Filme;
import br.com.cwi.reset.primeiroprojetospring.domain.Genero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    Diretor diretor1 = new Diretor("Anderson", LocalDate.parse("1992-02-01"),1,Genero.MASCULINO);

    private static List<Filme> filmes = new ArrayList<>();
    @GetMapping
    public List<Filme> getFilmes() {
        return filmes;
    }

    @GetMapping("/{nome}")
    public Filme getFilmeNome(@PathVariable String nome) {
        Filme filme = buscarFilmePeloNome(nome);
        return filme;
    }

    @PostMapping
    public ResponseEntity<Filme> cadastrarFilme(@RequestBody Filme filme) {
       if (buscarFilmePeloNome(filme.getNome())!= null){
           return ResponseEntity.badRequest().build();
        }
        filmes.add(filme);
        return ResponseEntity.ok(filme);
    }

    @DeleteMapping("/{nome}")
    public void deletarFilme(@PathVariable String nome) {
        Filme filme = buscarFilmePeloNome(nome);
        if (filme != null) {
            filmes.remove(filme);
        }
    }

    @PutMapping
    public void atualizarFilme(@RequestBody Filme filme) {
        Filme filmeCadastrado = buscarFilmePeloNome(filme.getNome());
        if (filmeCadastrado != null) {
            filmes.remove(filmeCadastrado);
            filmes.add(filme);
        }
    }

    private Filme buscarFilmePeloNome(String nome) {
        for (Filme filme : filmes) {
            if (filme.getNome().equals(nome)) {
                return filme;
            }
        }
        return null;
    }
}
