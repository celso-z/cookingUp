package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.AlternativaIngredienteDTO;
import br.ufjf.cookingup.model.service.AlternativaIngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alternativa")
@CrossOrigin("*")
public class AlternativaIngredienteController {

    @Autowired
    private AlternativaIngredienteService alternativaIngredienteService;

    @GetMapping("/{id}")
    public ResponseEntity<AlternativaIngredienteDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alternativaIngredienteService.buscarDTOporId(id));
    }

    @GetMapping
    public ResponseEntity<List<AlternativaIngredienteDTO>> listarTodos() {
        return ResponseEntity.ok(alternativaIngredienteService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<AlternativaIngredienteDTO> criar(@RequestBody AlternativaIngredienteDTO dto) {
        return ResponseEntity.ok(alternativaIngredienteService.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlternativaIngredienteDTO> atualizar(@PathVariable Long id, @RequestBody AlternativaIngredienteDTO dto) {
        return ResponseEntity.ok(alternativaIngredienteService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        alternativaIngredienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}