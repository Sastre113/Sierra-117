package sierra.model.entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Permisos")
public class Permiso implements Serializable {

	private static final long serialVersionUID = -184874401871960302L;
	
	@Id
	@Column(name = "id_permiso")
	private String idPermiso;
	@Column(name = "permiso_nombre")
	private String permisoNombre;

	@ManyToMany
	@JoinTable(
			joinColumns = @JoinColumn(name = "id_rol"),
			inverseJoinColumns = @JoinColumn(name = "id_permiso")
	)
	private Set<Rol> roles;

	public String getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(String idPermiso) {
		this.idPermiso = idPermiso;
	}

	public String getPermisoNombre() {
		return permisoNombre;
	}

	public void setPermisoNombre(String permisoNombre) {
		this.permisoNombre = permisoNombre;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
}