package tn.univ.internautemicroservice.controllers;

import org.springframework.web.bind.annotation.*;
import tn.univ.internautemicroservice.entities.Internaute;
import tn.univ.internautemicroservice.services.InternauteService;

@RestController
@RequestMapping("/api/internauts")
public class InternauteController {

    private final InternauteService internauteService;

    public InternauteController(InternauteService internauteService) {
        this.internauteService = internauteService;
    }

    @PostMapping
    public Internaute ajouterInternaute(@RequestBody Internaute internaute) {
        return internauteService.ajouterInternaute(internaute);
    }

    @GetMapping("/most-active")
    public String getMostActiveInternaut() {
        return internauteService.internauteLePlusActif();
    }
}
