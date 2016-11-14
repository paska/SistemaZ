package com.xy.sistemaz.web.paginas.login;

import com.xy.sistemaz.web.paginas.base.PaginaExterna;


public class Logout extends PaginaExterna {

	public Logout() {
		getSistemaZSession().invalidate();
		setRedirect(true);
		setResponsePage(Login.class);
	}
	
}
