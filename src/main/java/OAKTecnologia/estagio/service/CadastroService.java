package OAKTecnologia.estagio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import OAKTecnologia.estagio.DTO.ProdutoResponseDTO;
import OAKTecnologia.estagio.entities.ProdutoEntity;
import OAKTecnologia.estagio.repositories.CadastroandFindRepository;

@Service
public class CadastroService {

  @Autowired
  private CadastroandFindRepository cadastroRepository;
  
  public ProdutoResponseDTO cadastrarProduto(ProdutoEntity produto) {
    try{
      ProdutoEntity savedProduto = cadastroRepository.save(produto);
      return ProdutoResponseDTO.builder()
        .produtoName(savedProduto.getProdutoName())
        .preco(savedProduto.getPrice())
        .build();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
