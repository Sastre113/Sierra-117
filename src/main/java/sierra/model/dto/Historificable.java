/**
 * 
 */
package sierra.model.dto;

import java.time.Instant;

/**
 * 
 */
public class Historificable {

	private String idHistorico;
	private String id;
	private String nombreTabla;
	private String nombreEntidad;
	private String columna;
	private String valorAnterior;
	private String valorPosterior;
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