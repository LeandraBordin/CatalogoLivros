package com.leandra.LivroService.business.dto.Response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutorResponseDTO {
    private Long id;
    private String nome;
    private String nacionalidade;
}
