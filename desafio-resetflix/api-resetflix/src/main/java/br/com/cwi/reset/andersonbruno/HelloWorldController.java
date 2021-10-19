package br.com.cwi.reset.andersonbruno;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

    @GetMapping
    public String helloWorld() throws AtorExceptions {
        throw new AtorExceptions("Mensagem de erro aqui");
     //   return "Minha API resetflix está UP!!!";
    }
}
