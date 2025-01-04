package OAKTecnologia.estagio;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import OAKTecnologia.estagio.DTO.ProdutoResponseDTO;
import OAKTecnologia.estagio.entities.ProdutoEntity;
import OAKTecnologia.estagio.repositories.CadastroandFindRepository;
import OAKTecnologia.estagio.service.CadastroService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class CadastroServiceTest {

    @Mock
    private CadastroandFindRepository cadastroandFindRepository;

    @InjectMocks
    private CadastroService cadastroService;

    private ProdutoEntity produto;

    @BeforeEach
    void setUp() {
        // Criando o ProdutoEntity diretamente, sem builder (caso seu ProdutoEntity não tenha um builder)
        produto = new ProdutoEntity(null, "Produto Teste", "Descrição do Produto Teste", 100.0, true);
    }

    @Test
    void testCadastrarProduto_Success() {
        // Mocking o método save do repositório
        when(cadastroandFindRepository.save(produto)).thenReturn(produto);

        // Chama o método de serviço
        ProdutoResponseDTO result = cadastroService.cadastrarProduto(produto);

        // Verifica o resultado
        assertNotNull(result);
        assertEquals("Produto Teste", result.getProdutoName());
        assertEquals(100.0, result.getPreco());
    }

    @Test
    void testCadastrarProduto_Exception() {
        // Mocking o repositório para lançar uma exceção
        when(cadastroandFindRepository.save(produto)).thenThrow(new RuntimeException("Erro de banco"));

        // Chama o método de serviço e verifica o tratamento de exceções
        ProdutoResponseDTO result = cadastroService.cadastrarProduto(produto);

        // Verifica se o resultado foi nulo devido à exceção
        assertNull(result);
    }
}