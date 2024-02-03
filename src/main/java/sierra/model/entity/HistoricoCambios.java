/**
 * 
 */
package sierra.model.entity;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Historico_cambios")
public class HistoricoCambios implements Serializable {

	private static final long serialVersionUID = -1875917360731351013L;

	@Id
	@Column(name = "id_historico")
	private String idHistorico;

	@Column(name = "id")
	private String id;

	@Column(name = "nombre_tabla")
	private String nombreTabla;

	@Column(name = "nombre_entidad")
	private String nombreEntidad;

	@Column(name = "columna")
	private String columna;

	@Column(name = "valor_anterior")
	private String valorAnterior;

	@Column(name = "valor_posterior")
	private String valorPosterior;

	@Column(name = "fecha_cambio")
	private Instant fechaCambio;

	public String getIdHistorico() {
		return this.idHistorico;
	}

	public void setIdHistorico(String idHistorico) {
		this.idHistorico = idHistorico;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreTabla() {
		return this.nombreTabla;
	}

	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}

	public String getNombreEntidad() {
		return this.nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	public String getColumna() {
		return this.columna;
	}

	public void setColumna(String columna) {
		this.columna = columna;
	}

	public String getValorAnterior() {
		return this.valorAnterior;
	}

	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public String getValorPosterior() {
		return this.valorPosterior;
	}

	public void setValorPosterior(String valorPosterior) {
		this.valorPosterior = valorPosterior;
	}

	public Instant getFechaCambio() {
		return this.fechaCambio;
	}

	public void setFechaCambio(Instant fechaCambio) {
		this.fechaCambio = fechaCambio;
	}
}