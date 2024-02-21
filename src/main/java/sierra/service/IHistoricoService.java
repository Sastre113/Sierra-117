/**
 * 
 */
package sierra.service;

import java.io.Serializable;
import java.util.List;

import sierra.model.entity.HistoricoCambios;

public interface IHistoricoService {

	public <T,K> void registrarCambiosV1(Serializable entityConCambios, K id);
	public <T, K>  void registrarCambiosV2(Serializable entityConCambios);
	public List<HistoricoCambios> getHistorico();
}
