/**
 * 
 */
package sierra.model.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * @author Miguel Á. Sastre <sastre113@gmail.com>
 * @version 0:34:48 - 02/02/2024
 *
 */
@Embeddable
public class UsuarioRolPK implements Serializable {

	private static final long serialVersionUID = 6692765346048419723L;

	@Column(name = "nif")
	private String nif;

	@Column(name = "id_rol")
	private String idRol;

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getIdRol() {
		return idRol;
	}

	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idRol, nif);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UsuarioRolPK)) {
			return false;
		}
		UsuarioRolPK other = (UsuarioRolPK) obj;
		return Objects.equals(idRol, other.idRol) && Objects.equals(nif, other.nif);
	}	
}