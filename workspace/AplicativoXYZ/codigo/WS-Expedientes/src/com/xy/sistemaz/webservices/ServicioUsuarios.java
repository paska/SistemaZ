package com.xy.sistemaz.webservices;

import com.xy.sistemaz.modelo.Usuario;
import com.xy.sistemaz.persistencia.UsuarioDAO;

/**
 * Servicios ligados a los usuarios registrados
 */
public class ServicioUsuarios {

	private UsuarioDAO dao;
	
	
	
	public ServicioUsuarios() {
		dao = new UsuarioDAO();
	}
	
	/**
	 * Permite validar un usuario y clave contra la base de usuarios
	 * registrados
	 * 
	 * @param usuario
	 * @return true si el usuario y clave son correctos, false en caso contrario
	 */
	public boolean validar(Usuario usuario) {
		Usuario resultado = dao.getUsuario(usuario);
		if (resultado != null)
			return true;
		return false;
	}
	
	/**
	 * Permite obtener un usuario dados parametros de busqueda
	 * 
	 * @param usuario
	 * @return usuario resultante
	 */
	public Usuario getUsuario(Usuario parametros) {
		return dao.getUsuario(parametros);
	}
	
	/**
	 * Permite registrar un nuevo usuario
	 * 
	 * @param usuario
	 * @return id asignado
	 */
	public Usuario registrar(Usuario usuario) {
		dao.registrar(usuario);
		return usuario;
	}
	
}
