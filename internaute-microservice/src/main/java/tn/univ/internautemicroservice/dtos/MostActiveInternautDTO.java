package tn.univ.internautemicroservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MostActiveInternautDTO {
    private Long idInternaut;
    private Long ticketCount;
}