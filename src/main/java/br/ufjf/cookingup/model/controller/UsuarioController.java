package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.UsuarioDTO;
import br.ufjf.cookingup.model.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO buscarPorId(@PathVariable Long id) {
        System.out.println("UsuarioController.buscarPorId");
        return usuarioService.buscarDTOporId(id);
    }
}
