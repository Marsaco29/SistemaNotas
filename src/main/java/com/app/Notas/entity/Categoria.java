package com.app.Notas.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="categoria")
public class Categoria {
	
	@Id
	private String id;
	private String ctitulo;
	private String fecha;
	private String descripcion;
	
	public Categoria() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getCtitulo() {
		return ctitulo;
	}

	public void setCtitulo(String ctitulo) {
		this.ctitulo = ctitulo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}
