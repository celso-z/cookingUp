package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.ChefDTO;
import br.ufjf.cookingup.model.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chefs")
@CrossOrigin("*")
public class ChefController {

    @Autowired
    private ChefService chefService;

    @GetMapping("/{id}")
    public ResponseEntity<ChefDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(chefService.buscarDTOporId(id));
    }

    @GetMapping
    public ResponseEntity<List<ChefDTO>> listarTodos() {
        return ResponseEntity.ok(chefService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<ChefDTO> criar(@RequestBody ChefDTO dto) {
        return ResponseEntity.ok(chefService.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChefDTO> atualizar(@PathVariable Long id, @RequestBody ChefDTO dto) {
        return ResponseEntity.ok(chefService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        chefService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}