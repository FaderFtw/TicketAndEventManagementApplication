package tn.univ.internautemicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.univ.internautemicroservice.clients.EventFeignClient;
import tn.univ.internautemicroservice.entities.Internaute;
import tn.univ.internautemicroservice.repositories.InternauteRepository;

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

    public String internauteLePlusActif() {
        return eventFeignClient.findMostActiveInternaut();
    }
}
