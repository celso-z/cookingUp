package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.LivroReceitaDTO;
import br.ufjf.cookingup.model.dto.ReceitaLivroReceitaDTO;
import br.ufjf.cookingup.model.service.LivroReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/livros")
@CrossOrigin("*")
@Api("API de livros de receitas")
public class LivroReceitaController {

    @Autowired
    private LivroReceitaService livroReceitaService;

    @GetMapping("/{id}")
    @ApiOperation("Obter detalhes de um livro de receitas")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Livro Receitas encontrado"),
            @ApiResponse(code = 404, message = "Livro Receitas não encontrado")
    })
    public ResponseEntity<LivroReceitaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroReceitaService.buscarDTOporId(id));
    }

    @GetMapping
    @ApiOperation("Obter todos os livros de receitas")
    @ApiResponses({
        @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<List<LivroReceitaDTO>> listarTodos() {
        return ResponseEntity.ok(livroReceitaService.buscarTodos());
    }

    @PostMapping
    @ApiOperation("Registrar um novo livros de receitas")
    @ApiResponses({
        @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<LivroReceitaDTO> criar(@RequestBody LivroReceitaDTO dto) {
        return ResponseEntity.ok(livroReceitaService.salvar(dto));
    }

    @PutMapping("/{id}")
    @ApiOperation("Modificar um livro de receitas existente")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<LivroReceitaDTO> atualizar(@PathVariable Long id, @RequestBody LivroReceitaDTO dto) {
        return ResponseEntity.ok(livroReceitaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletar livro de receitas existente")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 404, message = "Livro de receitas com id já foi deletado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livroReceitaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Métodos específicos para gerenciar receitas no livro
    @PostMapping("/{idLivro}/receita/{idReceita}")
    @ApiOperation("Registrar uma nova receita em um livro de receitas existente")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<ReceitaLivroReceitaDTO> adicionarReceita(@PathVariable Long idLivro, @PathVariable Long idReceita) {
        return ResponseEntity.ok(livroReceitaService.adicionarReceitaNoLivro(idLivro, idReceita));
    }

    @DeleteMapping("/{idLivro}/receita-livro/{idReceitaLivroReceita}")
    @ApiOperation("Deletar receita em um livro de receitas existente")
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
    })
    public ResponseEntity<Void> removerReceita(@PathVariable Long idLivro, @PathVariable Long idReceitaLivroReceita) {
        livroReceitaService.removerReceitaDoLivro(idLivro, idReceitaLivroReceita);
        return ResponseEntity.noContent().build();
    }
}