package es.curso.modelo.negocio;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.curso.modelo.entidad.Usuario;
import es.curso.modelo.entidad.Videojuego;
import es.curso.modelo.persistencia.DaoVideojuego;

@Service
public class GestorVidojuegos {
	
	@Autowired
	private DaoVideojuego daoJuego;
	
	@Transactional
	public int insertar(Videojuego v) {
		return daoJuego.save(v).getId();
	}
	
	@Transactional
	public Videojuego modificar(Videojuego v) {
		return daoJuego.save(v);
	}
	@Transactional
	public void borrar(int id) {
		daoJuego.deleteById(id);
	}
	
}
