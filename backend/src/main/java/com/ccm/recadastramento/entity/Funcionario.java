package com.ccm.recadastramento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidade Funcionário
 * Representa os dados pessoais de um funcionário municipal
 */
@Entity
@Table(name = "funcionarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF deve estar no formato 000.000.000-00")
    @Column(unique = true, nullable = false, length = 14)
    private String cpf;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 200, message = "Nome deve ter entre 3 e 200 caracteres")
    @Column(nullable = false, length = 200)
    private String nome;

    @Size(max = 200, message = "Nome social deve ter no máximo 200 caracteres")
    @Column(name = "nome_social", length = 200)
    private String nomeSocial;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento deve ser no passado")
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @NotBlank(message = "Raça/Cor é obrigatória")
    @Column(name = "raca_cor", nullable = false, length = 20)
    private String racaCor;

    @NotBlank(message = "Sexo é obrigatório")
    @Pattern(regexp = "masculino|feminino", message = "Sexo deve ser masculino ou feminino")
    @Column(nullable = false, length = 10)
    private String sexo;

    @NotBlank(message = "Nacionalidade é obrigatória")
    @Column(nullable = false, length = 50)
    private String nacionalidade;

    @NotBlank(message = "Estado de nascimento é obrigatório")
    @Size(min = 2, max = 2, message = "Estado deve ter 2 caracteres")
    @Column(name = "estado_nascimento", nullable = false, length = 2)
    private String estadoNascimento;

    @NotBlank(message = "Cidade de nascimento é obrigatória")
    @Size(max = 100, message = "Cidade deve ter no máximo 100 caracteres")
    @Column(name = "cidade_nascimento", nullable = false, length = 100)
    private String cidadeNascimento;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "Telefone deve estar no formato (00) 00000-0000")
    @Column(nullable = false, length = 15)
    private String telefone;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;
}
