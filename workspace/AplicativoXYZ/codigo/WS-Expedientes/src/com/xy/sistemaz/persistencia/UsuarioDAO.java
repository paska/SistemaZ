package com.xy.sistemaz.persistencia;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.xy.sistemaz.modelo.Usuario;


public class UsuarioDAO extends BaseDAO {
	
	
	public UsuarioDAO() {
        super();
	}
	
	public Usuario getUsuario(Usuario usuario) {
		if (!usuario.esNulo()) {
			Session sesion = sessionFactory.openSession();
			try {
				Criteria criterio = sesion.createCriteria(Usuario.class);
				
				// Agrego los criterios de busqueda
				if (usuario.getId() != null)
					criterio.add(Restrictions.eq("id", usuario.getId()));
				if (usuario.getNombre() != null)
					criterio.add(Restrictions.eq("nombre", usuario.getNombre()));
				if (usuario.getUsuario() != null)
					criterio.add(Restrictions.eq("usuario", usuario.getUsuario()));
				if (usuario.getClave() != null)
					criterio.add(Restrictions.eq("clave", usuario.getClave()));
				
				// Buso al usuario
				Usuario resultado = (Usuario) criterio.uniqueResult();
				sesion.close();
				return resultado;
			} catch (HibernateException e) {
				sesion.close();
				logger.error("Error al obtener un usuario", e);
			}
		}
		return null;
	}

}
