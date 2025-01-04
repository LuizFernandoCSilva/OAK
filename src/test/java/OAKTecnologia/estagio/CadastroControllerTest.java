package OAKTecnologia.estagio;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import OAKTecnologia.estagio.service.CadastroService;
import OAKTecnologia.estagio.DTO.ProdutoResponseDTO;
import OAKTecnologia.estagio.controllers.CadastroController;
import OAKTecnologia.estagio.entities.ProdutoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
class CadastroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CadastroService cadastroService;

    @InjectMocks
    private CadastroController cadastroController;

    private ProdutoEntity produto;

    @BeforeEach
    void setUp() {
      // Criando o ProdutoEntity diretamente, sem builder
      produto = new ProdutoEntity(null, "Produto Teste", "Descrição do Produto Teste", 100.0, true);
  }


    @Test
    void testCadastrarProduto_Success() throws Exception {
        ProdutoResponseDTO response = ProdutoResponseDTO.builder()
                                                        .produtoName("Produto Teste")
                                                        .preco(100.0)
                                                        .build();

        when(cadastroService.cadastrarProduto(produto)).thenReturn(response);

        mockMvc.perform(post("/cadastro/produto")
                .contentType("application/json")
                .content("{ \"produtoName\": \"Produto Teste\", \"descricao\": \"Descrição do Produto Teste\", \"preco\": 100.0, \"disponivel\": true }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.produtoName").value("Produto Teste"))
                .andExpect(jsonPath("$.preco").value(100.0))
                .andExpect(jsonPath("$.descricao").value("Descrição do Produto Teste"));
    }

    @Test
    void testCadastrarProduto_Error() throws Exception {
        when(cadastroService.cadastrarProduto(produto)).thenThrow(new RuntimeException("Erro de cadastro"));

        mockMvc.perform(post("/cadastro/produto")
                .contentType("application/json")
                .content("{ \"produtoName\": \"Produto Teste\", \"descricao\": \"Descrição do Produto Teste\", \"preco\": 100.0, \"disponivel\": true }"))
                .andExpect(status().isInternalServerError());
    }
}
