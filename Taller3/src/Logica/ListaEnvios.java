package Logica;
import Dominio.*;


public class ListaEnvios {
	private NodoEnvio first;
	private NodoEnvio last;
	public ListaEnvios() {
	   first = null;
	   last = null;
	}
	public boolean isEmpty() {
		return first == null;
		
	}
	public void add(Envio e){
		NodoEnvio nuevoNodo = new NodoEnvio(e);
		if(isEmpty()) {
			first = nuevoNodo;
			last = nuevoNodo;
			nuevoNodo.setNext(nuevoNodo);
			nuevoNodo.setPrev(nuevoNodo);
		}
		else {
			nuevoNodo.setPrev(last);
			nuevoNodo.setNext(last.getNext());
			last.getNext().setPrev(nuevoNodo);
			last.setNext(nuevoNodo);
			last = nuevoNodo;
		}
		
	}
	public NodoEnvio getFirst() {
		return first;
	}
	public int EnviosTotales() {
		int total = 1;
		NodoEnvio current = first;
		while(current.getNext() != first) {
			total++;
			current = current.getNext();
		}
		return total;
	}
	public Envio getEnvioParaDato(int i) {
		int cont = 0;
		NodoEnvio current = first;
		while(current.getNext() != first) {
			if(cont == i) {
				break;
			}
			cont++;
			current = current.getNext();
		}
		if(cont == 0) {
			return first.getEnvio();
		}
		else {
			return current.getEnvio();
		}
	}

}
