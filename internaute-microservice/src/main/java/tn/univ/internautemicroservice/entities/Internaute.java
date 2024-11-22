package tn.univ.internautemicroservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import tn.univ.internautemicroservice.entities.enumerations.TrancheAge;

import java.util.List;

@Entity
@Data
public class Internaute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInternaute;

    private String fullname;

    @Enumerated(EnumType.STRING)
    private TrancheAge trancheAge;

}
