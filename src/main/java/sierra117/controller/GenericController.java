/**
 * 
 */
package sierra117.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Miguel Á. Sastre <sastre113@gmail.com>
 * @version 21:53:00 - 12/12/2023
 *
 */
@RestController
public class GenericController {

	@GetMapping(path = "/generic/{nameTable}")
	public <T> ResponseEntity<T> getTable(@PathVariable String nameTable){
		return null;
	}
	
}
