package br.ufjf.cookingup.model.controller;

    import br.ufjf.cookingup.model.dto.AvaliacaoDTO;
    import br.ufjf.cookingup.model.service.AvaliacaoService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/v1/avaliacoes")
    @CrossOrigin("*")
    public class AvaliacaoController {

        @Autowired
        private AvaliacaoService avaliacaoService;

        @GetMapping("/{id}")
        public ResponseEntity<AvaliacaoDTO> buscarPorId(@PathVariable Long id) {
            return ResponseEntity.ok(avaliacaoService.buscarDTOporId(id));
        }

        @GetMapping
        public ResponseEntity<List<AvaliacaoDTO>> listarTodos() {
            return ResponseEntity.ok(avaliacaoService.buscarTodos());
        }

        @PostMapping
        public ResponseEntity<AvaliacaoDTO> criar(@RequestBody AvaliacaoDTO dto) {
            return ResponseEntity.ok(avaliacaoService.salvar(dto));
        }

        @PutMapping("/{id}")
        public ResponseEntity<AvaliacaoDTO> atualizar(@PathVariable Long id, @RequestBody AvaliacaoDTO dto) {
            return ResponseEntity.ok(avaliacaoService.atualizar(id, dto));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deletar(@PathVariable Long id) {
            avaliacaoService.deletar(id);
            return ResponseEntity.noContent().build();
        }
    }