package com.xy.sistemaz.web.componentes.menu;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.xy.sistemaz.web.SistemaZSession;
import com.xy.sistemaz.web.paginas.expedientes.BuscarExpediente;
import com.xy.sistemaz.web.paginas.expedientes.RegistrarExpediente;


public class MenuPrimario extends Panel {

	private static final long serialVersionUID = -1076826483119561520L;

	
	public MenuPrimario(String id, final SistemaZSession sesion, String paginaActual) {
		super(id);
		
		// Nombre del panel actual
		//Label panelActualLabel = new Label("panelActual", new Model<String>(paginaActual));
		
		// Lista del menu
		List<MenuTab> list = new ArrayList<MenuTab>();
		
		list.add(new MenuTab("Buscar expediente", BuscarExpediente.class.getCanonicalName(), paginaActual));
		list.add(new MenuTab("Registrar expediente", RegistrarExpediente.class.getCanonicalName(), paginaActual));
		
		// Agrego el menu...
		ListView<MenuTab> menu = new ListView<MenuTab>("menu", list) {
			private static final long serialVersionUID = 362331162482763898L;

			@Override
			protected void populateItem(ListItem<MenuTab> item) {
		    	MenuTab mi = (MenuTab) item.getDefaultModelObject();
		        item.add(new LabeledMenuLink("link", mi));
		        if (mi.isSelected())
		        	item.add(new SimpleAttributeModifier("class","selected"));
		    }
		};
		
		/*if (sesion.getUsuario() == null) {
			menu.setVisible(false);
			panelActualLabel.setVisible(false);
		}
		
		add(panelActualLabel);*/
		add(menu);
	}

}
