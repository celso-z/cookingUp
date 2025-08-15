package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.ChefDTO;
import br.ufjf.cookingup.model.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chefs")
@CrossOrigin("*")
@Api("API de Chefs")
public class ChefController {

    @Autowired
    private ChefService chefService;

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um Chef")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Chef encontrado"),
            @ApiResponse(code = 404, message = "Chef não encontrado")
    })
    public ResponseEntity<ChefDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(chefService.buscarDTOporId(id));
    }

    @GetMapping
    @ApiOperation("Obter todas os chefs")
    @ApiResponses({
        @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<List<ChefDTO>> listarTodos() {
        return ResponseEntity.ok(chefService.buscarTodos());
    }

    @PostMapping
    @ApiOperation("Registrar um novo Chef")
    @ApiResponses({
        @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<ChefDTO> criar(@RequestBody ChefDTO dto) {
        return ResponseEntity.ok(chefService.salvar(dto));
    }

    @PutMapping("/{id}")
    @ApiOperation("Modificar um Chef existente")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<ChefDTO> atualizar(@PathVariable Long id, @RequestBody ChefDTO dto) {
        return ResponseEntity.ok(chefService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletar Chef existente")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "Chef com id já foi deletado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        chefService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}