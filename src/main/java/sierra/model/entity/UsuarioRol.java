package sierra.model.entity;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;


@Entity
@Table(name = "Usuarios_Roles")
public class UsuarioRol implements Serializable {

	private static final long serialVersionUID = -2368836540446090390L;

	@EmbeddedId
    private UsuarioRolPK id;
	
	@ManyToOne
	@MapsId("nif")
	@JoinColumn(name = "nif")
	private Usuario usuario;
	
	@ManyToOne
	@MapsId("idRol")
	@JoinColumn(name = "id_rol")
	private Rol rol;

    public UsuarioRolPK getId() {
        return id;
    }

    public void setId(UsuarioRolPK id) {
        this.id = id;
    }
}