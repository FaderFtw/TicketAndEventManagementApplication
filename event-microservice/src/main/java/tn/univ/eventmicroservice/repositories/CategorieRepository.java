package tn.univ.eventmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.univ.eventmicroservice.entities.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}