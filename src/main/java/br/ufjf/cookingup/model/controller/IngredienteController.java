package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.IngredienteDTO;
import br.ufjf.cookingup.model.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredientes")
@CrossOrigin("*")
@Api("API de Ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um Ingrediente")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ingrediente encontrado"),
            @ApiResponse(code = 404, message = "Ingrediente não encontrado")
    })
    public ResponseEntity<IngredienteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ingredienteService.buscarDTOporId(id));
    }

    @GetMapping
    @ApiOperation("Obter todas os ingredientes")
    @ApiResponses({
        @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<List<IngredienteDTO>> listarTodos() {
        return ResponseEntity.ok(ingredienteService.buscarTodos());
    }

    @PostMapping
    @ApiOperation("Registrar um novo ingrediente")
    @ApiResponses({
        @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<IngredienteDTO> criar(@RequestBody IngredienteDTO dto) {
        return ResponseEntity.ok(ingredienteService.salvar(dto));
    }

    @PutMapping("/{id}")
    @ApiOperation("Modificar um Ingrediente existente")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<IngredienteDTO> atualizar(@PathVariable Long id, @RequestBody IngredienteDTO dto) {
        return ResponseEntity.ok(ingredienteService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletar Ingrediente existente")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "Ingrediente com id já foi deletado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ingredienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}