/**
 * 
 */
package sierra.service;

import java.io.Serializable;

public interface IHistoricoService {

	public <T,K> void registrarCambios(Serializable entityConCambios, K id);

}
