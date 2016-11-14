package com.xy.sistemaz.web.componentes.menu;

import java.lang.reflect.Constructor;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import com.xy.sistemaz.web.paginas.base.PaginaInterna;


public class LabeledMenuLink extends AjaxLink<Object> {
	
	private static final long serialVersionUID = 238652035690921266L;
	private final MenuTab item;
	
	
	public LabeledMenuLink(String id, MenuTab item) {
		super(id);
		this.item = item;
		add(new Label("label", new Model<String>(item.getLabel())));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onClick(AjaxRequestTarget target) {
		// Obtengo la pagina principal segun el rol y voy a la misma
		Class<? extends PaginaInterna> cls = null;
		try {
			cls = (Class<? extends PaginaInterna>) Class.forName(item.getPage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// Busco el constructor que no lleve parametros
        /*Constructor[] cts = cls.getConstructors();
       	Constructor<?> ct = cts[0];
       	int i = 0;
       	while (ct.getParameterTypes().length > 0)
       		ct = cts[++i];
       	
       	// Creo la pagina a partir del constructor sin parametros
		PaginaInterna pagina = null;
		try {
			pagina = (PaginaInterna) ct.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		// Redirecciono
		setResponsePage(cls);//pagina);
	}
	
}

