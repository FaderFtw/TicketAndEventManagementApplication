package tn.univ.internautemicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.univ.internautemicroservice.clients.EventFeignClient;
import tn.univ.internautemicroservice.dtos.MostActiveInternautDTO;
import tn.univ.internautemicroservice.entities.Internaute;
import tn.univ.internautemicroservice.repositories.InternauteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InternauteService {

    @Autowired
    private InternauteRepository internauteRepository;

    @Autowired
    private EventFeignClient eventFeignClient;

    public Internaute ajouterInternaute(Internaute internaute) {
        return internauteRepository.save(internaute);
    }

    public List<Internaute> getAllInternauts() {
        return internauteRepository.findAll();
    }

    public Optional<Internaute> getInternauteById(Long id) {
        return internauteRepository.findById(id);
    }

    public Internaute updateInternaute(Long id, Internaute updatedInternaute) {
        Optional<Internaute> existingInternaute = internauteRepository.findById(id);
        if (existingInternaute.isEmpty()) {
            throw new IllegalArgumentException("Internaute with ID " + id + " not found.");
        }

        updatedInternaute.setIdInternaute(id);
        return internauteRepository.save(updatedInternaute);
    }

    public void deleteInternaute(Long id) {
        if (!internauteRepository.existsById(id)) {
            throw new IllegalArgumentException("Internaute with ID " + id + " not found.");
        }
        internauteRepository.deleteById(id);
    }

    public String internauteLePlusActif() {
        MostActiveInternautDTO mostActiveInternaut = eventFeignClient.findMostActiveInternaut();
        if (mostActiveInternaut == null) {
            return "No active internaut found";
        }

        Internaute internaute = internauteRepository.findById(mostActiveInternaut.getIdInternaut())
                .orElseThrow(() -> new IllegalArgumentException("Internaut not found with ID: " + mostActiveInternaut.getIdInternaut()));

        return "Internaut: " + internaute.getFullname() +
                " (ID: " + internaute.getIdInternaute() +
                ") has purchased " + mostActiveInternaut.getTicketCount() + " tickets.";
    }
}
