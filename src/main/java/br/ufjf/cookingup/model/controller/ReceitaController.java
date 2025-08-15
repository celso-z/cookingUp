package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.ReceitaDTO;
import br.ufjf.cookingup.model.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/receitas")
@CrossOrigin("*")
@Api("API de Receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de uma receita")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Receita encontrada"),
            @ApiResponse(code = 404, message = "Receita não encontrada")
    })
    public ResponseEntity<ReceitaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(receitaService.buscarDTOporId(id));
    }

    @GetMapping
    @ApiOperation("Obter todas as receitas")
    @ApiResponses({
        @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<List<ReceitaDTO>> listarTodos() {
        return ResponseEntity.ok(receitaService.buscarTodos());
    }

    @PostMapping
    @ApiOperation("Registrar uma nova receita")
    @ApiResponses({
        @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<ReceitaDTO> criar(@RequestBody ReceitaDTO dto) {
        return ResponseEntity.ok(receitaService.salvar(dto));
    }

    @PutMapping("/{id}")
    @ApiOperation("Modificar uma receita existente")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<ReceitaDTO> atualizar(@PathVariable Long id, @RequestBody ReceitaDTO dto) {
        return ResponseEntity.ok(receitaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletar receita existente")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "Receita com id já foi deletado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        receitaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}