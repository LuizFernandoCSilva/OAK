package OAKTecnologia.estagio.entities;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Produto")
@Data
@Schema(description = "Entidade que representa um produto")
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @NotBlank()
  @Schema(description = "Nome do produto", example = "Coca-Cola")
  private String produtoName;

  @NotBlank()
  @Schema(description = "Descrição do produto", example = "Refrigerante")
  private String descricao;

  @Positive
  @Schema(description = "Preço do produto", example = "5.00")
  private double price;

  @Schema(description = "Disponibilidade do produto", example = "true")
  private boolean disponivel;


}
