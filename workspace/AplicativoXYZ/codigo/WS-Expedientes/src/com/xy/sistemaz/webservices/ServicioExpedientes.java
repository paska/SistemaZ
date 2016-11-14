package com.xy.sistemaz.webservices;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.xy.sistemaz.modelo.Expediente;
import com.xy.sistemaz.persistencia.ExpedienteDAO;

/**
 * Servicios expuestos para manejo de expedientes
 */
public class ServicioExpedientes {

	private ExpedienteDAO dao;
	
	
	public ServicioExpedientes() {
		dao = new ExpedienteDAO();
	}
	
	/**
	 * Permite obtener un expediente por su numero
	 * 
	 * @param numero
	 * @return expediente buscado
	 */
	public Expediente getExpediente(String numero) {
		return dao.getExpediente(numero);
	}
	
	/**
	 * Permite registrar un expediente
	 * 
	 * @param expediente
	 * @return expediente registrado y actualizado
	 */
	public Expediente registrar(Expediente expediente) {
		expediente.setFechaCreacion(new SimpleDateFormat("dd-MM-yyyy").
				format(new Date()));
		dao.registrar(expediente);
		return expediente;
	}
	
}
