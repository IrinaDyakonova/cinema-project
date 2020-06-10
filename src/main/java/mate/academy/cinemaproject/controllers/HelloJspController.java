package mate.academy.cinemaproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloJspController {

    @GetMapping(value = "/")
    public String sayHello() {
        return "hello";
    }

}
