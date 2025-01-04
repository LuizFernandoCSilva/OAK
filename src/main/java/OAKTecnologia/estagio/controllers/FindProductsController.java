package OAKTecnologia.estagio.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import OAKTecnologia.estagio.DTO.ProdutoResponseDTO;
import OAKTecnologia.estagio.service.FindProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController

@RequestMapping("/find")
public class FindProductsController {

  @Autowired
  private FindProductService findProductService;
  
  @Operation(
      summary = "Buscar lista de produtos",
      description = "Este endpoint retorna uma lista de produtos com nome e preço ordenados pelo preço (do menor para o maior)."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso",
              content = @Content(mediaType = "application/json",
                      schema = @Schema(implementation = ProdutoResponseDTO.class))),
      @ApiResponse(responseCode = "204", description = "Nenhum produto encontrado"),
      @ApiResponse(responseCode = "500", description = "Erro interno ao buscar produtos",
              content = @Content(mediaType = "application/json"))
  })
  @GetMapping()
  public ResponseEntity<List<ProdutoResponseDTO>> findProducts() {
        try {

            // Chamar o service para buscar os produtos
            List<ProdutoResponseDTO> response = findProductService.findProduct();

            // Verificar se a lista está vazia
            if (response.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 No Content
            }

            return ResponseEntity.ok(response); // 200 OK com a lista de produtos
        } catch (Exception e) {
            e.printStackTrace();

            // Retornar erro genérico em caso de falha
            ProdutoResponseDTO errorResponse = ProdutoResponseDTO.builder()
                    .produtoName("Erro ao buscar produtos no banco de dados")
                    .build();
            return ResponseEntity.status(500).body(Collections.singletonList(errorResponse));
        }
    }
}
