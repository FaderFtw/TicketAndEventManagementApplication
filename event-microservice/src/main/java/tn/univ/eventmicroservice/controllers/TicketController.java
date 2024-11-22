package tn.univ.eventmicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.univ.eventmicroservice.entities.Ticket;
import tn.univ.eventmicroservice.entities.enumerations.TypeTicket;
import tn.univ.eventmicroservice.services.TicketService;


import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public List<Ticket> ajouterTicketsEtAffecterAEvenementEtInternaute(@RequestBody List<Ticket> tickets, @RequestParam Long idEvenement, @RequestParam Long idInternaute) {
        return ticketService.ajouterTicketsEtAffecterAEvenementEtInternaute(tickets, idEvenement, idInternaute);
    }

    @GetMapping("/event/{nomEvt}/tickets/{typeTicket}/montant")
    public Double montantRecupereParEvtEtTypeTicket(@PathVariable String nomEvt, @PathVariable String typeTicket) {
        return ticketService.montantRecupereParEvtEtTypeTicket(nomEvt, TypeTicket.valueOf(typeTicket));
    }

    @GetMapping("/most-active-internaut")
    public String findMostActiveInternaut() {
        return ticketService.findMostActiveInternaut();
    }
}
