package projeto.spring.data.aula;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.aula.dao.InterfaceSpringDataUser;

@RunWith(SpringJUnit4ClassRunner.class) //anotação para a integração do spring com junit
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"}) //definindo o caminho de configuração spring
public class AppSpringDataTest {
	
	@Autowired //anotação de injeção de dependencia, alternativa ao @inject
	private InterfaceSpringDataUser interfaceSpringDataUser;
	
	
	@Test
	public void insert() {
		System.out.println("Iniciou o Spriong com sucesso");
	}
	
	
}
