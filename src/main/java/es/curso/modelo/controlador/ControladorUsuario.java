package es.curso.modelo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.curso.modelo.entidad.Usuario;
import es.curso.modelo.negocio.GestorUsuario;

@Controller
@ComponentScan(basePackages = {"es.curso"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"es.curso.modelo.persistencia"})
public class ControladorUsuario {

		
	@RequestMapping("verlogin")
	public String verlogin() {
		return "login";
	}
	
	@Autowired
	GestorUsuario gu;
	@Autowired
	Usuario usuario;
	
	
	@PostMapping("acceder")
	public String doSingup(@RequestParam("user") String username,
							@RequestParam("pass") String password,
							Model model) {
		
		usuario.setUsuario(username);		
		if(gu.validarUsuario(username, password)) {
			
		return "redirect:/listaVideojuegos";
		}else {
			String error = "Usuario con nombre: " + username +
					" y password " + password + " no existe";
			model.addAttribute("error",error);
			return "redirect:/index.html";
		}
		
		
		
		
	}
	
	
	
	
}


