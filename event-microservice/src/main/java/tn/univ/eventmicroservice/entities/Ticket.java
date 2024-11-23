package tn.univ.eventmicroservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import tn.univ.eventmicroservice.entities.enumerations.TypeTicket;

@Entity
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTicket;

    private String codeTicket;
    private double prixTicket;

    @Enumerated(EnumType.STRING)
    private TypeTicket typeTicket;

    @ManyToOne
    @JoinColumn(name = "evenement_id")
    private Evenement evenement;

    private Long internautId;
}