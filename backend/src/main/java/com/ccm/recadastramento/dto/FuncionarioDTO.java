package com.ccm.recadastramento.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO para Funcionário
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {

    private Long id;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido")
    private String cpf;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 200)
    private String nome;

    @Size(max = 200)
    private String nomeSocial;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento deve ser no passado")
    private LocalDate dataNascimento;

    @NotBlank(message = "Raça/Cor é obrigatória")
    private String racaCor;

    @NotBlank(message = "Sexo é obrigatório")
    @Pattern(regexp = "masculino|feminino")
    private String sexo;

    @NotBlank(message = "Nacionalidade é obrigatória")
    private String nacionalidade;

    @NotBlank(message = "Estado de nascimento é obrigatório")
    @Size(min = 2, max = 2)
    private String estadoNascimento;

    @NotBlank(message = "Cidade de nascimento é obrigatória")
    @Size(max = 100)
    private String cidadeNascimento;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}")
    private String telefone;
}
