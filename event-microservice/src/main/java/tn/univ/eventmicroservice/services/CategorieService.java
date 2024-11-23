package tn.univ.eventmicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.univ.eventmicroservice.entities.Categorie;
import tn.univ.eventmicroservice.repositories.CategorieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    public Categorie addCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Optional<Categorie> getCategorieById(Long id) {
        return categorieRepository.findById(id);
    }

    public Categorie updateCategorie(Long id, Categorie updatedCategorie) {
        Optional<Categorie> existingCategorie = categorieRepository.findById(id);
        if (existingCategorie.isEmpty()) {
            throw new IllegalArgumentException("Categorie with ID " + id + " not found.");
        }
        updatedCategorie.setIdCategorie(id);
        return categorieRepository.save(updatedCategorie);
    }

    public void deleteCategorie(Long id) {
        if (!categorieRepository.existsById(id)) {
            throw new IllegalArgumentException("Categorie with ID " + id + " not found.");
        }
        categorieRepository.deleteById(id);
    }
}
