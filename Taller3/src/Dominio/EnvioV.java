package Dominio;

public class EnvioV extends Envio{
	private String material;
	public EnvioV(String codigo, double peso, String material) {
		super(codigo, peso);
		this.material = material;
	}
	
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public int calcularEnvio() {
		int precioMaterial = 0;
		int envio = 0;
		if(material.equals("Cuero")) {
			precioMaterial = 200;
		}
		if(material.equals("Plastico")) {
			precioMaterial = 150;
		}
		if(material.equals("Tela")) {
			precioMaterial = 100;
		}
		envio = (int) (precioMaterial * this.getPeso() * 150);
		return envio;
	}
	
}
