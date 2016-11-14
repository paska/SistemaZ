package com.xy.sistemaz.web.componentes.menu;

import java.io.Serializable;


@SuppressWarnings("serial")
public class MenuTab implements Serializable {

	private String label;
	private String page;
	private boolean selected;
	
	
	public MenuTab(String label, String page, String paginaActual) {
		this.label = label;
		this.page = page;
		selected = false;
		
		if (paginaActual.equals(label))
			selected = true;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}


	public boolean isSelected() {
		return selected;
	}
	
}
