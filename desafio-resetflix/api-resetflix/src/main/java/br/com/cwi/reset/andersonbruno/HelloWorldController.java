package br.com.cwi.reset.andersonbruno;

import br.com.cwi.reset.andersonbruno.exceptions.customExceptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

    @GetMapping
    public String helloWorld() throws customExceptions {
       // throw new AtorExceptions("Mensagem de erro aqui");
      return "Minha API resetflix está UP!!!";
    }
}
