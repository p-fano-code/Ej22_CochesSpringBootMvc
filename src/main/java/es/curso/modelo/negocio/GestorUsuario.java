package es.curso.modelo.negocio;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.curso.modelo.entidad.Usuario;
import es.curso.modelo.persistencia.DaoUsuario;


@Service
public class GestorUsuario {

	@Autowired
	private DaoUsuario daoUsuario;
	
	@Transactional
	public boolean validarUsuario(String user, String pass) {
		Optional<Usuario> usuario = daoUsuario.findByUsuario(user);
		return usuario.isPresent() ? true : false;
	}
}
