package com.javanauta.usuario.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDTO {
    private Long id;
    private String telefone;
    private String ddd;
}
