package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.CategoriaDTO;
import br.ufjf.cookingup.model.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
@CrossOrigin("*")
@Api("API de Categorias de receitas")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de uma categoria")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Categoria encontrada"),
            @ApiResponse(code = 404, message = "Categoria não encontrada")
    })
    public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarDTOporId(id));
    }

    @GetMapping
    @ApiOperation("Obter todas as categorias")
    @ApiResponses({
        @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<List<CategoriaDTO>> listarTodos() {
        return ResponseEntity.ok(categoriaService.buscarTodos());
    }

    @PostMapping
    @ApiOperation("Registrar uma nova categoria")
    @ApiResponses({
        @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<CategoriaDTO> criar(@RequestBody CategoriaDTO dto) {
        return ResponseEntity.ok(categoriaService.salvar(dto));
    }

    @PutMapping("/{id}")
    @ApiOperation("Modificar uma categoria existente")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<CategoriaDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaDTO dto) {
        return ResponseEntity.ok(categoriaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletar categoria existente")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "Categoria com id já foi deletado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}