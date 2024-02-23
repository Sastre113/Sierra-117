/**
 * 
 */
package sierra.service;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

import sierra.model.dto.Historificable;
import sierra.model.entity.HistoricoCambios;

public interface IHistoricoService {

	public <T,K> void registrarCambiosV1(Serializable entityConCambios, K id);
	public <T, K>  void registrarCambiosV2(Serializable entityConCambios);
	public <V, ID, R extends Serializable>  void registrarCambiosV3(Serializable entityConCambios, Function<Historificable, R> mapHistoricFunction);
	public List<HistoricoCambios> getHistorico();
}
