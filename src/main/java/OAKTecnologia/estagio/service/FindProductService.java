package OAKTecnologia.estagio.service;

import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import OAKTecnologia.estagio.DTO.ProdutoResponseDTO;
import OAKTecnologia.estagio.repositories.CadastroandFindRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Service
public class FindProductService {
  
  @Autowired
  private CadastroandFindRepository findProductRepository;

  @Operation(summary = "Buscar produtos", 
               description = "Permite buscar produtos pelo nome (`produtoName`) e/ou preço (`price`). "
                           + "Os produtos retornados estão ordenados pelo preço de forma decrescente.")
  @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                     description = "Lista de produtos encontrada com sucesso.",
                     content = @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = ProdutoResponseDTO.class),
                     examples = @ExampleObject(value = "[\n"
                             + "  {\n"
                             + "    \"produtoName\": \"Produto 1\",\n"
                             + "    \"descricao\": \"Descrição do Produto 1\",\n"
                             + "    \"preco\": 199.99\n"
                             + "  },\n"
                             + "  {\n"
                             + "    \"produtoName\": \"Produto 2\",\n"
                             + "    \"descricao\": \"Descrição do Produto 2\",\n"
                             + "    \"preco\": 99.99\n"
                             + "  }\n"
                             + "]"))),
        @ApiResponse(responseCode = "204", 
                     description = "Nenhum produto encontrado para os critérios especificados."),
        @ApiResponse(responseCode = "500", 
                     description = "Erro interno ao buscar os produtos.",
                     content = @Content(mediaType = "application/json", 
                     examples = @ExampleObject(value = "[\n"
                             + "  {\n"
                             + "    \"produtoName\": \"Erro ao buscar produtos no banco de dados\"\n"
                             + "  }\n"
                             + "]")))
  })
 public List<ProdutoResponseDTO> findProduct() {
    try {
        // Busca os produtos ordenados pelo preço diretamente no banco de dados
        List<Object[]> produtos = findProductRepository.findProdutoNameAndPriceOrderedByPriceAsc();
        
        // Transforma os resultados em uma lista de ProdutoResponseDTO
        return produtos.stream()
        .map(obj -> ProdutoResponseDTO.builder()
                .produtoName(obj[0].toString()) // Define o nome do produto
                .preco((Double) obj[1])        // Define o preço
                .build())                      // Cria o objeto ProdutoResponseDTO
        .collect(Collectors.toList());
        
    } catch (Exception e) {
        e.printStackTrace();
        return Collections.emptyList(); // Retorna uma lista vazia em caso de erro
    }
}

}
