package com.xy.sistemaz.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "expediente", catalog = "xy_sistemaz")
public class Expediente {

	private Integer id;
	private String numero;
	private String fechaCreacion;
	private Usuario responsable;
	private String titulo;
	private String cuerpo;
	
	
	public Expediente() {
		id = null;
		numero = null;
		fechaCreacion = null;
		responsable = null;
		titulo = null;
		cuerpo = null;
	}

	// Getters y setters -------------------------------------------
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		this.numero = "EXP-" + id;
	}

	@Transient
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = "fecha_creacion", nullable = false, length = 10)
	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_responsable", nullable = false)
	public Usuario getResponsable() {
		return responsable;
	}

	public void setResponsable(Usuario responsable) {
		this.responsable = responsable;
	}

	@Column(name = "titulo", nullable = false, length = 500)
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Column(name = "cuerpo", nullable = false, length = 1000)
	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	
}
