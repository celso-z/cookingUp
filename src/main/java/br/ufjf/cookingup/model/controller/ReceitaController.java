package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.ReceitaDTO;
import br.ufjf.cookingup.model.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/receitas")
@CrossOrigin("*")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(receitaService.buscarDTOporId(id));
    }

    @GetMapping
    public ResponseEntity<List<ReceitaDTO>> listarTodos() {
        return ResponseEntity.ok(receitaService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<ReceitaDTO> criar(@RequestBody ReceitaDTO dto) {
        return ResponseEntity.ok(receitaService.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceitaDTO> atualizar(@PathVariable Long id, @RequestBody ReceitaDTO dto) {
        return ResponseEntity.ok(receitaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        receitaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}