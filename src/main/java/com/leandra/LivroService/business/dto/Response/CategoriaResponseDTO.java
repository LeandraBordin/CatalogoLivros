package com.leandra.LivroService.business.dto.Response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class CategoriaResponseDTO {
    private Long id;
    private String nome;
}
