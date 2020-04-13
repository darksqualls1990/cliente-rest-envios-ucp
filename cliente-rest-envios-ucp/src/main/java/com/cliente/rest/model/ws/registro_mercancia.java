package com.cliente.rest.model.ws;

public class registro_mercancia {

  private String cedula_cliente;
  private String cedula_destinatario;
  private String nombre_destinatario;
  private String apellido_destinatario;
  private String ciudad_origen;
  private String ciudad_destino;
  private int peso_paquete;
  private int valor_asegurado;
  private int valor_numerico;
  private String estado_paquete;
  private String nro_guia;
  private int ID;

  public String getCedula_cliente() {
    return cedula_cliente;
  }

  public void setCedula_cliente(String cedula_cliente) {
    this.cedula_cliente = cedula_cliente;
  }

  public String getCedula_destinatario() {
    return cedula_destinatario;
  }

  public void setCedula_destinatario(String cedula_destinatario) {
    this.cedula_destinatario = cedula_destinatario;
  }

  public String getNombre_destinatario() {
    return nombre_destinatario;
  }

  public void setNombre_destinatario(String nombre_destinatario) {
    this.nombre_destinatario = nombre_destinatario;
  }

  public String getApellido_destinatario() {
    return apellido_destinatario;
  }

  public void setApellido_destinatario(String apellido_destinatario) {
    this.apellido_destinatario = apellido_destinatario;
  }

  public String getCiudad_origen() {
    return ciudad_origen;
  }

  public void setCiudad_origen(String ciudad_origen) {
    this.ciudad_origen = ciudad_origen;
  }

  public String getCiudad_destino() {
    return ciudad_destino;
  }

  public void setCiudad_destino(String ciudad_destino) {
    this.ciudad_destino = ciudad_destino;
  }

  public int getPeso_paquete() {
    return peso_paquete;
  }

  public void setPeso_paquete(int peso_paquete) {
    this.peso_paquete = peso_paquete;
  }

  public int getValor_asegurado() {
    return valor_asegurado;
  }

  public void setValor_asegurado(int valor_asegurado) {
    this.valor_asegurado = valor_asegurado;
  }

  public int getValor_numerico() {
    return valor_numerico;
  }

  public void setValor_numerico(int valor_numerico) {
    this.valor_numerico = valor_numerico;
  }

  public String getEstado_paquete() {
    return estado_paquete;
  }

  public void setEstado_paquete(String estado_paquete) {
    this.estado_paquete = estado_paquete;
  }

  public String getNro_guia() {
    return nro_guia;
  }

  public void setNro_guia(String nro_guia) {
    this.nro_guia = nro_guia;
  }

  public int getID() {
    return ID;
  }

  public void setID(int ID) {
    this.ID = ID;
  }
}
