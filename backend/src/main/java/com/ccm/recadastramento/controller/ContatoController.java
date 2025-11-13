package com.ccm.recadastramento.controller;

import com.ccm.recadastramento.dto.ContatoDTO;
import com.ccm.recadastramento.service.ContatoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para Contatos
 */
@RestController
@RequestMapping("/api/contatos")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class ContatoController {

    private final ContatoService contatoService;

    /**
     * GET /api/contatos/funcionario/{funcionarioId} - Lista contatos de um funcionário
     */
    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<List<ContatoDTO>> listarPorFuncionario(@PathVariable Long funcionarioId) {
        log.info("GET /api/contatos/funcionario/{} - Listando contatos", funcionarioId);
        List<ContatoDTO> contatos = contatoService.listarPorFuncionario(funcionarioId);
        return ResponseEntity.ok(contatos);
    }

    /**
     * GET /api/contatos/{id} - Busca contato por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContatoDTO> buscarPorId(@PathVariable Long id) {
        log.info("GET /api/contatos/{} - Buscando contato", id);
        ContatoDTO contato = contatoService.buscarPorId(id);
        return ResponseEntity.ok(contato);
    }

    /**
     * POST /api/contatos - Cria novo contato
     */
    @PostMapping
    public ResponseEntity<ContatoDTO> criar(@Valid @RequestBody ContatoDTO dto) {
        log.info("POST /api/contatos - Criando novo contato");
        ContatoDTO created = contatoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * PUT /api/contatos/{id} - Atualiza contato
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContatoDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ContatoDTO dto) {
        log.info("PUT /api/contatos/{} - Atualizando contato", id);
        ContatoDTO updated = contatoService.atualizar(id, dto);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /api/contatos/{id} - Deleta contato
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        log.info("DELETE /api/contatos/{} - Deletando contato", id);
        contatoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
