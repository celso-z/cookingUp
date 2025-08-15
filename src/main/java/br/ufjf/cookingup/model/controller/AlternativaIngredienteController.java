package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.AlternativaIngredienteDTO;
import br.ufjf.cookingup.model.service.AlternativaIngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alternativas")
@CrossOrigin("*")
@Api("API de Alternativas para ingredientes")
public class AlternativaIngredienteController {

    @Autowired
    private AlternativaIngredienteService alternativaIngredienteService;

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um alternativa")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Alternativa Ingrediente encontrada"),
            @ApiResponse(code = 404, message = "Alternativa Ingrediente não encontrada")
    })
    public ResponseEntity<AlternativaIngredienteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alternativaIngredienteService.buscarDTOporId(id));
    }

    @GetMapping
    @ApiOperation("Obter todas as alternativas")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<List<AlternativaIngredienteDTO>> listarTodos() {
        return ResponseEntity.ok(alternativaIngredienteService.buscarTodos());
    }

    @PostMapping
    @ApiOperation("Registrar uma nova alternativa")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<AlternativaIngredienteDTO> criar(@RequestBody AlternativaIngredienteDTO dto) {
        return ResponseEntity.ok(alternativaIngredienteService.salvar(dto));
    }

    @PutMapping("/{id}")
    @ApiOperation("Modificar uma alternativa existente")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<AlternativaIngredienteDTO> atualizar(@PathVariable Long id, @RequestBody AlternativaIngredienteDTO dto) {
        return ResponseEntity.ok(alternativaIngredienteService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletar alternativa existente")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "Alternativa com id já foi deletado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        alternativaIngredienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}