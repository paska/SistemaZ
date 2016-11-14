package com.xy.sistemaz.web;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

import com.xy.sistemaz.webservices.ServicioUsuariosStub.Usuario;


public class SistemaZSession extends WebSession {

	private static final long serialVersionUID = 5493723102054234302L;
	private Usuario usuario;
	
	
	public SistemaZSession(Request request) {
		super(request);
	}

	@Override
	public void invalidate() {
		super.invalidate();
		usuario = null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
