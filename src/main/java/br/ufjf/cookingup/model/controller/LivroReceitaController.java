package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.LivroReceitaDTO;
import br.ufjf.cookingup.model.service.LivroReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livro")
@CrossOrigin("*")
public class LivroReceitaController {

    @Autowired
    private LivroReceitaService livroReceitaService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LivroReceitaDTO buscarPorId(@PathVariable Long id) {
        System.out.println("LivroReceitaController.buscarPorId");
        return livroReceitaService.buscarPorId(id);
    }
}
