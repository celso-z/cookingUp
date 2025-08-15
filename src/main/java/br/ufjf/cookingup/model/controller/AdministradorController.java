package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.AdministradorDTO;
import br.ufjf.cookingup.model.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/administradores")
@CrossOrigin("*")
@Api("API de Administradores")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um administrador")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Administrador encontrado"),
            @ApiResponse(code = 404, message = "Administrador não encontrado")
    })
    public ResponseEntity<AdministradorDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(administradorService.buscarDTOporId(id));
    }

    @GetMapping
    @ApiResponses({
        @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<List<AdministradorDTO>> listarTodos() {
        return ResponseEntity.ok(administradorService.buscarTodos());
    }

    
    @PostMapping
    @ApiResponses({
        @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<AdministradorDTO> criar(@RequestBody AdministradorDTO dto) {
        return ResponseEntity.ok(administradorService.salvar(dto));
    }

    @PutMapping("/{id}")
    @ApiResponses({
        @ApiResponse(code = 200, message = ""),
        @ApiResponse(code = 404, message = "Não é possível atualizar um administrador que já foi deletado com id")
    })
    public ResponseEntity<AdministradorDTO> atualizar(@PathVariable Long id, @RequestBody AdministradorDTO dto) {
        return ResponseEntity.ok(administradorService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ApiResponses({
        @ApiResponse(code = 200, message = ""),
        @ApiResponse(code = 404, message = "Administrador com id já foi deletado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        administradorService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}