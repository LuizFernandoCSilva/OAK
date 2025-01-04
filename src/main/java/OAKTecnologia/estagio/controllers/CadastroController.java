package OAKTecnologia.estagio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import OAKTecnologia.estagio.DTO.ProdutoResponseDTO;
import OAKTecnologia.estagio.entities.ProdutoEntity;
import OAKTecnologia.estagio.service.CadastroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

// Classe responsável por controlar o cadastro de produtos

// A anotação @RestController indica que a classe é um controlador REST
@RestController
// A anotação @RequestMapping mapeia a URL base para a classe
@RequestMapping("/cadastro")
public class CadastroController {

  @Autowired
  private CadastroService cadastroService;
  
  @Operation(summary = "Cadastrar um produto", 
               description = "Este endpoint permite cadastrar um novo produto no sistema. "
                           + "Os campos obrigatórios são validados, e o sistema retorna o produto cadastrado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
                     description = "Produto cadastrado com sucesso.", 
                     content = @Content(mediaType = "application/json",
                     schema = @Schema(implementation = ProdutoResponseDTO.class),
                     examples = @ExampleObject(value = "{\n"
                             + "  \"produtoName\": \"Produto Exemplo\",\n"
                             + "  \"descricao\": \"Descrição do Produto Exemplo\",\n"
                             + "  \"preco\": 99.99,\n"
                             + "  \"disponivel\": true\n"
                             + "}"))),
        @ApiResponse(responseCode = "400", 
                     description = "Erro de validação ou dados ausentes no corpo da requisição.", 
                     content = @Content(mediaType = "text/plain", 
                     examples = @ExampleObject(value = "Erro ao cadastrar produto, ausência de dados do campo: produtoName"))),
        @ApiResponse(responseCode = "500", 
                     description = "Erro interno no servidor.", 
                     content = @Content(mediaType = "text/plain", 
                     examples = @ExampleObject(value = "Erro interno no servidor: Mensagem de erro detalhada")))
    })
  // Método responsável por cadastrar um produto
  @PostMapping("/produto")
  public ResponseEntity<Object> cadastrarProduto(@Valid @RequestBody ProdutoEntity produto) {
    try{
      ProdutoResponseDTO result = cadastroService.cadastrarProduto(produto);
      return ResponseEntity.ok(result);

    }catch(HttpMessageNotReadableException e){
      e.printStackTrace();
      return ResponseEntity.badRequest().body("Erro ao cadastrar produto, ausência de dados do campo: " + e.getMessage());
    }catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().body("Erro interno no servidor: " + e.getMessage());
    }
  }
}
