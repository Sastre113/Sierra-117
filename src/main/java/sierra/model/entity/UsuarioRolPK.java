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
 * @version 0:34:48 - 02/02/2024
 *
 */
@Embeddable
public class UsuarioRolPK implements Serializable {

	private static final long serialVersionUID = 6692765346048419723L;

	@ManyToOne
	private String nif;

	@ManyToOne
	private Rol rol;

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nif, rol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioRolPK other = (UsuarioRolPK) obj;
		return Objects.equals(nif, other.nif) && Objects.equals(rol, other.rol);
	}
}