package tn.univ.eventmicroservice.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class WelcomeController {
    // a get mapping to print a welcome message with the instance port
    @Value("${server.port}")
    private String port;

    @GetMapping
    public String welcome() {
        return "Welcome to the Event Microservice, running on port " + port;
    }

}
