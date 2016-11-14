package com.xy.sistemaz.web.componentes;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.xy.sistemaz.web.SistemaZSession;
import com.xy.sistemaz.web.paginas.login.Logout;


public class UsuarioPanel extends Panel {

	private static final long serialVersionUID = 2904461320991647177L;

	
	public UsuarioPanel(String id, final SistemaZSession sesion) {
		super(id);
		
		// Container para los datos de usuario
		WebMarkupContainer datosUsuario = new WebMarkupContainer("datosUsuario");
		
		// Agrego el nombre del usuario
		Label label;
		
		
		// Link para logout
		Link<String> logoutLink = new Link<String>("logoutLink") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(Logout.class);
			}
		};
		
		if (sesion.getUsuario() != null) {
			label = new Label("usuario", sesion.getUsuario().getUsuario());
			label.setEscapeModelStrings(false);
		}
		else {
			label = new Label("usuario", "");
			label.setVisible(false);
			logoutLink.setVisible(false);
		}
			
		datosUsuario.add(label);
		datosUsuario.add(logoutLink);
		add(datosUsuario);
	}

}
