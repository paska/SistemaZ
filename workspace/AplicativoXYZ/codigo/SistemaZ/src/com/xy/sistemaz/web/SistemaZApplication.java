package com.xy.sistemaz.web;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.target.coding.QueryStringUrlCodingStrategy;
import org.apache.wicket.settings.IApplicationSettings;

import com.xy.sistemaz.web.paginas.error.ErrorInterno;
import com.xy.sistemaz.web.paginas.error.SesionExpirada;
import com.xy.sistemaz.web.paginas.expedientes.BuscarExpediente;
import com.xy.sistemaz.web.paginas.expedientes.RegistrarExpediente;
import com.xy.sistemaz.web.paginas.login.Login;
import com.xy.sistemaz.web.paginas.login.Logout;


public class SistemaZApplication extends WebApplication {

	@Override
	public void init() {
        super.init();
        IApplicationSettings settings = getApplicationSettings();
        settings.setPageExpiredErrorPage(SesionExpirada.class);
        settings.setInternalErrorPage(ErrorInterno.class);
        
        mount(new QueryStringUrlCodingStrategy("login", Login.class));
        mount(new QueryStringUrlCodingStrategy("logout", Logout.class));
        
        mount(new QueryStringUrlCodingStrategy("error_interno", ErrorInterno.class));
        mount(new QueryStringUrlCodingStrategy("sesion_expirada", SesionExpirada.class));
        
        mount(new QueryStringUrlCodingStrategy("busqueda_expediente", BuscarExpediente.class));
        mount(new QueryStringUrlCodingStrategy("registro_expediente", RegistrarExpediente.class));
	}
	
	@Override
	public Class<? extends Page> getHomePage() {
		return Login.class;
	}
	
	
	@Override
	public Session newSession(Request request, Response response) {
		return new SistemaZSession(request);
	}

}
