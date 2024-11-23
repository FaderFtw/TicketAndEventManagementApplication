package tn.univ.internautemicroservice.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.univ.internautemicroservice.entities.Internaute;
import tn.univ.internautemicroservice.services.InternauteService;

import java.util.List;

@RestController
@RequestMapping("/api/internauts")
public class InternauteController {

    private final InternauteService internauteService;

    public InternauteController(InternauteService internauteService) {
        this.internauteService = internauteService;
    }

    @PostMapping
    public ResponseEntity<Internaute> ajouterInternaute(@RequestBody Internaute internaute) {
        Internaute createdInternaute = internauteService.ajouterInternaute(internaute);
        return ResponseEntity.ok(createdInternaute);
    }

    @GetMapping
    public ResponseEntity<List<Internaute>> getAllInternauts() {
        return ResponseEntity.ok(internauteService.getAllInternauts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Internaute> getInternauteById(@PathVariable Long id) {
        return internauteService.getInternauteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Internaute> updateInternaute(@PathVariable Long id, @RequestBody Internaute internaute) {
        try {
            Internaute updatedInternaute = internauteService.updateInternaute(id, internaute);
            return ResponseEntity.ok(updatedInternaute);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInternaute(@PathVariable Long id) {
        try {
            internauteService.deleteInternaute(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/most-active")
    @Retry(name = "getMostActiveInternautRetry", fallbackMethod = "fallbackMostActiveInternaut")
    @RateLimiter(name = "getMostActiveInternautRateLimiter", fallbackMethod = "fallbackMostActiveInternaut")
    @CircuitBreaker(name = "getMostActiveInternautCircuitBreaker", fallbackMethod = "fallbackMostActiveInternaut")
    public ResponseEntity<String> getMostActiveInternaut() {
        return ResponseEntity.ok(internauteService.internauteLePlusActif());
    }

    public ResponseEntity<String> fallbackMostActiveInternaut(Exception e) {
        return ResponseEntity.ok("Event Service is currently unavailable. Please try again later.");
    }
}
