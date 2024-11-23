package tn.univ.eventmicroservice.aspects;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TicketServiceAspect {

    private static final Logger logger = LoggerFactory.getLogger(TicketServiceAspect.class);

    @AfterThrowing(
            pointcut = "execution(* tn.univ.eventmicroservice.services.TicketService.ajouterTicketsEtAffecterAEvenementEtInternaute(..))",
            throwing = "ex"
    )
    public void logAfterThrowing(Exception ex) {
        if (ex instanceof UnsupportedOperationException) {
            logger.error("Le nombre de places restantes dépasse le nombre de tickets demandés");
        } else {
            logger.error("Une exception inattendue s'est produite: {}", ex.getMessage());
        }
    }
}
