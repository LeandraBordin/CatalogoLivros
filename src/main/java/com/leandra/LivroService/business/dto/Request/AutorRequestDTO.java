package com.leandra.LivroService.business.dto.Request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutorRequestDTO {
    private String nome;
    private String nacionalidade;
}
