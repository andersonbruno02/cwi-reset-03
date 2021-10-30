package br.com.cwi.reset.projeto1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercicios1Test {

    private final Exercicios1 service = new Exercicios1();

    @Test
    public void somarListaCincoInteiros() {
        //Arange
        List<Integer> numeros = new ArrayList();
        numeros.add(5);
        numeros.add(1);
        numeros.add(2);
        numeros.add(3);
        numeros.add(4);
        Integer resultadoEsperado = 15;

        //Action
        Integer resultado = service.somarLista(numeros);

        //Assert
        Assertions.assertEquals(resultadoEsperado, resultado);

    }

    @Test
    public void somarListaQuatroInteirosUmNegativo() {
        List<Integer> numeros = new ArrayList();
        numeros.add(5);
        numeros.add(1);
        numeros.add(2);
        numeros.add(3);
        numeros.add(-4);
        Integer resultadoEsperado = 7;

        Integer resultado = service.somarLista(numeros);

        Assertions.assertEquals(resultadoEsperado, resultado);

    }

    @Test
    public void somaTresNegativos() {
        List<Integer> numeros = new ArrayList();
        numeros.add(-1);
        numeros.add(-1);
        numeros.add(-1);

        Integer resultadoEsperado = -3;

        Integer resultado = service.somarLista(numeros);

        Assertions.assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void somaTudoZero() {
        List<Integer> numeros = new ArrayList();
        numeros.add(0);
        numeros.add(0);
        numeros.add(0);
        numeros.add(0);
        numeros.add(0);
        Integer resultadoEsperado = 0;

        Integer resultado = service.somarLista(numeros);

        Assertions.assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void somaListaVazia() {
        List<Integer> numeros = new ArrayList();

        Integer resultadoEsperado = 0;

        Integer resultado = service.somarLista(numeros);

        Assertions.assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void mediaCincoNumerosInteiros() {
        List<Integer> numeros = new ArrayList();
        numeros.add(5);
        numeros.add(5);
        numeros.add(5);
        numeros.add(5);
        numeros.add(5);
        Double resultadoEsperado = 5.0;

        Double resultado = service.calcularMedia(numeros);

        Assertions.assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void mediaQuatroInteirosUmNegativo() {
        List<Integer> numeros = new ArrayList();
        numeros.add(5);
        numeros.add(5);
        numeros.add(5);
        numeros.add(5);
        numeros.add(-5);
        Double resultadoEsperado = 3.0;

        Double resultado = service.calcularMedia(numeros);

        Assertions.assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void mediaComTresNegativos() {
        List<Integer> numeros = new ArrayList();
        numeros.add(-5);
        numeros.add(-5);
        numeros.add(-5);

        Double resultadoEsperado = -5.0;

        Double resultado = service.calcularMedia(numeros);

        Assertions.assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void mediaComTodosZero() {
        List<Integer> numeros = new ArrayList();
        numeros.add(0);
        numeros.add(0);
        numeros.add(0);
        numeros.add(0);
        numeros.add(0);
        Double resultadoEsperado = 0.0;

        Double resultado = service.calcularMedia(numeros);

        Assertions.assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void mediaComListaVazia() {
        List<Integer> numeros = new ArrayList();

        Double resultadoEsperado = 0.0;

        Double resultado = service.calcularMedia(numeros);

        Assertions.assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void invertePalavraAbacate() {
        String palavra = "Abacate";
        String resultadoEsperado = "etacabA";

        String resultado = service.obterPalavraInvertida(palavra);

        Assertions.assertEquals(resultadoEsperado,resultado);
    }
    @Test
    public void invertePalavraBanana() {
        String palavra = "Banana";
        String resultadoEsperado = "ananaB";

        String resultado = service.obterPalavraInvertida(palavra);

        Assertions.assertEquals(resultadoEsperado,resultado);
    }

    @Test
    public void invertePalavraPessego() {
        String palavra = "Pessego";
        String resultadoEsperado = "ogesseP";

        String resultado = service.obterPalavraInvertida(palavra);

        Assertions.assertEquals(resultadoEsperado,resultado);
    }

    @Test
    public void invertePalavraMorango() {
        String palavra = "Morango";
        String resultadoEsperado = "ognaroM";

        String resultado = service.obterPalavraInvertida(palavra);

        Assertions.assertEquals(resultadoEsperado,resultado);
    }

    @Test
    public void listaOrdenadaInteiros() {
        List<Integer> numeros = new ArrayList<>();
        numeros.add(2);
        numeros.add(1);
        numeros.add(3);
        numeros.add(4);

        List<Integer> resultadoEsperado = Arrays.asList(1,2,3,4);

        List<Integer> resultado = service.ordenarLista(numeros);

        Assertions.assertEquals(resultadoEsperado,resultado);
    }

    @Test
    public void listaOrdenadaInteirosRepetidos() {
        List<Integer> numeros = new ArrayList<>();
        numeros.add(2);
        numeros.add(1);
        numeros.add(2);
        numeros.add(4);

        List<Integer> resultadoEsperado = Arrays.asList(1,2,2,4);

        List<Integer> resultado = service.ordenarLista(numeros);

        Assertions.assertEquals(resultadoEsperado,resultado);
    }

    @Test
    public void listaOrdenadaInteirosComNegativo() {
        List<Integer> numeros = new ArrayList<>();
        numeros.add(-2);
        numeros.add(1);
        numeros.add(3);
        numeros.add(4);

        List<Integer> resultadoEsperado = Arrays.asList(-2,1,3,4);

        List<Integer> resultado = service.ordenarLista(numeros);

        Assertions.assertEquals(resultadoEsperado,resultado);
    }

    @Test
    public void listaOrdenadaVazia() {
        List<Integer> numeros = new ArrayList<>();

        List<Integer> resultadoEsperado = new ArrayList<>();

        List<Integer> resultado = service.ordenarLista(numeros);

        Assertions.assertEquals(resultadoEsperado,resultado);
    }

    @Test
    public void listaOrdenadaTudoZero() {
        List<Integer> numeros = new ArrayList<>();
        numeros.add(0);
        numeros.add(0);
        numeros.add(0);
        numeros.add(0);

        List<Integer> resultadoEsperado = Arrays.asList(0,0,0,0);

        List<Integer> resultado = service.ordenarLista(numeros);

        Assertions.assertEquals(resultadoEsperado,resultado);
    }

}
