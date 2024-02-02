package sierra.model.entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Roles")
public class Rol implements Serializable {

	private static final long serialVersionUID = -5696229525571760023L;
	
	@Id
	@Column(name = "id_rol")
	private String idRol;
	@Column(name = "rol_nombre")
	private String rolNombre;
	@Column(name = "activo")
	private int activo;

	@OneToMany(mappedBy = "rol")
	private Set<UsuarioRol> usuariosRoles;

	@ManyToMany(mappedBy = "roles")
	private Set<Permiso> permisos;

	public String getIdRol() {
		return idRol;
	}

	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

	public String getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(String rolNombre) {
		this.rolNombre = rolNombre;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public Set<UsuarioRol> getUsuariosRoles() {
		return usuariosRoles;
	}

	public void setUsuariosRoles(Set<UsuarioRol> usuariosRoles) {
		this.usuariosRoles = usuariosRoles;
	}

	public Set<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(Set<Permiso> permisos) {
		this.permisos = permisos;
	}
}