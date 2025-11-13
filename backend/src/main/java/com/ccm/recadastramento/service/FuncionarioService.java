package com.ccm.recadastramento.service;

import com.ccm.recadastramento.dto.FuncionarioDTO;
import com.ccm.recadastramento.entity.Funcionario;
import com.ccm.recadastramento.exception.ResourceNotFoundException;
import com.ccm.recadastramento.exception.DuplicateResourceException;
import com.ccm.recadastramento.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service para gerenciar Funcionários
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final ModelMapper modelMapper;

    /**
     * Lista todos os funcionários
     */
    @Transactional(readOnly = true)
    public List<FuncionarioDTO> listarTodos() {
        log.debug("Listando todos os funcionários");
        return funcionarioRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca funcionário por ID
     */
    @Transactional(readOnly = true)
    public FuncionarioDTO buscarPorId(Long id) {
        log.debug("Buscando funcionário por ID: {}", id);
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com ID: " + id));
        return convertToDTO(funcionario);
    }

    /**
     * Busca funcionário por CPF
     */
    @Transactional(readOnly = true)
    public FuncionarioDTO buscarPorCpf(String cpf) {
        log.debug("Buscando funcionário por CPF: {}", cpf);
        Funcionario funcionario = funcionarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com CPF: " + cpf));
        return convertToDTO(funcionario);
    }

    /**
     * Cria novo funcionário
     */
    @Transactional
    public FuncionarioDTO criar(FuncionarioDTO dto) {
        log.debug("Criando novo funcionário com CPF: {}", dto.getCpf());

        if (funcionarioRepository.existsByCpf(dto.getCpf())) {
            throw new DuplicateResourceException("Já existe um funcionário cadastrado com o CPF: " + dto.getCpf());
        }

        Funcionario funcionario = convertToEntity(dto);
        Funcionario saved = funcionarioRepository.save(funcionario);
        log.info("Funcionário criado com ID: {}", saved.getId());
        return convertToDTO(saved);
    }

    /**
     * Atualiza funcionário existente
     */
    @Transactional
    public FuncionarioDTO atualizar(Long id, FuncionarioDTO dto) {
        log.debug("Atualizando funcionário ID: {}", id);

        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com ID: " + id));

        // Verifica se o CPF já está em uso por outro funcionário
        if (!funcionario.getCpf().equals(dto.getCpf()) && funcionarioRepository.existsByCpf(dto.getCpf())) {
            throw new DuplicateResourceException("CPF já está em uso: " + dto.getCpf());
        }

        // Atualiza os campos
        modelMapper.map(dto, funcionario);
        funcionario.setId(id); // Garante que o ID não muda

        Funcionario updated = funcionarioRepository.save(funcionario);
        log.info("Funcionário atualizado ID: {}", id);
        return convertToDTO(updated);
    }

    /**
     * Deleta funcionário
     */
    @Transactional
    public void deletar(Long id) {
        log.debug("Deletando funcionário ID: {}", id);

        if (!funcionarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Funcionário não encontrado com ID: " + id);
        }

        funcionarioRepository.deleteById(id);
        log.info("Funcionário deletado ID: {}", id);
    }

    // Métodos auxiliares de conversão
    private FuncionarioDTO convertToDTO(Funcionario entity) {
        return modelMapper.map(entity, FuncionarioDTO.class);
    }

    private Funcionario convertToEntity(FuncionarioDTO dto) {
        return modelMapper.map(dto, Funcionario.class);
    }
}
