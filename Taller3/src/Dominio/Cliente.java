package Dominio;
import Logica.*;

public class Cliente {
	private String rut;
	private String nombre;
	private String apellido;
	private Ciudad ciudad;
	private int saldo;
	private ListaEnvios enviados;
	private ListaEnvios recibidos;

	public Cliente(String rut, String nombre, String apellido, int saldo) {
		this.rut = rut;
		this.nombre = nombre;
		this.apellido = apellido;
		this.ciudad = null;
		this.saldo = saldo;
		enviados = new ListaEnvios();
		recibidos = new ListaEnvios();
	}
	
	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
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

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public ListaEnvios getEnviados() {
		return enviados;
	}

	public void setEnviados(ListaEnvios enviados) {
		this.enviados = enviados;
	}

	public ListaEnvios getRecibidos() {
		return recibidos;
	}

	public void setRecibidos(ListaEnvios recibidos) {
		this.recibidos = recibidos;
	}
	

}
