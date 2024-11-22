package tn.univ.internautemicroservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "event-microservice")
public interface EventFeignClient {

    @GetMapping("/api/tickets/most-active-internaut")
    String findMostActiveInternaut();
}
