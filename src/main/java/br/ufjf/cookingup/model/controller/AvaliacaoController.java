package br.ufjf.cookingup.model.controller;

    import br.ufjf.cookingup.model.dto.AvaliacaoDTO;
    import br.ufjf.cookingup.model.service.AvaliacaoService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import io.swagger.annotations.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/v1/avaliacoes")
    @CrossOrigin("*")
    @Api("API de Avaliações para receitas")
    public class AvaliacaoController {

        @Autowired
        private AvaliacaoService avaliacaoService;

        @GetMapping("/{id}")
        @ApiOperation("Obter detalhes de uma avaliação")
        @ApiResponses({
                @ApiResponse(code = 200, message = "Avaliação encontrada"),
                @ApiResponse(code = 404, message = "Avaliação não encontrada")
        })
        public ResponseEntity<AvaliacaoDTO> buscarPorId(@PathVariable Long id) {
            return ResponseEntity.ok(avaliacaoService.buscarDTOporId(id));
        }

        @GetMapping
        @ApiOperation("Obter todas as avaliações")
        @ApiResponses({
            @ApiResponse(code = 200, message = ""),
        })
        public ResponseEntity<List<AvaliacaoDTO>> listarTodos() {
            return ResponseEntity.ok(avaliacaoService.buscarTodos());
        }

        @PostMapping
        @ApiOperation("Registrar uma nova avaliação")
        @ApiResponses({
            @ApiResponse(code = 200, message = ""),
        })
        public ResponseEntity<AvaliacaoDTO> criar(@RequestBody AvaliacaoDTO dto) {
            return ResponseEntity.ok(avaliacaoService.salvar(dto));
        }

        @PutMapping("/{id}")
        @ApiOperation("Modificar uma avaliação existente")
        @ApiResponses({
                @ApiResponse(code = 200, message = ""),
        })
        public ResponseEntity<AvaliacaoDTO> atualizar(@PathVariable Long id, @RequestBody AvaliacaoDTO dto) {
            return ResponseEntity.ok(avaliacaoService.atualizar(id, dto));
        }

        @DeleteMapping("/{id}")
        @ApiOperation("Deletar avaliação existente")
        @ApiResponses({
                @ApiResponse(code = 200, message = ""),
                @ApiResponse(code = 404, message = "Avaliação com id já foi deletado")
        })
        public ResponseEntity<Void> deletar(@PathVariable Long id) {
            avaliacaoService.deletar(id);
            return ResponseEntity.noContent().build();
        }
    }