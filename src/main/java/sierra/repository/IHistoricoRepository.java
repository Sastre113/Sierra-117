/**
 * 
 */
package sierra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sierra.model.entity.HistoricoCambios;

@Repository
public interface IHistoricoRepository extends JpaRepository<HistoricoCambios, String> {

}
