package tn.univ.eventmicroservice.services;

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

    @Scheduled(fixedRate = 15000)
    public void listeEvenementsParCategorie() {
        List<Categorie> categories = categorieRepository.findAll();
        for (Categorie categorie : categories) {
            System.out.println("Categorie " + categorie.getNomCategorie() + ":");
            for (Evenement evenement : categorie.getEvenements()) {
                System.out.println("Evenement " + evenement.getNomEvenement() + " planifié le " + evenement.getDateEvenement());
            }
        }
    }

}
