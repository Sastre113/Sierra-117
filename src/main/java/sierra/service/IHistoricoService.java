/**
 * 
 */
package sierra.service;

import java.io.Serializable;

public interface IHistoricoService {

	public <T> void registrarCambios(Serializable entityConCambios, String id);

}
