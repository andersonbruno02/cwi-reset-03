package br.com.cwi.reset.projeto1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculadoraServiceTest {

    private final CalculadoraService service = new CalculadoraService();

    @Test
    public void quandoMultiplicarDoisNumerosInteirosPositivosDeveRetornarSuaMultiplicacao() {
        // Arrange
        Integer numeroUm = 2;
        Integer numeroDois = 3;
        Integer resultadoEsperado = 6;

        // Action
        Integer resultado = service.multiplicar(numeroUm, numeroDois);

        // Assert
        Assertions.assertEquals(resultadoEsperado, resultado, "O valor esperado deveria ser " + resultadoEsperado + " obtemos o resultado " + resultado);
    }

    @Test
    public void quandoMultiplicarDoisNumerosNegativosDeveRetornarAMultiplicacaoPositiva() {
        // Arrange
        Integer numeroUm = -8;
        Integer numeroDois = -8;
        Integer resultadoEsperado = 64;

        // Action
        Integer resultado = service.multiplicar(numeroUm, numeroDois);

        // Assert
        Assertions.assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void quandoMultiplicarUmNumeroNegativoPorUmNumeroPositivoDeveRetornarAMultiplicacaoNegativa() {
        // Arrange
        Integer numeroUm = -6;
        Integer numeroDois = 6;
        Integer resultadoEsperado = -36;

        // Action
        Integer resultado = service.multiplicar(numeroUm, numeroDois);

        // Assert
        Assertions.assertEquals(resultadoEsperado, resultado);
    }

    @Test
    public void divisaoNumerosInteiros() {
        Integer primeiroNumero = 4;
        Integer segundoNumero = 2;

        Integer resultadoEsperado = 2;

        Integer resultado = service.divisao(primeiroNumero,segundoNumero);
    }

    @Test
    public void divisaoPorZero() {
        Integer primeiroNumero = 4;
        Integer segundoNumero = 0;

        Throwable exception = Assertions.assertThrows(ArithmeticException.class, () -> service.divisao(primeiroNumero,segundoNumero));


    }

    @Test
    public void divisaoNumerosNegativos() {
        Integer primeiroNumero = -4;
        Integer segundoNumero = -2;

        Integer resultadoEsperado = 2;

        Integer resultado = service.divisao(primeiroNumero,segundoNumero);
    }

    @Test
    public void divisaoNumeroNegativoPorPositivo() {
        Integer primeiroNumero = -4;
        Integer segundoNumero = 2;

        Integer resultadoEsperado = -2;

        Integer resultado = service.divisao(primeiroNumero,segundoNumero);
    }

    @Test
    public void divisaoNumeroPositivoPorNegativo() {
        Integer primeiroNumero = 4;
        Integer segundoNumero = -2;

        Integer resultadoEsperado = -2;

        Integer resultado = service.divisao(primeiroNumero,segundoNumero);
    }
}
