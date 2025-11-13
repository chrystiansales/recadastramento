package com.ccm.recadastramento.repository;

import com.ccm.recadastramento.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository para Funcionário
 */
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    /**
     * Busca funcionário por CPF
     */
    Optional<Funcionario> findByCpf(String cpf);

    /**
     * Verifica se existe funcionário com o CPF informado
     */
    boolean existsByCpf(String cpf);
}
