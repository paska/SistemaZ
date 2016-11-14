package com.xy.sistemaz.persistencia;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.classic.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseDAO {

	protected SessionFactory sessionFactory;
	protected Logger logger;
	
	
	public BaseDAO() {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
        
        logger = LoggerFactory.getLogger(this.getClass());
	}
	
	public int registrar(Object objeto) {
		Session sesion = sessionFactory.openSession();
		Integer id = -1;
		try {
			sesion.beginTransaction();
			id = (Integer) sesion.save(objeto);
			sesion.getTransaction().commit();
		} catch (HibernateException e) {
			sesion.getTransaction().rollback();
			logger.error("Error al registrar un objeto de clase " + 
					objeto.getClass(), e);
		}
		sesion.close();
		return id;
	}
}
