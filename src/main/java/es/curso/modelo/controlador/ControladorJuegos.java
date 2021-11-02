package es.curso.modelo.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.curso.modelo.entidad.Videojuego;
import es.curso.modelo.persistencia.DaoVideojuego;

@Controller
@ComponentScan(basePackages = {"es.curso"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"es.curso.modelo.persistencia"})
public class ControladorJuegos {

	@Autowired
	DaoVideojuego dv;
	
	@PostMapping("listaVideojuegos")
	public ModelAndView verListaJuegos(@RequestParam("user") String user,
			 				     @RequestParam("pass") String pass){		
		ModelAndView m = new ModelAndView("listaVideojuegos"); 
		List<Videojuego> listado = dv.findAll();
		m.addObject("listado", listado);
		return m;
	}
	
	@RequestMapping("adicionar")
	public String veradicionar() {
		return "adicionar";
	}
	
	@PostMapping("addJuego")
	public ModelAndView addJuego(@RequestParam("nombre") String nombre,
						   @RequestParam("compania") String compania,
						   @RequestParam("nota") int nota) {
		Videojuego vadd = new Videojuego();
		vadd.setNombre(nombre);
		vadd.setCompania(compania);
		vadd.setNotaMedia(nota);
		
		ModelAndView m = new ModelAndView("listaVideojuegos");
		dv.save(vadd);
		List<Videojuego> listado = dv.findAll();
		m.addObject("listado", listado);
		
		return m;
		
	}
	
	@PostMapping("detalle")
	public String verDetalle(@RequestParam("id") String id, HttpServletRequest r){
		int newId = Integer.parseInt(id);
	
		
		if(dv.findById(newId).isEmpty() != true)  {
			r.setAttribute("j", dv.findById(newId).get());
			return "detalleJuego";
		}else return "errorDetalle";
	}
}
