package projeto.spring.data.aula.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import projeto.spring.data.aula.model.UsuarioSpringData;

/*Para cada classe persistente é feito um repository*/

@Repository
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long> {
	//realiando consultas customizadas com @Query, criando um método de interface e uma query com JPQL na anotação
	//os parâmetros são passado com ? e o número referente a eles
	@Query(value = "select p from UsuarioSpringData p where p.nome like %?1%")
	public List<UsuarioSpringData> buscaPorNome(String nome);
	
	@Query(value = "select p from UsuarioSpringData p where p.nome = :paramnome")
	public UsuarioSpringData buscaPorNomeParam(@Param("paramnome") String paramnome);
	
	
	//delete condicional, alem da query customizada é necessário inicar uma transação para aoperação no bd com a anotação @Transactional
	//e com a anotação @Modifying informa que esse método vai realizar uma alteração no banco de dados
	@Modifying
	@Transactional
	@Query(value = "delete from UsuarioSpringData p where p.nome = ?1")
	public void deletePorNome(String nome);
	
	
	//update condicional
	@Modifying
	@Transactional
	@Query(value = "update UsuarioSpringData p set p.email = ?1 where p.nome = ?2")
	public void updateEmailPorNome(String email, String nome);
}
		
