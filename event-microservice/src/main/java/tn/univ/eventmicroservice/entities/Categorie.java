package tn.univ.eventmicroservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codeCategorie;
    private String nomCategorie;

    @ManyToMany(mappedBy = "categories")
    private List<Evenement> evenements;
}