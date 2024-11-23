package tn.univ.eventmicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import tn.univ.eventmicroservice.dtos.MostActiveInternautDTO;
import tn.univ.eventmicroservice.entities.Ticket;
import tn.univ.eventmicroservice.entities.enumerations.TypeTicket;
import tn.univ.eventmicroservice.services.TicketService;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<?> ajouterTicketsEtAffecterAEvenementEtInternaute(
            @RequestBody List<Ticket> tickets,
            @RequestParam Long idEvenement,
            @RequestParam Long idInternaute) {
        try {
            List<Ticket> createdTickets = ticketService.ajouterTicketsEtAffecterAEvenementEtInternaute(tickets, idEvenement, idInternaute);
            return ResponseEntity.ok(createdTickets);
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }



    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        try {
            return ResponseEntity.ok(ticketService.updateTicket(id, ticket));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        try {
            ticketService.deleteTicket(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/montant")
    public ResponseEntity<Double> montantRecupereParEvtEtTypeTicket(
            @RequestParam String nomEvt,
            @RequestParam String typeTicket) {
        try {
            Double montant = ticketService.montantRecupereParEvtEtTypeTicket(nomEvt, TypeTicket.valueOf(typeTicket));
            return ResponseEntity.ok(montant);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Handle invalid input
        }
    }

    @GetMapping("/most-active-internaut")
    public ResponseEntity<MostActiveInternautDTO> findMostActiveInternaut() {
        MostActiveInternautDTO mostActiveInternaut = ticketService.findMostActiveInternaut();
        if (mostActiveInternaut == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mostActiveInternaut);
    }
}
