package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.MembroDTO;
import br.ufjf.cookingup.model.service.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/membros")
@CrossOrigin("*")
@Api("API de Membros")
public class MembroController {

    @Autowired
    private MembroService membroService;

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um membro")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Membro encontrado"),
            @ApiResponse(code = 404, message = "Membro não encontrado")
    })
    public ResponseEntity<MembroDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(membroService.buscarDTOporId(id));
    }

    @GetMapping
    @ApiOperation("Obter todos os membros")
    @ApiResponses({
        @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<List<MembroDTO>> listarTodos() {
        return ResponseEntity.ok(membroService.buscarTodos());
    }

    @PostMapping
    @ApiOperation("Registrar um novo membro")
    @ApiResponses({
        @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<MembroDTO> criar(@RequestBody MembroDTO dto) {
        return ResponseEntity.ok(membroService.salvar(dto));
    }

    @PutMapping("/{id}")
    @ApiOperation("Modificar um membro existente")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<MembroDTO> atualizar(@PathVariable Long id, @RequestBody MembroDTO dto) {
        return ResponseEntity.ok(membroService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletar membro existente")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "Membro com id já foi deletado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        membroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}