package com.cliente.rest.model;

import com.cliente.rest.model.util.dto.Constantes;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class RegistroMercanciaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7899438028750247628L;

	private int ID;
	@NotBlank(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 50, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String cedulaCliente;
	@NotBlank(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 50, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String cedulaDestinatario;
	@Size(max = 50, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String nombreDestinatario;
	@Size(max = 50, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String apellidoDestinatario;
	@NotBlank(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 50, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String ciudadOrigen;
	@NotBlank(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 50, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String ciudadDestino;
	@NotNull(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Max(value = 999999, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private int pesoPaquete;
	@NotNull(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Max(value = 999999, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private int valorAsegurado;
	@NotNull(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Max(value = 999999, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private int valorNumerico;
	@NotBlank(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 15, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String estadoPaquete;
	@NotBlank(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 15, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String nroGuia;

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

	public String getCedulaDestinatario() {
		return cedulaDestinatario;
	}

	public void setCedulaDestinatario(String cedulaDestinatario) {
		this.cedulaDestinatario = cedulaDestinatario;
	}

	public String getNombreDestinatario() {
		return nombreDestinatario;
	}

	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}

	public String getApellidoDestinatario() {
		return apellidoDestinatario;
	}

	public void setApellidoDestinatario(String apellidoDestinatario) {
		this.apellidoDestinatario = apellidoDestinatario;
	}

	public String getCiudadOrigen() {
		return ciudadOrigen;
	}

	public void setCiudadOrigen(String ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}

	public String getCiudadDestino() {
		return ciudadDestino;
	}

	public void setCiudadDestino(String ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}

	public int getPesoPaquete() {
		return pesoPaquete;
	}

	public void setPesoPaquete(int pesoPaquete) {
		this.pesoPaquete = pesoPaquete;
	}

	public int getValorAsegurado() {
		return valorAsegurado;
	}

	public void setValorAsegurado(int valorAsegurado) {
		this.valorAsegurado = valorAsegurado;
	}

	public int getValorNumerico() {
		return valorNumerico;
	}

	public void setValorNumerico(int valorNumerico) {
		this.valorNumerico = valorNumerico;
	}

	public String getEstadoPaquete() {
		return estadoPaquete;
	}

	public void setEstadoPaquete(String estadoPaquete) {
		this.estadoPaquete = estadoPaquete;
	}

	public String getNroGuia() {
		return nroGuia;
	}

	public void setNroGuia(String nroGuia) {
		this.nroGuia = nroGuia;
	}
}
