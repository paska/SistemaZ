package com.xy.sistemaz.persistencia;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.xy.sistemaz.modelo.Expediente;

public class ExpedienteDAO extends BaseDAO {

	public ExpedienteDAO() {
		super();
	}
	
	public Expediente getExpediente(String numero) {
		Session sesion = sessionFactory.openSession();
		try {
			Criteria criterio = sesion.createCriteria(Expediente.class);
			criterio.add(Restrictions.eq("id", Integer.parseInt(numero.split("\\-")[1])));
			Expediente resultado = (Expediente) criterio.uniqueResult();
			sesion.close();
			return resultado;
		} catch (HibernateException e) {
			sesion.close();
			logger.error("Error al obtener un expediente", e);
		}
		return null;
	}

}
