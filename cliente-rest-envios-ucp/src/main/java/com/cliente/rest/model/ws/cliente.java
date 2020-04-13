package com.cliente.rest.model.ws;

public class cliente {

  private int id;
  private String cedula;
  private String nombre;
  private String apellido;
  private String fecha_nacimiento;
  private String lugar_residencia;
  private String telefono;
  private String correo_electronico;
  private String estado;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCedula() {
    return cedula;
  }

  public void setCedula(String cedula) {
    this.cedula = cedula;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getFecha_nacimiento() {
    return fecha_nacimiento;
  }

  public void setFecha_nacimiento(String fecha_nacimiento) {
    this.fecha_nacimiento = fecha_nacimiento;
  }

  public String getLugar_residencia() {
    return lugar_residencia;
  }

  public void setLugar_residencia(String lugar_residencia) {
    this.lugar_residencia = lugar_residencia;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getCorreo_electronico() {
    return correo_electronico;
  }

  public void setCorreo_electronico(String correo_electronico) {
    this.correo_electronico = correo_electronico;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }
}
