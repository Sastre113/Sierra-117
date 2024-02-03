package sierra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sierra.model.entity.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, String> {

}
