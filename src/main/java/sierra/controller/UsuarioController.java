package sierra.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/usuarios")
	public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
		return ResponseEntity.ok().body(this.usuarioService.listarUsuarios());
	}
	
	
}
