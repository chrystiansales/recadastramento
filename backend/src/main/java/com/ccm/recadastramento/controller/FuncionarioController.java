package com.ccm.recadastramento.controller;

import com.ccm.recadastramento.dto.FuncionarioDTO;
import com.ccm.recadastramento.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para Funcionários
 */
@RestController
@RequestMapping("/api/funcionarios")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    /**
     * GET /api/funcionarios - Lista todos os funcionários
     */
    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> listarTodos() {
        log.info("GET /api/funcionarios - Listando todos os funcionários");
        List<FuncionarioDTO> funcionarios = funcionarioService.listarTodos();
        return ResponseEntity.ok(funcionarios);
    }

    /**
     * GET /api/funcionarios/{id} - Busca funcionário por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> buscarPorId(@PathVariable Long id) {
        log.info("GET /api/funcionarios/{} - Buscando funcionário", id);
        FuncionarioDTO funcionario = funcionarioService.buscarPorId(id);
        return ResponseEntity.ok(funcionario);
    }

    /**
     * GET /api/funcionarios/cpf/{cpf} - Busca funcionário por CPF
     */
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<FuncionarioDTO> buscarPorCpf(@PathVariable String cpf) {
        log.info("GET /api/funcionarios/cpf/{} - Buscando funcionário por CPF", cpf);
        FuncionarioDTO funcionario = funcionarioService.buscarPorCpf(cpf);
        return ResponseEntity.ok(funcionario);
    }

    /**
     * POST /api/funcionarios - Cria novo funcionário
     */
    @PostMapping
    public ResponseEntity<FuncionarioDTO> criar(@Valid @RequestBody FuncionarioDTO dto) {
        log.info("POST /api/funcionarios - Criando novo funcionário");
        FuncionarioDTO created = funcionarioService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * PUT /api/funcionarios/{id} - Atualiza funcionário
     */
    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody FuncionarioDTO dto) {
        log.info("PUT /api/funcionarios/{} - Atualizando funcionário", id);
        FuncionarioDTO updated = funcionarioService.atualizar(id, dto);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /api/funcionarios/{id} - Deleta funcionário
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        log.info("DELETE /api/funcionarios/{} - Deletando funcionário", id);
        funcionarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
