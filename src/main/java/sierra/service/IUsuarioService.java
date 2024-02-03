/**
 * 
 */
package sierra.service;

import java.util.List;

import sierra.model.dto.PeticionCrearUsuarioDTO;
import sierra.model.dto.UsuarioDTO;

/**
 * 
 */
public interface IUsuarioService {

	public UsuarioDTO getUsuario(String nif);
	public UsuarioDTO crearUsuario(PeticionCrearUsuarioDTO peticionDTO);
	public List<UsuarioDTO> listarUsuarios();
	public UsuarioDTO eliminarUsuario(String idUsuario);
}
