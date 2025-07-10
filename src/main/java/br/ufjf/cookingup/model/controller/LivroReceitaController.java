package br.ufjf.cookingup.model.controller;

import br.ufjf.cookingup.model.dto.LivroReceitaDTO;
import br.ufjf.cookingup.model.dto.ReceitaLivroReceitaDTO;
import br.ufjf.cookingup.model.service.LivroReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livro")
@CrossOrigin("*")
public class LivroReceitaController {

    @Autowired
    private LivroReceitaService livroReceitaService;

    @GetMapping("/{id}")
    public ResponseEntity<LivroReceitaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroReceitaService.buscarDTOporId(id));
    }

    @GetMapping
    public ResponseEntity<List<LivroReceitaDTO>> listarTodos() {
        return ResponseEntity.ok(livroReceitaService.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<LivroReceitaDTO> criar(@RequestBody LivroReceitaDTO dto) {
        return ResponseEntity.ok(livroReceitaService.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroReceitaDTO> atualizar(@PathVariable Long id, @RequestBody LivroReceitaDTO dto) {
        return ResponseEntity.ok(livroReceitaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livroReceitaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Métodos específicos para gerenciar receitas no livro
    @PostMapping("/{idLivro}/receita/{idReceita}")
    public ResponseEntity<ReceitaLivroReceitaDTO> adicionarReceita(@PathVariable Long idLivro, @PathVariable Long idReceita) {
        return ResponseEntity.ok(livroReceitaService.adicionarReceitaNoLivro(idLivro, idReceita));
    }

    @DeleteMapping("/{idLivro}/receita-livro/{idReceitaLivroReceita}")
    public ResponseEntity<Void> removerReceita(@PathVariable Long idLivro, @PathVariable Long idReceitaLivroReceita) {
        livroReceitaService.removerReceitaDoLivro(idLivro, idReceitaLivroReceita);
        return ResponseEntity.noContent().build();
    }
}