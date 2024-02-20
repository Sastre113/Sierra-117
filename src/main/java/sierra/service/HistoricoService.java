/**
 * 
 */
package sierra.service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Table;
import sierra.model.entity.HistoricoCambios;
import sierra.repository.IHistoricoRepository;

@Service
public class HistoricoService implements IHistoricoService {

	private EntityManager entityManager;
	private IHistoricoRepository historicoRepository;

	public HistoricoService(EntityManager entityManager, IHistoricoRepository historicoRepository) {
		this.entityManager = entityManager;
		this.historicoRepository = historicoRepository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public <T, K> void registrarCambios(Serializable entityConCambios, K id) {
		Serializable entityOriginal = this.entityManager.find(entityConCambios.getClass(), id);

		String nombreTabla = this.obtenerNombreTabla(entityConCambios.getClass());
		String nombreEntidad = entityConCambios.getClass().getSimpleName();
		
		Field[] fields = entityOriginal.getClass().getDeclaredFields();
		
		for(Field field : fields) {
			Method getter = this.getMethodGetter(entityOriginal.getClass(), field.getName());
			
			if (getter != null) {
				T valorEntityOriginal = this.invocarGet(getter, entityOriginal);
				T valorEntityConCambios = this.invocarGet(getter, entityConCambios);

				if (this.hayCambios(valorEntityOriginal, valorEntityConCambios)) {
					HistoricoCambios historicoEntity = new HistoricoCambios();
					historicoEntity.setIdHistorico(UUID.randomUUID().toString());
					historicoEntity.setId(id.toString());
					historicoEntity.setNombreTabla(nombreTabla);
					historicoEntity.setNombreEntidad(nombreEntidad);
					historicoEntity.setColumna(field.getDeclaredAnnotation(Column.class).name());
					historicoEntity
							.setValorAnterior(valorEntityOriginal != null ? valorEntityOriginal.toString() : null);
					historicoEntity
							.setValorPosterior(valorEntityConCambios != null ? valorEntityConCambios.toString() : null);
					historicoEntity.setFechaCambio(Instant.now());

					this.historicoRepository.saveAndFlush(historicoEntity);
				}
			}

		}
		
		
		/** TODO @Deprecated
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
		}*/
	}
	

	@Override
	public List<HistoricoCambios> getHistorico() {
		return this.historicoRepository.findAll();
	}
	
	@Deprecated(forRemoval = true)
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
	
	private Method getMethodGetter(Class<? extends Serializable> clazz, String nameMethod) {
		String getterName = "get" + nameMethod.substring(0,1).toUpperCase() + nameMethod.substring(1);
		try {
			return clazz.getDeclaredMethod(getterName);
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("No existe el método " + nameMethod);
			return null;
		}
	}
}