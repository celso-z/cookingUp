package br.ufjf.cookingup.model.controller;


import br.ufjf.cookingup.model.dto.CategoriaDTO;
import br.ufjf.cookingup.model.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoriaDTO buscarPorId(@PathVariable Long id) {
        System.out.println("CategoriaController.buscarPorId");
        return categoriaService.buscarDTOporId(id);
    }
}
