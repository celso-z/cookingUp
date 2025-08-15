package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.IngredienteDTO;
import br.ufjf.cookingup.model.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredientes")
@CrossOrigin("*")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping("/{id}")
    public ResponseEntity<IngredienteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ingredienteService.buscarDTOporId(id));
    }

    @GetMapping
    public ResponseEntity<List<IngredienteDTO>> listarTodos() {
        return ResponseEntity.ok(ingredienteService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<IngredienteDTO> criar(@RequestBody IngredienteDTO dto) {
        return ResponseEntity.ok(ingredienteService.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredienteDTO> atualizar(@PathVariable Long id, @RequestBody IngredienteDTO dto) {
        return ResponseEntity.ok(ingredienteService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ingredienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}