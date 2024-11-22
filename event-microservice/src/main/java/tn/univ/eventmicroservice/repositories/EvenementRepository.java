package tn.univ.eventmicroservice.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import tn.univ.eventmicroservice.entities.Evenement;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {
}