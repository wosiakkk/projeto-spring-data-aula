package projeto.spring.data.aula;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.aula.dao.InterfaceSpringDataUser;
import projeto.spring.data.aula.dao.InterfaceTelefone;
import projeto.spring.data.aula.model.Telefone;
import projeto.spring.data.aula.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class) // anotação para a integração do spring com junit
@ContextConfiguration(locations = { "classpath:META-INF/spring-config.xml" }) // definindo o caminho de configuração
																				// spring
public class AppSpringDataTest {

	@Autowired // anotação de injeção de dependencia, alternativa ao @inject
	private InterfaceSpringDataUser interfaceSpringDataUser;

	@Autowired
	private InterfaceTelefone interfaceTelefone;
	
	@Test
	public void testeInsert() {

		UsuarioSpringData usuario = new UsuarioSpringData();
		usuario.setEmail("teste@teste.com");
		usuario.setNome("Jose");
		usuario.setIdade(20);
		usuario.setLogin("teste123");
		usuario.setSenha("senha123");

		interfaceSpringDataUser.save(usuario);

		System.out.println("Total de usuário cadastrados: " + interfaceSpringDataUser.count());

	}

	@Test
	public void testeConsulta() {

		// O find by id do spring retorna um objeto Optional do java util, por isso é
		// necessário um obj desse tipo receber o retorno
		Optional<UsuarioSpringData> usuario = interfaceSpringDataUser.findById(4L);
		// para a cessar os métodos do objeto do tipo UsuarioSpringData, usamos o método
		// get() do optional que retorna o objeto do tipo, para dai ter acesso aos
		// gettres do usuário
		System.out.println(usuario.get().getNome());
		System.out.println(usuario.get().getLogin());
		System.out.println(usuario.get().getEmail());
		System.out.println(usuario.get().getSenha());
		System.out.println(usuario.get().getIdade());
		System.out.println(usuario.get().getId());
		
		for (Telefone telefone : usuario.get().getTelefones()) {
			System.out.println(telefone.getTipo());
			System.out.println(telefone.getNumero());
			System.out.println(telefone.getId());
			System.out.println(telefone.getUsuario().getNome());
			System.out.println("-----------------------------");
		}
		
	}

	@Test
	public void testeConsultaTodos() {
		// o find all retorna um objeto iterable, que é um tipo de lista
		Iterable<UsuarioSpringData> lista = interfaceSpringDataUser.findAll();
		for (UsuarioSpringData usuarioSpringData : lista) {
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getId());
			System.out.println("********************************");
		}
	}

	@Test
	public void testeUpdate() {
		// consultando o objeto
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(3L);
		// pegando0 o objeto UsuarioSpringata de dentro do objeto Optional
		UsuarioSpringData data = usuarioSpringData.get();
		// atualizando o nome
		data.setNome("Terceiro UPDATE");
		interfaceSpringDataUser.save(data);
	}

	@Test
	public void testeDelete() {
		// primeira forma
		// interfaceSpringDataUser.deleteById(5L);
		// segunda
		Optional<UsuarioSpringData> usuario = interfaceSpringDataUser.findById(5L);
		interfaceSpringDataUser.delete(usuario.get());
	}

	// query customizada para like
	@Test
	public void testeConsultaNome() {

		List<UsuarioSpringData> lista = interfaceSpringDataUser.buscaPorNome("Segundo");

		for (UsuarioSpringData usuarioSpringData : lista) {
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getId());
			System.out.println("********************************");
		}

	}

	// query customizada nome específico
	@Test
	public void testeConsultaNomeParam() {

		UsuarioSpringData usuarioSpringData = interfaceSpringDataUser.buscaPorNomeParam("Quarto Teste");

		System.out.println(usuarioSpringData.getNome());
		System.out.println(usuarioSpringData.getLogin());
		System.out.println(usuarioSpringData.getEmail());
		System.out.println(usuarioSpringData.getSenha());
		System.out.println(usuarioSpringData.getIdade());
		System.out.println(usuarioSpringData.getId());

	}

	@Test
	public void testeDeletePorNome() {
		interfaceSpringDataUser.deletePorNome("Eita");
	}
	
	@Test
	public void testeUpdateEmailPorNome() {
		interfaceSpringDataUser.updateEmailPorNome("brunofwosiak@gmail.com", "Quarto Teste");
	}

	//Testes de telefone
	@Test
	public void testeInsertTelefone() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);
		
		UsuarioSpringData buscado = usuarioSpringData.get();
		
		Telefone telefone = new Telefone();
		telefone.setTipo("celular");
		telefone.setNumero("4196658974");
		telefone.setUsuario(buscado);
		
		interfaceTelefone.save(telefone);
	}
	
}
