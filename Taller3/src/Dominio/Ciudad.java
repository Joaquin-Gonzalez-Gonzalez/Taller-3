package Dominio;
import Logica.ListaEnvios;

public class Ciudad {
	private String nombre;
	private ListaEnvios enviados;
	private ListaEnvios recibidos;
	public Ciudad(String nombre) {
		this.nombre = nombre;
		this.enviados = new ListaEnvios();
		this.recibidos = new ListaEnvios();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
