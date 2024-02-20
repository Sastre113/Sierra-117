/**
 * 
 */
package sierra.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sierra.model.entity.HistoricoCambios;

@Repository
//@Qualifier("loggerDb")
public interface IHistoricoRepository extends JpaRepository<HistoricoCambios, String> {

}
