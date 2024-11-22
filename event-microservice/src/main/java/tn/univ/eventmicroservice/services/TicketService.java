package tn.univ.eventmicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.univ.eventmicroservice.entities.Evenement;
import tn.univ.eventmicroservice.entities.Ticket;
import tn.univ.eventmicroservice.entities.enumerations.TypeTicket;
import tn.univ.eventmicroservice.repositories.EvenementRepository;
import tn.univ.eventmicroservice.repositories.TicketRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private EvenementRepository evenementRepository;


    public List<Ticket> ajouterTicketsEtAffecterAEvenementEtInternaute(List<Ticket> tickets, Long idEvenement, Long idInternaute) {
        Evenement evenement = evenementRepository.findById(idEvenement).orElseThrow(() -> new IllegalArgumentException("Événement non trouvé"));

        int ticketsToAdd = tickets.size();
        if (evenement.getNbPlacesRestantes() < ticketsToAdd) {
            throw new UnsupportedOperationException("nombre de places demandées indisponible");
        }

        evenement.setNbPlacesRestantes(evenement.getNbPlacesRestantes() - ticketsToAdd);
        evenementRepository.save(evenement);

        for (Ticket ticket : tickets) {
            ticket.setEvenement(evenement);
            ticket.setInternautId(idInternaute);
            ticketRepository.save(ticket);
        }

        return tickets;
    }

    public Double montantRecupereParEvtEtTypeTicket(String nomEvt, TypeTicket typeTicket) {
        List<Ticket> tickets = ticketRepository.findByEvenementNomEvenementAndTypeTicket(nomEvt, typeTicket);
        return tickets.stream().mapToDouble(Ticket::getPrixTicket).sum();
    }

    public String findMostActiveInternaut() {
        Map<Long, Long> ticketCounts = ticketRepository.findAll().stream()
                .collect(Collectors.groupingBy(Ticket::getInternautId, Collectors.counting()));

        return ticketCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> "Internaut ID: " + entry.getKey())
                .orElse("No active internaut found");
    }
}
