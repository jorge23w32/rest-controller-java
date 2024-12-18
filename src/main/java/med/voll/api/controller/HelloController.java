package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//Indicar que es un controller
@RequestMapping("/hello")//Crea una nueva pestana en el servidor we
public class HelloController {
    @GetMapping//Se le indica el metodo que le corresponde a la pestana ./hello
    public String helloWorld(){
        return "Hello World from IntelliJ";
    }
}
