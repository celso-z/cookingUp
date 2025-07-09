package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.ReceitaDTO;
import br.ufjf.cookingup.model.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receita")
@CrossOrigin("*")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReceitaDTO buscarPorId(@PathVariable Long id) {
        System.out.println("ReceitaController.buscarPorId");
        return receitaService.buscarDTOporId(id);
    }

}
