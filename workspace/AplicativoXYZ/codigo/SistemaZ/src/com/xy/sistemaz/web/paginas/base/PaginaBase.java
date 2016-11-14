package com.xy.sistemaz.web.paginas.base;

import org.apache.wicket.markup.html.WebPage;

import com.xy.sistemaz.web.SistemaZSession;


class PaginaBase extends WebPage {

	
	public PaginaBase() {
	}
	
	
	public SistemaZSession getSistemaZSession() {
		return (SistemaZSession) getSession();
	}
}
