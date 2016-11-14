package com.xy.sistemaz.web.paginas.login;

import org.apache.axis2.AxisFault;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxFallbackButton;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;
import org.apache.wicket.validation.validator.StringValidator.LengthBetweenValidator;

import com.xy.sistemaz.web.SistemaZSession;
import com.xy.sistemaz.web.paginas.base.PaginaExterna;
import com.xy.sistemaz.web.paginas.error.ErrorInterno;
import com.xy.sistemaz.web.paginas.expedientes.BuscarExpediente;
import com.xy.sistemaz.webservices.ServicioUsuariosStub;
import com.xy.sistemaz.webservices.ServicioUsuariosStub.Usuario;


public class Login extends PaginaExterna {
	

	public Login() {
		
		// Obtengo la sesion actual
		final SistemaZSession sesion = getSistemaZSession();
		
		// Mensajes de error
		final FeedbackPanel mensajes = new FeedbackPanel("mensajes");
		mensajes.setOutputMarkupId(true);
		add(mensajes);
		
		// Creo el modelo con un usuario y el formulario
		final Usuario usuario = new Usuario();
		Form<Usuario> formLogin = new Form<Usuario>("formLogin", new CompoundPropertyModel<Usuario>(usuario));
		add(formLogin);
		
		// Agrego los campos al formulario
		TextField<String> usuarioField = new TextField<String>("usuario");
		usuarioField.setRequired(true);
		formLogin.add(usuarioField);
		
		final PasswordTextField password = new PasswordTextField("clave");
		password.setRequired(true);
		password.setOutputMarkupId(true);
		formLogin.add(password);
		
		// Agrego el boton de submit
		IndicatingAjaxButton submit = new IndicatingAjaxButton("submit", formLogin) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form) {
				target.addComponent(mensajes);
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				try {
					ServicioUsuariosStub servicioUsuarios = new ServicioUsuariosStub();
					Usuario aux = servicioUsuarios.getUsuario(usuario);
					
					if (aux != null) {
						sesion.setUsuario(aux);
						
						// Redirecciono
						setResponsePage(BuscarExpediente.class);
					}
					else {
						password.setDefaultModelObject("");
						mensajes.error("Usuario o clave invalida");
						target.addComponent(password);
						target.addComponent(mensajes);
					}
				} catch (AxisFault e) {
					System.err.println(e.getMessage());
					setRedirect(true);
					setResponsePage(ErrorInterno.class);
				}
			}
		};
		formLogin.add(submit);
	}
	
}
