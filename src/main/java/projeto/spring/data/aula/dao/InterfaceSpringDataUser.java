package projeto.spring.data.aula.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import projeto.spring.data.aula.model.UsuarioSpringData;

/*Para cada classe persistente é feito um repository*/

@Repository
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long> {}
		
