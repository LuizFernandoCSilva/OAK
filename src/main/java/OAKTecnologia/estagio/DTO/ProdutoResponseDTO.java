package OAKTecnologia.estagio.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoResponseDTO {
  private String produtoName;
  private double preco;
  
} 
