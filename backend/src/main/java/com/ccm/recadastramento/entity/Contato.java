package com.ccm.recadastramento.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Entidade Contato
 * Representa os contatos de um funcionário (email, telefone, etc)
 */
@Entity
@Table(name = "contatos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @NotBlank(message = "Tipo de contato é obrigatório")
    @Pattern(regexp = "email|celular|telefone", message = "Tipo deve ser: email, celular ou telefone")
    @Column(nullable = false, length = 20)
    private String tipo;

    @NotBlank(message = "Valor do contato é obrigatório")
    @Size(max = 100, message = "Valor deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String valor;

    @Column(length = 200)
    private String descricao;

    @Column(nullable = false)
    private Boolean principal = false;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;
}
