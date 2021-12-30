package Dominio;

public abstract class Envio {
	protected String codigo;
	protected double peso;
	protected Cliente cliente1;
	protected Cliente cliente2;
	protected Ciudad ciudad;
	protected Envio(String codigo, double peso) {
		this.codigo = codigo;
		this.peso = peso;
		this.cliente1 = null;
		this.cliente2 = null;
		this.ciudad = null;
	}
	
	public Cliente getCliente1() {
		return cliente1;
	}

	public void setCliente1(Cliente cliente1) {
		this.cliente1 = cliente1;
	}

	public Cliente getCliente2() {
		return cliente2;
	}

	public void setCliente2(Cliente cliente2) {
		this.cliente2 = cliente2;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public double getPeso() {
		return peso;
	}


	public void setPeso(double peso) {
		this.peso = peso;
	}


	abstract public int calcularEnvio();
	
}
