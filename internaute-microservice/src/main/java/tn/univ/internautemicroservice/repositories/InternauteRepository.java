package tn.univ.internautemicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.univ.internautemicroservice.entities.Internaute;

import java.util.Optional;

public interface InternauteRepository extends JpaRepository<Internaute, Long> {
}
