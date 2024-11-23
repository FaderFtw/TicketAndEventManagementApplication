package tn.univ.eventmicroservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.univ.eventmicroservice.entities.Categorie;
import tn.univ.eventmicroservice.entities.Evenement;
import tn.univ.eventmicroservice.repositories.CategorieRepository;
import tn.univ.eventmicroservice.repositories.EvenementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EvenementService {

    private static final Logger logger = LoggerFactory.getLogger(EvenementService.class);

    @Autowired
    private EvenementRepository evenementRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    public Evenement ajouterEvenement(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    public List<Evenement> getAllEvenements() {
        return evenementRepository.findAll();
    }

    public Optional<Evenement> getEvenementById(Long id) {
        return evenementRepository.findById(id);
    }

    public Evenement updateEvenement(Long id, Evenement updatedEvent) {
        Optional<Evenement> existingEvent = evenementRepository.findById(id);
        if (existingEvent.isEmpty()) {
            throw new IllegalArgumentException("Event with ID " + id + " not found.");
        }
        updatedEvent.setIdEvenement(id);
        return evenementRepository.save(updatedEvent);
    }

    public void deleteEvenement(Long id) {
        if (!evenementRepository.existsById(id)) {
            throw new IllegalArgumentException("Event with ID " + id + " not found.");
        }
        evenementRepository.deleteById(id);
    }

    @Scheduled(fixedRate = 15000)
    public void listeEvenementsParCategorie() {
        List<Categorie> categories = categorieRepository.findAll();
        if (categories.isEmpty()) {
            logger.info("Aucune catégorie trouvée.");
            return;
        }

        for (Categorie categorie : categories) {
            logger.info("Categorie: {}", categorie.getNomCategorie());
            for (Evenement evenement : categorie.getEvenements()) {
                logger.info("Evenement: {} planifié le {}",
                        evenement.getNomEvenement(),
                        evenement.getDateEvenement());
            }
        }
    }
}
