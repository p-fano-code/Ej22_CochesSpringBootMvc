package es.curso.modelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import es.curso.modelo.entidad.Usuario;
import es.curso.modelo.entidad.Videojuego;
import es.curso.modelo.negocio.GestorUsuario;
import es.curso.modelo.persistencia.DaoUsuario;
import es.curso.modelo.persistencia.DaoVideojuego;

@SpringBootApplication
public class Ej22CochesSpringBootMvcApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Ej22CochesSpringBootMvcApplication.class, args);
	}
	
	@Autowired
	private DaoUsuario du;
	@Autowired
	private DaoVideojuego dv;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Usuario u = new Usuario();
		u.setUsuario("root");
		u.setContrasenia("root");
		du.save(u);
		
		Videojuego v1 = new Videojuego(1,"GTA","Rockstar",8);
		Videojuego v2 = new Videojuego(2,"Juego2","Company2",4);
		Videojuego v3 = new Videojuego(3,"Juego3","Company3",5);
		Videojuego v4 = new Videojuego(4,"Juego4","Company4",9);
		
		dv.save(v1);
		dv.save(v2);
		dv.save(v3);
		dv.save(v4);
		
	}

}
