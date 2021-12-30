package Dominio;

public class NodoEnvio {
	private Envio envio;
    private NodoEnvio next;
    private NodoEnvio prev;
	public NodoEnvio(Envio envio) {
		// TODO Auto-generated constructor stub
		this.envio = envio;
		next = null;
		prev = null;
	}

	public Envio getEnvio() {
		return envio;
	}
	
	public void setEnvio(Envio envio) {
		this.envio = envio;
	}
	
	public NodoEnvio getNext() {
		return next;
	}
	
	public void setNext(NodoEnvio next) {
		this.next = next;
	}
	
	public NodoEnvio getPrev() {
		return prev;
	}
	
	public void setPrev(NodoEnvio prev) {
		this.prev = prev;
	}
	

}
