package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.IngredienteDTO;
import br.ufjf.cookingup.model.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ingrediente")
@CrossOrigin("*")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<IngredienteDTO> buscarPorId(@PathVariable Long id) {
        System.out.println("IngredienteController.buscarPorId");
        return ResponseEntity.ok( ingredienteService.buscarDTOporId(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<IngredienteDTO>> listarIngredientes() {
        System.out.println("IngredienteController.listarIngredientes");
        return ResponseEntity.ok(ingredienteService.buscarTodos());
    }
}
