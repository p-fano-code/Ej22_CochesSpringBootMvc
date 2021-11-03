package es.curso.modelo.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.curso.modelo.entidad.Usuario;
import es.curso.modelo.entidad.Videojuego;
import es.curso.modelo.negocio.GestorUsuario;
import es.curso.modelo.negocio.GestorVidojuegos;
import es.curso.modelo.persistencia.DaoVideojuego;

@Controller
@ComponentScan(basePackages = {"es.curso"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"es.curso.modelo.persistencia"})
public class ControladorJuegos {

	@Autowired
	GestorVidojuegos gv;
	@Autowired
	Usuario user;
	
	@RequestMapping("listaVideojuegos")
	public ModelAndView verListaJuegos(){	
		List<Videojuego> listado = gv.listar();
		ModelAndView m = new ModelAndView("listaVideojuegos"); 		
		m.addObject("listado", listado);
		m.addObject("usuario", user);
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
		gv.insertar(vadd);
		List<Videojuego> listado = gv.listar();
		m.addObject("listado", listado);
		m.addObject("usuario", user);
		return m;
		
	}
	
	@PostMapping("detalle")
	public String verDetalle(@RequestParam("id") String id, HttpServletRequest r){
		int newId = Integer.parseInt(id);	
		
		if(gv.findById(newId) != null)  {
			r.setAttribute("j", gv.findById(newId));
			return "detalleJuego";
		}else return "errorDetalle";
	}
	
	@RequestMapping("verFormulario")
	public ModelAndView formulario() {
		ModelAndView mav = new ModelAndView("formularioJuego");
		mav.addObject("videojuego", new Videojuego());
		return mav;
	}
	@RequestMapping(value = "insertar", method = RequestMethod.POST)
	public ModelAndView insertar(@ModelAttribute Videojuego videojuego, Model model) {
		gv.insertar(videojuego);
		ModelAndView mav = new ModelAndView("redirect:/listaVideojuegos");
		return mav;
	}

	@RequestMapping(value = "modificar", method = RequestMethod.POST)
	public ModelAndView modificar(@ModelAttribute Videojuego videojuego) {
		gv.modificar(videojuego);
		ModelAndView mav = new ModelAndView("redirect:/listaVideojuegos");
		return mav;
	}

	@RequestMapping(value = "borrar", method = RequestMethod.POST)
	public ModelAndView borrar(@ModelAttribute Videojuego videojuego) {
		System.out.println(videojuego.getId());
		gv.borrar(videojuego.getId());
		ModelAndView mav = new ModelAndView("redirect:/listaVideojuegos");
		return mav;
	}
}
