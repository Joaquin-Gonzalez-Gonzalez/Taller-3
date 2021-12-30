package Dominio;

public class EnvioE extends Envio {
	private double largo;
	private double ancho;
	private double profundidad;
	public EnvioE(String codigo, double peso, double largo, double ancho, double profundidad) {
		// TODO Auto-generated constructor stub
		super(codigo,peso);
		this.largo = largo;
		this.ancho = ancho;
		this.profundidad = profundidad;
	} 
	
	public double getLargo() {
		return largo;
	}

	public void setLargo(double largo) {
		this.largo = largo;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	public double getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(double profundidad) {
		this.profundidad = profundidad;
	}

	@Override
	public int calcularEnvio() {
		int envio = (int) (this.getPeso() * (largo * ancho * profundidad) * 50); 
		return envio;
	}

}
