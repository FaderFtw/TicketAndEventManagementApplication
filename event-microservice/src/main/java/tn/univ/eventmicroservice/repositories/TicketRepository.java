package tn.univ.eventmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.univ.eventmicroservice.entities.Ticket;
import tn.univ.eventmicroservice.entities.enumerations.TypeTicket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByEvenementNomEvenementAndTypeTicket(String nomEvenement, TypeTicket typeTicket);

}