package com.xy.sistemaz.modelo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario", catalog = "xy_sistemaz")
public class Usuario {

	private Integer id;
	private String nombre;
	private String usuario;
	private String clave;
	
	
	public Usuario() {
		id = null;
		nombre = null;
		usuario = null;
		clave = null;
	}

	// Getters y setters ---------------------------------
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "nombre", nullable = false, length = 100)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "usuario", nullable = false, length = 20)
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Column(name = "clave", nullable = false, length = 20)
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean esNulo() {
		if (this.clave == null && this.id == null && this.nombre == null && this.usuario == null)
			return true;
		return false;
	}
	
}
