package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.AdministradorDTO;
import br.ufjf.cookingup.model.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/administrador")
@CrossOrigin("*")
public class AdministradorController {

    @Autowired
    private  AdministradorService administradorService;

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorDTO> buscarPorId(@PathVariable Long id) {
        System.out.println("AdministradorController.buscarPorId");
        AdministradorDTO administrador = administradorService.buscarDTOporId(id);
        return ResponseEntity.ok(administrador);
    }
}
