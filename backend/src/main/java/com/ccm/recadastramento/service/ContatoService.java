package com.ccm.recadastramento.service;

import com.ccm.recadastramento.dto.ContatoDTO;
import com.ccm.recadastramento.entity.Contato;
import com.ccm.recadastramento.entity.Funcionario;
import com.ccm.recadastramento.exception.ResourceNotFoundException;
import com.ccm.recadastramento.repository.ContatoRepository;
import com.ccm.recadastramento.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service para gerenciar Contatos
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final ModelMapper modelMapper;

    /**
     * Lista contatos de um funcionário
     */
    @Transactional(readOnly = true)
    public List<ContatoDTO> listarPorFuncionario(Long funcionarioId) {
        log.debug("Listando contatos do funcionário ID: {}", funcionarioId);
        return contatoRepository.findByFuncionarioId(funcionarioId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca contato por ID
     */
    @Transactional(readOnly = true)
    public ContatoDTO buscarPorId(Long id) {
        log.debug("Buscando contato ID: {}", id);
        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado com ID: " + id));
        return convertToDTO(contato);
    }

    /**
     * Cria novo contato
     */
    @Transactional
    public ContatoDTO criar(ContatoDTO dto) {
        log.debug("Criando contato para funcionário ID: {}", dto.getFuncionarioId());

        Funcionario funcionario = funcionarioRepository.findById(dto.getFuncionarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com ID: " + dto.getFuncionarioId()));

        Contato contato = convertToEntity(dto);
        contato.setFuncionario(funcionario);

        Contato saved = contatoRepository.save(contato);
        log.info("Contato criado com ID: {}", saved.getId());
        return convertToDTO(saved);
    }

    /**
     * Atualiza contato existente
     */
    @Transactional
    public ContatoDTO atualizar(Long id, ContatoDTO dto) {
        log.debug("Atualizando contato ID: {}", id);

        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado com ID: " + id));

        contato.setTipo(dto.getTipo());
        contato.setValor(dto.getValor());
        contato.setDescricao(dto.getDescricao());
        contato.setPrincipal(dto.getPrincipal());

        Contato updated = contatoRepository.save(contato);
        log.info("Contato atualizado ID: {}", id);
        return convertToDTO(updated);
    }

    /**
     * Deleta contato
     */
    @Transactional
    public void deletar(Long id) {
        log.debug("Deletando contato ID: {}", id);

        if (!contatoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contato não encontrado com ID: " + id);
        }

        contatoRepository.deleteById(id);
        log.info("Contato deletado ID: {}", id);
    }

    // Métodos auxiliares de conversão
    private ContatoDTO convertToDTO(Contato entity) {
        ContatoDTO dto = modelMapper.map(entity, ContatoDTO.class);
        dto.setFuncionarioId(entity.getFuncionario().getId());
        return dto;
    }

    private Contato convertToEntity(ContatoDTO dto) {
        return modelMapper.map(dto, Contato.class);
    }
}
