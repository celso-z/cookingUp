package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.MembroDTO;
import br.ufjf.cookingup.model.service.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/membro")
@CrossOrigin("*")
public class MembroController {

    @Autowired
    private MembroService membroService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MembroDTO buscarPorId(@PathVariable Long id) {
        System.out.println("MembroController.buscarPorId");
        return membroService.buscarPorId(id);
    }
}
