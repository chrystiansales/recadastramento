package com.ccm.recadastramento.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para Contato
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContatoDTO {

    private Long id;

    @NotNull(message = "ID do funcionário é obrigatório")
    private Long funcionarioId;

    @NotBlank(message = "Tipo é obrigatório")
    @Pattern(regexp = "email|celular|telefone")
    private String tipo;

    @NotBlank(message = "Valor é obrigatório")
    @Size(max = 100)
    private String valor;

    @Size(max = 200)
    private String descricao;

    private Boolean principal;
}
