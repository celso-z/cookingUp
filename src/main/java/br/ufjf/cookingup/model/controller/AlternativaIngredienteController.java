package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.AlternativaIngredienteDTO;
import br.ufjf.cookingup.model.service.AlternativaIngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alternativa-ingrediente")
@CrossOrigin("*")
public class AlternativaIngredienteController {

    @Autowired
    private AlternativaIngredienteService alteranativaIngredienteService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AlternativaIngredienteDTO buscarPorId(@PathVariable Long id) {
        System.out.println("AlternativaIngredienteController.buscarPorId");
        return alteranativaIngredienteService.buscarPorId(id);
    }
}
