package com.xy.sistemaz.web.paginas.expedientes;

import org.apache.axis2.AxisFault;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator.LengthBetweenValidator;

import com.xy.sistemaz.web.paginas.base.PaginaInterna;
import com.xy.sistemaz.web.paginas.error.ErrorInterno;
import com.xy.sistemaz.webservices.ServicioExpedientesStub;
import com.xy.sistemaz.webservices.ServicioExpedientesStub.Expediente;



public class BuscarExpediente extends PaginaInterna {

	public BuscarExpediente() {
		super("Buscar expediente");

		final CompoundPropertyModel<Expediente> modelo = new CompoundPropertyModel<Expediente>(new Expediente());
		final FeedbackPanel mensajes = new FeedbackPanel("mensajes");
		mensajes.setOutputMarkupId(true);
		mensajes.setOutputMarkupPlaceholderTag(true);
		add(mensajes);
		
		// Form para buscar
    	Form<String> formBusqueda = new Form<String>("formBusqueda");
    	add(formBusqueda);
    	
    	final TextField<String> codigo = new TextField<String>("codigoExpediente", new Model<String>());
    	codigo.setOutputMarkupId(true);
    	codigo.setRequired(true);
    	codigo.add(new LengthBetweenValidator(5, 10));
    	formBusqueda.add(codigo);
    	
    	// Resultado
    	final WebMarkupContainer container = new WebMarkupContainer("resultado", modelo);
    	container.setOutputMarkupId(true);
    	container.setOutputMarkupPlaceholderTag(true);
    	container.setVisible(false);
    	
    	Label numero = new Label("numero");
    	Label fecha = new Label("fechaCreacion");
    	Label titulo = new Label("titulo");
    	Label responsable = new Label("responsable.nombre");
    	Label cuerpo = new Label("cuerpo");
    	container.add(numero);
    	container.add(fecha);
    	container.add(titulo);
    	container.add(responsable);
    	container.add(cuerpo);
    	add(container);

    	
    	// Boton de submit de busqueda
		IndicatingAjaxButton submit = new IndicatingAjaxButton("submit", formBusqueda) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.addComponent(mensajes);
			}
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				String buscado = codigo.getDefaultModelObjectAsString();
				modelo.setObject(new Expediente());
				
				if (buscado.length() == 0) {
					mensajes.error("Debe ingresar un codigo a buscar");
					container.setVisible(false);
				}
				else {
					ServicioExpedientesStub servicioExpedientes;
					try {
						servicioExpedientes = new ServicioExpedientesStub();
						Expediente expediente = servicioExpedientes.getExpedientePorCodigo(buscado);
						
						if (expediente != null) {
							modelo.setObject(expediente);
							container.setVisible(true);
						}
						else {
							mensajes.info("No se encontro ningun expediente con ese codigo");
							container.setVisible(false);
						}
					} catch (AxisFault e) {
						System.err.println(e.getMessage());
						setRedirect(true);
						setResponsePage(ErrorInterno.class);
					}
				}
				target.addComponent(mensajes);
				target.addComponent(container);
			}
		};
		formBusqueda.add(submit);
	}
	
}
