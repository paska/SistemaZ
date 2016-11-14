package com.xy.sistemaz.web.paginas.expedientes;

import org.apache.axis2.AxisFault;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.StringValidator.LengthBetweenValidator;

import com.xy.sistemaz.web.paginas.base.PaginaInterna;
import com.xy.sistemaz.web.paginas.error.ErrorInterno;
import com.xy.sistemaz.webservices.ServicioExpedientesStub;
import com.xy.sistemaz.webservices.ServicioExpedientesStub.Expediente;
import com.xy.sistemaz.webservices.ServicioExpedientesStub.Usuario;


public class RegistrarExpediente extends PaginaInterna {
	
	
	public RegistrarExpediente() {
		super("Registrar expediente");
	
		final FeedbackPanel mensajes = new FeedbackPanel("mensajes");
		mensajes.setEscapeModelStrings(false);
		mensajes.setOutputMarkupId(true);
		mensajes.setOutputMarkupPlaceholderTag(true);
		add(mensajes);
		
		Expediente expediente = new Expediente();
		Usuario usuario = new Usuario();
		if (getSistemaZSession().getUsuario() != null) {
			usuario.setId(getSistemaZSession().getUsuario().getId());
			usuario.setUsuario(getSistemaZSession().getUsuario().getUsuario());
			usuario.setNombre(getSistemaZSession().getUsuario().getNombre());
			usuario.setClave(getSistemaZSession().getUsuario().getClave());
		}
		expediente.setResponsable(usuario);
		
		// Form de registro
		Form<Expediente> formRegistro = new Form<Expediente>("formRegistro", 
				new CompoundPropertyModel<Expediente>(expediente));
    	add(formRegistro);
    	
    	TextField<String> titulo = new TextField<String>("titulo");
    	titulo.setRequired(true);
    	titulo.setOutputMarkupId(true);
    	titulo.add(new LengthBetweenValidator(2, 100));
    	formRegistro.add(titulo);
    	
    	TextArea<String> cuerpo = new TextArea<String>("cuerpo");
    	cuerpo.setRequired(true);
    	cuerpo.setOutputMarkupId(true);
    	cuerpo.add(new LengthBetweenValidator(2, 500));
    	formRegistro.add(cuerpo);
    	
    	// Boton de submit de registro
		IndicatingAjaxButton submit = new IndicatingAjaxButton("submit", formRegistro) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				Expediente aRegistrar = (Expediente) form.getModelObject();

				try {
					ServicioExpedientesStub servicioExpedientes = new ServicioExpedientesStub();
					Expediente resultado = servicioExpedientes.registrar(aRegistrar);
					mensajes.info("Se ha registrado el expediente. El codigo asignado es " + resultado.getNumero());
					
					// Pongo un nuevo expediente en el modelo
					//form.detachModels();
					form.setDefaultModelObject(new Expediente());
				} catch (AxisFault e) {
					System.err.println(e.getMessage());
					setRedirect(true);
					setResponsePage(ErrorInterno.class);
				}
				target.addComponent(mensajes);
				target.addComponent(form);
			}
		};
		formRegistro.add(submit);
		
	}
	
}
