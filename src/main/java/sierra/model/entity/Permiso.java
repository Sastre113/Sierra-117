package sierra.model.entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
/*
@Entity
@Table(name = "Permisos")*/
public class Permiso implements Serializable {

	private static final long serialVersionUID = -184874401871960302L;
	
	@Id
	@Column(name = "id_permiso")
	private String idPermiso;
	@Column(name = "permiso_nombre")
	private String permisoNombre;

	@OneToMany(mappedBy = "permiso")
	private Set<RolPermiso> rolesPermisos;

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

	public Set<RolPermiso> getRolesPermisos() {
		return rolesPermisos;
	}

	public void setRolesPermisos(Set<RolPermiso> rolesPermisos) {
		this.rolesPermisos = rolesPermisos;
	}
}