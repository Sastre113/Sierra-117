/**
 * 
 */
package sierra.model.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

/**
 * @author Miguel Á. Sastre <sastre113@gmail.com>
 * @version 0:31:51 - 02/02/2024
 *
 */

@Embeddable
public class RolPermisoPK implements Serializable {

	private static final long serialVersionUID = -9009681338000323096L;

	@ManyToOne
	private Rol rol;

	@ManyToOne
	private Permiso permiso;

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Permiso getPermiso() {
		return permiso;
	}

	public void setPermiso(Permiso permiso) {
		this.permiso = permiso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(permiso, rol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolPermisoPK other = (RolPermisoPK) obj;
		return Objects.equals(permiso, other.permiso) && Objects.equals(rol, other.rol);
	}
}