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
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
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
	
	/**
	 * @version v1
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public <T, K> void registrarCambiosV1(Serializable entityConCambios, K id) {
		Serializable entityOriginal = this.entityManager.find(entityConCambios.getClass(), id);

		String nombreTabla = this.obtenerNombreTabla(entityConCambios.getClass());
		String nombreEntidad = entityConCambios.getClass().getSimpleName();
		
		Field[] fields = entityOriginal.getClass().getDeclaredFields();
		
		for(Field field : fields) {
			Optional<Method> optMethodGetter = this.getMethodGetter(entityOriginal.getClass(), field.getName());
			
			if (optMethodGetter.isPresent()) {
				T valorEntityOriginal = this.invocarGet(optMethodGetter.get(), entityOriginal);
				T valorEntityConCambios = this.invocarGet(optMethodGetter.get(), entityConCambios);

				if (this.hayCambios(valorEntityOriginal, valorEntityConCambios)) {
					HistoricoCambios historicoEntity = new HistoricoCambios();
					historicoEntity.setIdHistorico(UUID.randomUUID().toString());
					historicoEntity.setId(id.toString());
					historicoEntity.setNombreTabla(nombreTabla);
					historicoEntity.setNombreEntidad(nombreEntidad);
					historicoEntity.setColumna(field.getDeclaredAnnotation(Column.class).name());
					historicoEntity.setValorAnterior(valorEntityOriginal != null ? valorEntityOriginal.toString() : null);
					historicoEntity.setValorPosterior(valorEntityConCambios != null ? valorEntityConCambios.toString() : null);
					historicoEntity.setFechaCambio(Instant.now());

					this.historicoRepository.saveAndFlush(historicoEntity);
				}
			}

		}
	}
	


	/**
	 * @version v2
	 * @param entityConCambios
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
	public <T, K, R>  void registrarCambiosV2(Serializable entityConCambios, Function<T, R> mapHistoricFunction) {
		K id = this.getId(entityConCambios);
		Serializable entityOriginal = this.entityManager.find(entityConCambios.getClass(), id);

		String nombreTabla = this.obtenerNombreTabla(entityConCambios.getClass());
		String nombreEntidad = entityConCambios.getClass().getSimpleName();
		
		Field[] fields = entityOriginal.getClass().getDeclaredFields();
		
		for(Field field : fields) {
			Optional<Method> optMethodGetter = this.getMethodGetter(entityOriginal.getClass(), field.getName());
			
			if (optMethodGetter.isPresent()) {
				T valorEntityOriginal = this.invocarGet(optMethodGetter.get(), entityOriginal);
				T valorEntityConCambios = this.invocarGet(optMethodGetter.get(), entityConCambios);

				if (this.hayCambios(valorEntityOriginal, valorEntityConCambios)) {
					HistoricoCambios historicoEntity = new HistoricoCambios();
					historicoEntity.setIdHistorico(UUID.randomUUID().toString());
					historicoEntity.setId(id.toString());
					historicoEntity.setNombreTabla(nombreTabla);
					historicoEntity.setNombreEntidad(nombreEntidad);
					historicoEntity.setColumna(field.getDeclaredAnnotation(Column.class).name());
					historicoEntity.setValorAnterior(valorEntityOriginal != null ? valorEntityOriginal.toString() : null);
					historicoEntity.setValorPosterior(valorEntityConCambios != null ? valorEntityConCambios.toString() : null);
					historicoEntity.setFechaCambio(Instant.now());
					
					this.entityManager.persist(historicoEntity);
					this.entityManager.flush();
					//this.historicoRepository.saveAndFlush(historicoEntity);
				}
			}

		}
	}

	/**
	 * @param <K>
	 * @param entityConCambios
	 * @return
	 */
	private <K> K getId(Serializable entityConCambios) {
		for(Field field : entityConCambios.getClass().getDeclaredFields()) {
			if(field.getDeclaredAnnotation(Id.class) != null) {
				 Method getter = this.getMethodGetter(entityConCambios.getClass(), field.getName()).orElseThrow();
				return this.invocarGet(getter, entityConCambios);
			}
		}
		
		throw new RuntimeException("No hay atributo marcado como @Id");
	}

	@Override
	public List<HistoricoCambios> getHistorico() {
		return this.historicoRepository.findAll();
	}

	private <T> boolean hayCambios(T valorEntityOriginal, T valorEntityConCambios) {
		if (valorEntityOriginal == null) {
			return valorEntityConCambios != null;
		}

		return !valorEntityOriginal.equals(valorEntityConCambios);
	}

	private <T> String obtenerNombreTabla(Class<T> clazz) {
		if (!clazz.isAnnotationPresent(Entity.class)) {
			throw new RuntimeException("La clase no esta marcada como @Entity");
		}

		Table tabla = clazz.getAnnotation(Table.class);
		if (tabla != null) {
			return tabla.name();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	private <T> T invocarGet(Method method, Serializable entity) {
		try {
			return (T) method.invoke(entity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException("Error al intentar invocar el método Getter");
		}
	}
	
	private Optional<Method> getMethodGetter(Class<? extends Serializable> clazz, String nameMethod) {
		String getterName = "get" + nameMethod.substring(0,1).toUpperCase() + nameMethod.substring(1);
		try {
			return Optional.of(clazz.getDeclaredMethod(getterName));
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("No existe el método " + nameMethod);
			return Optional.empty();
		}
	}
}