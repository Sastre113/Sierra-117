/**
 * 
 */
package sierra.service;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Table;
import sierra.model.entity.HistoricoCambios;
import sierra.repository.IHistoricoRepository;

@Service
public class HistoricoService implements IHistoricoService {

	private EntityManager entityManager;
	private IHistoricoRepository historicoRepository;

	public HistoricoService(EntityManager entityManager, @Qualifier("loggerDb") IHistoricoRepository historicoRepository) {
		this.entityManager = entityManager;
		this.historicoRepository = historicoRepository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public <T, K> void registrarCambios(Serializable entityConCambios, K id) {
		Serializable entityOriginal = this.entityManager.find(entityConCambios.getClass(), id);

		String nombreTabla = this.obtenerNombreTabla(entityConCambios.getClass());
		String nombreEntidad = entityConCambios.getClass().getSimpleName();

		Method[] methods = entityOriginal.getClass().getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().contains("get")) {
				T valorEntityOriginal = this.invocarGet(method, entityOriginal);
				T valorEntityConCambios = this.invocarGet(method, entityConCambios);

				if (this.hayCambios(valorEntityOriginal, valorEntityConCambios)) {
					HistoricoCambios historicoEntity = new HistoricoCambios();
					historicoEntity.setIdHistorico(UUID.randomUUID().toString());
					historicoEntity.setId(id.toString());
					historicoEntity.setNombreTabla(nombreTabla);
					historicoEntity.setNombreEntidad(nombreEntidad);
					historicoEntity.setColumna(this.obtenerNombreColumna(method.getName()));
					historicoEntity.setValorAnterior(valorEntityOriginal != null ? valorEntityOriginal.toString() : null);
					historicoEntity.setValorPosterior(valorEntityConCambios != null ? valorEntityConCambios.toString() : null);
					historicoEntity.setFechaCambio(Instant.now());
	
					this.historicoRepository.saveAndFlush(historicoEntity);
				}
			}
		}
	}

	private String obtenerNombreColumna(String methodName) {
		String nombreSinGet = methodName.replaceFirst("^get", "").substring(0, 1).toLowerCase()
				+ methodName.substring(4);
		boolean capitalizar = true;

		StringBuilder resultado = new StringBuilder();
		for (char letra : nombreSinGet.toCharArray()) {
			if (capitalizar && Character.isLetter(letra)) {
				resultado.append(Character.toUpperCase(letra));
				capitalizar = false;
			} else if (Character.isUpperCase(letra)) {
				resultado.append(" ").append(letra);
			} else {
				resultado.append(letra);
			}
		}

		return resultado.toString().trim();
	}

	private <T> boolean hayCambios(T valorEntityOriginal, T valorEntityConCambios) {
		if (valorEntityOriginal == null) {
			return valorEntityConCambios != null;
		}

		return !valorEntityOriginal.equals(valorEntityConCambios);
	}

	private <T> String obtenerNombreTabla(Class<T> clazz) {
		if (clazz.isAnnotationPresent(Entity.class)) {
			Table tabla = clazz.getAnnotation(Table.class);

			if (tabla != null) {
				return tabla.name();
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private <T> T invocarGet(Method method, Serializable entity) {
		try {
			return (T) method.invoke(entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}