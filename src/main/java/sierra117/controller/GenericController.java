/**
 * 
 */
package sierra117.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Miguel Á. Sastre <sastre113@gmail.com>
 * @version 21:53:00 - 12/12/2023
 *
 */
@RestController
public class GenericController {

	@GetMapping(path = "/generic/{nameTable}")
	public <T> ResponseEntity<String> getTable(@PathVariable String nameTable){
		return ResponseEntity.ok("Tabla " + nameTable + " obtenida!");
	}
	
	@PostMapping(path = "/generic/{nameTable}")
	public <T> ResponseEntity<String> postTable(@PathVariable String nameTable){
		return ResponseEntity.ok("Post " + nameTable + " method http!");
	}
	
	@DeleteMapping(path = "/generic/{nameTable}") 
	public ResponseEntity<String> deleteTable(@PathVariable String nameTable){
		return ResponseEntity.ok("Borrado "+ nameTable + " con exito!");
	}
}
