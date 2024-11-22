package tn.univ.eventmicroservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvenement;

    private String nomEvenement;
    private int nbPlacesRestantes;
    private LocalDate dateEvenement;

    @OneToMany(mappedBy = "evenement", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @ManyToMany
    @JoinTable(
            name = "evenement_categories",
            joinColumns = @JoinColumn(name = "evenement_id"),
            inverseJoinColumns = @JoinColumn(name = "categorie_id")
    )
    private List<Categorie> categories;
}

