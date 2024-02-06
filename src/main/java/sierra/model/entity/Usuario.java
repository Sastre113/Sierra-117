package sierra.model.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "MainSchema", name = "UsuariosTb")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "nif")
	private String nif;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "primer_apellido")
	private String primerApellido;
	@Column(name = "segundo_apellido")
	private String segundoApellido;
	@Column(name = "fecha_nacimiento")
	private Instant fechaNacimiento;

	@OneToMany(mappedBy = "usuario")
	private Set<UsuarioRol> roles;

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public Instant getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Instant fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Set<UsuarioRol> getRoles() {
		return roles;
	}

	public void setRoles(Set<UsuarioRol> roles) {
		this.roles = roles;
	}

}