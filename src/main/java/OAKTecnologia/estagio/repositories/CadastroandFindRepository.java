package OAKTecnologia.estagio.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import OAKTecnologia.estagio.entities.ProdutoEntity;

public interface CadastroandFindRepository extends JpaRepository<ProdutoEntity, UUID> {
  @Query("SELECT p.produtoName, p.price FROM ProdutoEntity p ORDER BY p.price ASC")
  List<Object[]> findProdutoNameAndPriceOrderedByPriceAsc();
}
