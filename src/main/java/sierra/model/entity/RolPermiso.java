package sierra.model.entity;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
/*
@Entity
@Table(name = "Roles_Permisos")*/
public class RolPermiso implements Serializable {
	
	private static final long serialVersionUID = 8817966378439174448L;
	@EmbeddedId
    private RolPermisoPK id;

    public RolPermisoPK getId() {
        return id;
    }

    public void setId(RolPermisoPK id) {
        this.id = id;
    }
}
