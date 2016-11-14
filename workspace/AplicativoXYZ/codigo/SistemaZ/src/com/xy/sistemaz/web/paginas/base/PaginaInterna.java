package com.xy.sistemaz.web.paginas.base;

import com.xy.sistemaz.web.componentes.UsuarioPanel;
import com.xy.sistemaz.web.componentes.menu.MenuPrimario;
import com.xy.sistemaz.web.paginas.login.Login;
import com.xy.sistemaz.webservices.ServicioUsuariosStub.Usuario;


public class PaginaInterna extends PaginaBase {
	
	
	public PaginaInterna(String nombrePagina) {
		Usuario usuario = getSistemaZSession().getUsuario();
		
		if (usuario != null) {
			// Panel de usuario y menu
			add(new UsuarioPanel("usuarioPanel", getSistemaZSession()));
			add(new MenuPrimario("menuPrimario", getSistemaZSession(), nombrePagina));
		}
		else {
			setRedirect(true);
			setResponsePage(Login.class);
		}
	}

}
