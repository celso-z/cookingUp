package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.MembroDTO;
import br.ufjf.cookingup.model.service.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membro")
@CrossOrigin("*")
public class MembroController {

    @Autowired
    private MembroService membroService;

    @GetMapping("/{id}")
    public ResponseEntity<MembroDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(membroService.buscarDTOporId(id));
    }

    @GetMapping
    public ResponseEntity<List<MembroDTO>> listarTodos() {
        return ResponseEntity.ok(membroService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<MembroDTO> criar(@RequestBody MembroDTO dto) {
        return ResponseEntity.ok(membroService.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembroDTO> atualizar(@PathVariable Long id, @RequestBody MembroDTO dto) {
        return ResponseEntity.ok(membroService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        membroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}