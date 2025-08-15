package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.AdministradorDTO;
import br.ufjf.cookingup.model.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/administradores")
@CrossOrigin("*")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(administradorService.buscarDTOporId(id));
    }

    @GetMapping
    public ResponseEntity<List<AdministradorDTO>> listarTodos() {
        return ResponseEntity.ok(administradorService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<AdministradorDTO> criar(@RequestBody AdministradorDTO dto) {
        return ResponseEntity.ok(administradorService.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministradorDTO> atualizar(@PathVariable Long id, @RequestBody AdministradorDTO dto) {
        return ResponseEntity.ok(administradorService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        administradorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}