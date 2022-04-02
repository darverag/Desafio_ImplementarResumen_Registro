package com.desafiolatam.entidades;

public class InscripcionDTO2 {
	
	//atributos
	
	private int idInsc;
	private String nombre;
	private String celular;
	private String curso;
	private String formaDePago;
	
	
	public InscripcionDTO2() {
		super();
	}


	public InscripcionDTO2(int idInsc, String nombre, String celular, String curso, String formaDePago) {
		super();
		this.idInsc = idInsc;
		this.nombre = nombre;
		this.celular = celular;
		this.curso = curso;
		this.formaDePago = formaDePago;
	}


	public int getIdInsc() {
		return idInsc;
	}


	public void setIdInsc(int idInsc) {
		this.idInsc = idInsc;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCelular() {
		return celular;
	}


	public void setCelular(String celular) {
		this.celular = celular;
	}


	public String getCurso() {
		return curso;
	}


	public void setCurso(String curso) {
		this.curso = curso;
	}


	public String getFormaDePago() {
		return formaDePago;
	}


	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}
	
	
	
}
