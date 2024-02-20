/**
 * 
 */
package sierra.service;

import java.io.Serializable;
import java.util.List;

import sierra.model.entity.HistoricoCambios;

public interface IHistoricoService {

	public <T,K> void registrarCambios(Serializable entityConCambios, K id);
	public List<HistoricoCambios> getHistorico();

}
