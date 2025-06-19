package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.IngredienteDTO;
import br.ufjf.cookingup.model.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingrediente")
@CrossOrigin("*")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public IngredienteDTO buscarPorId(@PathVariable Long id) {
        System.out.println("IngredienteController.buscarPorId");
        return ingredienteService.buscarPorId(id);
    }
}
