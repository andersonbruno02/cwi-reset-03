package br.com.cwi.reset.andersonbruno.exceptions;

public class CampoNaoInformadoException extends Exception {

    public CampoNaoInformadoException(final String campo) {
        super("Campo obrigatório não informado. Favor informar o campo " + campo);
    }
}
