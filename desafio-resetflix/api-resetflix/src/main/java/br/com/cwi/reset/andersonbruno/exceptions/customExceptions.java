package br.com.cwi.reset.andersonbruno.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class customExceptions extends Exception{
    public customExceptions(String mensagem) {
        super(mensagem);
    }
}
