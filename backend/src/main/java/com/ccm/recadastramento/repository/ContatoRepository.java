package com.ccm.recadastramento.repository;

import com.ccm.recadastramento.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository para Contato
 */
@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    /**
     * Busca todos os contatos de um funcionário
     */
    List<Contato> findByFuncionarioId(Long funcionarioId);

    /**
     * Busca contatos de um funcionário por tipo
     */
    List<Contato> findByFuncionarioIdAndTipo(Long funcionarioId, String tipo);
}
