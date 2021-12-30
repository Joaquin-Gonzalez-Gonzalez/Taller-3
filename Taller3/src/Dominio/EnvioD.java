package Dominio;

public class EnvioD extends Envio {
	private double grosor;
	public EnvioD(String codigo, double peso, double grosor) {
		super(codigo,peso);
		this.grosor = grosor;
		
	}
	
	public double getGrosor() {
		return grosor;
	}


	public void setGrosor(double grosor) {
		this.grosor = grosor;
	}


	@Override
	public int calcularEnvio() {
		int envio = (int) (this.getPeso() * grosor * 100); 
		return envio;
	}
	

}
