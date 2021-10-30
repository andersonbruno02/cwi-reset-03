package br.com.cwi.reset.projeto1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercicios1 {

    public Integer somarLista(List<Integer> numeros) {
        int total = 0;
        for (int numero : numeros) {
            total = total + numero;
        }
        return total;
    }

    public Double calcularMedia(List<Integer> numeros) {
        double resultado = 0.0;
        if (numeros.isEmpty()) {
            return 0.0;
        } else {

            for (double numero : numeros) {
                resultado += numero;
            }
            resultado = resultado / numeros.size();
        }
        return resultado;
    }

    public Integer obterMaiorNumero(List<Integer> numeros) {
        int maior = 0;
        for (int numero : numeros) {
            if (numero > maior) {
                maior = numero;
            }
        }
        return maior;
    }

    public String obterPalavraInvertida(String palavra) {
        String invertida = "";
        for (int i = palavra.length() - 1; i >= 0; i--) {
            invertida += palavra.charAt(i);
        }
        return invertida;
    }

    public List<Integer> ordenarLista(List<Integer> numeros) {
        Integer[] ordenado = numeros.toArray(new Integer[numeros.size()]);
        Integer aux;
        for (int i = 0; i < numeros.size() -1; i++) {
            for (int j = i +1; j < numeros.size(); j++) {
                if (ordenado[j] < ordenado[i]) {
                    aux = ordenado[j];
                    ordenado[j] = ordenado[i];
                    ordenado[i] = aux;
                }
        }
    }
            return Arrays.asList(ordenado);
}
}

