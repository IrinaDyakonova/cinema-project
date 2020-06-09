package mate.academy.cinemaproject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloJsonController {


    @GetMapping
    public String say() {
        return "hello";
    }
}
