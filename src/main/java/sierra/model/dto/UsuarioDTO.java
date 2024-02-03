/**
 * 
 */
package sierra.model.dto;

import java.time.Instant;

public class UsuarioDTO {

	private String nif;
	private String nombre;
	private String primerApellido;
	private Instant fechaNacimiento;

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public Instant getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Instant fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}
