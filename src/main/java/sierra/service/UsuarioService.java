/**
 * 
 */
package sierra.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import sierra.model.dto.PeticionCrearUsuarioDTO;
import sierra.model.dto.UsuarioDTO;
import sierra.model.entity.Usuario;
import sierra.repository.IUsuarioRepository;


@Service
public class UsuarioService implements IUsuarioService {

	private final IUsuarioRepository usuarioRepository;

    public UsuarioService(IUsuarioRepository usuarioRepository) {
        // Haciendo un cast al tipo específico
        this.usuarioRepository = usuarioRepository;
    }

	@Override
	public UsuarioDTO getUsuario(String nif) {
		return this.mapUsuarioDTO(this.usuarioRepository.findById(nif).orElseThrow());
	}

	@Override
	public UsuarioDTO crearUsuario(PeticionCrearUsuarioDTO peticionDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UsuarioDTO> listarUsuarios() {
		List<UsuarioDTO> listaUsuarios = new ArrayList<>();
		this.usuarioRepository.findAll().forEach(usuarioEntity -> listaUsuarios.add(this.mapUsuarioDTO(usuarioEntity)));
		return listaUsuarios;
	}

	@Override
	public UsuarioDTO eliminarUsuario(String idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private UsuarioDTO mapUsuarioDTO(Usuario usuarioEntity) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		
		usuarioDTO.setNif(usuarioEntity.getNif());
		usuarioDTO.setNombre(usuarioEntity.getNombre());
		usuarioDTO.setPrimerApellido(usuarioEntity.getPrimerApellido());
		usuarioDTO.setFechaNacimiento(usuarioEntity.getFechaNacimiento());

		return usuarioDTO;
	}

}
