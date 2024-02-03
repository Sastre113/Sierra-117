package sierra.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sierra.model.dto.PeticionCrearUsuarioDTO;
import sierra.model.dto.PeticionModificarUsuarioDTO;
import sierra.model.dto.UsuarioDTO;
import sierra.service.IUsuarioService;

@RestController
public class UsuarioController {

	private IUsuarioService usuarioService;
	
	public UsuarioController(IUsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	@GetMapping("/usuario/{nif}")
	public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable String nif) {
		return ResponseEntity.ok().body(this.usuarioService.getUsuario(nif));
	}
	
	@PostMapping("/usuario")
	public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody PeticionCrearUsuarioDTO peticionDTO) {
		return ResponseEntity.ok().body(this.usuarioService.crearUsuario(peticionDTO));
	}
	
	@PutMapping("/usuario")
	public ResponseEntity<UsuarioDTO> modificarUsuario(@RequestBody PeticionModificarUsuarioDTO peticionDTO) {
		return ResponseEntity.ok().body(this.usuarioService.modificarUsuario(peticionDTO));
	}
	  
	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
		return ResponseEntity.ok().body(this.usuarioService.listarUsuarios());
	}
	
	@DeleteMapping("/usuario/{nif}")
	public ResponseEntity<UsuarioDTO> eliminarUsuario(@PathVariable String nif) {
		return ResponseEntity.ok().body(this.usuarioService.eliminarUsuario(nif));
	}
}
