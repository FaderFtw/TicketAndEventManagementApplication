package tn.univ.eventmicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.univ.eventmicroservice.entities.Evenement;
import tn.univ.eventmicroservice.repositories.CategorieRepository;
import tn.univ.eventmicroservice.repositories.EvenementRepository;
import tn.univ.eventmicroservice.services.EvenementService;

import java.util.List;

@RestController
@RequestMapping("/api/evenements")
public class EvenementController {

    @Autowired
    private EvenementService evenementService;

    @PostMapping
    public Evenement addEvenement(@RequestBody Evenement evenement) {
        return evenementService.ajouterEvenement(evenement);
    }

    @GetMapping
    public List<Evenement> getAllEvenements() {
        return evenementService.getAllEvenements();
    }

    @GetMapping("/{id}")
    public Evenement getEvenementById(@PathVariable Long id) {
        return evenementService.getEvenementById(id).orElseThrow(() -> new RuntimeException("Event not found"));
    }

}
