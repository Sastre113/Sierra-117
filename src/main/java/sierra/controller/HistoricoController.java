package sierra.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sierra.model.entity.HistoricoCambios;
import sierra.service.IHistoricoService;

@RestController
public class HistoricoController {

	private IHistoricoService historicoService;

	public HistoricoController(IHistoricoService historicoService) {
		super();
		this.historicoService = historicoService;
	}
	
	@GetMapping("/historico")
	public ResponseEntity<List<HistoricoCambios>> getHistorico() {
		return ResponseEntity.ok().body(this.historicoService.getHistorico());
	}
}
