package com.xy.sistemaz.web.paginas.error;

import org.apache.wicket.markup.html.link.Link;

import com.xy.sistemaz.web.paginas.base.PaginaExterna;
import com.xy.sistemaz.web.paginas.login.Login;


public class SesionExpirada extends PaginaExterna {

	public SesionExpirada() {
		Link<String> loginLink = new Link<String>("loginLink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(Login.class);
			}
		};
		add(loginLink);
	}
	
}
