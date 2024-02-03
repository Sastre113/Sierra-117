/**
 * 
 */
package sierra.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sierra.model.dto.PeticionCrearUsuarioDTO;
import sierra.model.dto.PeticionModificarUsuarioDTO;
import sierra.model.dto.UsuarioDTO;
import sierra.model.entity.Usuario;
import sierra.repository.IUsuarioRepository;


@Service
public class UsuarioService implements IUsuarioService {

	private final IUsuarioRepository usuarioRepository;
	private final IHistoricoService historicoService;

    public UsuarioService(IUsuarioRepository usuarioRepository, IHistoricoService historicoService) {
        this.usuarioRepository = usuarioRepository;
        this.historicoService = historicoService;
    }

	@Override
	public UsuarioDTO getUsuario(String nif) {
		return this.mapUsuarioDTO(this.usuarioRepository.findById(nif).orElseThrow());
	}

	@Override
	@Transactional
	public UsuarioDTO crearUsuario(PeticionCrearUsuarioDTO peticionDTO) {
		Usuario usuarioEntity = new Usuario();
		usuarioEntity.setNif(peticionDTO.getNif());
		usuarioEntity.setNombre(peticionDTO.getNombre()); 
		usuarioEntity.setPrimerApellido(peticionDTO.getPrimerApellido()); 
		usuarioEntity.setSegundoApellido(peticionDTO.getSegundoApellido()); 
		usuarioEntity.setFechaNacimiento(peticionDTO.getFechaNacimiento());
		
		this.usuarioRepository.saveAndFlush(usuarioEntity);
		return this.mapUsuarioDTO(usuarioEntity);
	}

	@Override
	@Transactional
	public UsuarioDTO modificarUsuario(PeticionModificarUsuarioDTO peticionDTO) {
		Usuario usuarioEntity = this.usuarioRepository.findById(peticionDTO.getNif()).orElseThrow();
		
		usuarioEntity.setNombre(peticionDTO.getNombre());
		usuarioEntity.setPrimerApellido(peticionDTO.getPrimerApellido());
		usuarioEntity.setSegundoApellido(peticionDTO.getSegundoApellido());
		usuarioEntity.setFechaNacimiento(peticionDTO.getFechaNacimiento());
		
		this.historicoService.registrarCambios(usuarioEntity, usuarioEntity.getNif());
		this.usuarioRepository.save(usuarioEntity);
		return this.mapUsuarioDTO(usuarioEntity);
	}

	@Override
	public List<UsuarioDTO> listarUsuarios() {
		List<UsuarioDTO> listaUsuarios = new ArrayList<>();
		this.usuarioRepository.findAll().forEach(usuarioEntity -> listaUsuarios.add(this.mapUsuarioDTO(usuarioEntity)));
		return listaUsuarios;
	}

	@Override
	public UsuarioDTO eliminarUsuario(String idUsuario) {
		Usuario usuarioEntity = this.usuarioRepository.findById(idUsuario).orElseThrow();
		this.usuarioRepository.deleteById(idUsuario);
		return this.mapUsuarioDTO(usuarioEntity);
	}
	
	private UsuarioDTO mapUsuarioDTO(Usuario usuarioEntity) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		
		usuarioDTO.setNif(usuarioEntity.getNif());
		usuarioDTO.setNombre(usuarioEntity.getNombre());
		usuarioDTO.setPrimerApellido(usuarioEntity.getPrimerApellido());
		usuarioDTO.setSegundoApellido(usuarioEntity.getSegundoApellido());
		usuarioDTO.setFechaNacimiento(usuarioEntity.getFechaNacimiento());

		return usuarioDTO;
	}

}