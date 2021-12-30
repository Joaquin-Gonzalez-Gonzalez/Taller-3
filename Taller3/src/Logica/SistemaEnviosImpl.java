package Logica;
import java.util.ArrayList;
import java.util.LinkedList;
import ucn.*;
import Dominio.*;


public class SistemaEnviosImpl implements SistemaEnvios{
    private ListaEnvios le;
    private ArrayList <Ciudad> lc;
    private LinkedList <Cliente> lCliente;
	public SistemaEnviosImpl() {
		le = new ListaEnvios();
		lc = new ArrayList <Ciudad>();
		lCliente = new LinkedList <Cliente>();
	}

	@Override
	public boolean addEnvioD(String codigo,int peso, int grosor) {
		double pesoN = peso/1000;
		double grosorN = grosor/10;
		if(peso <= 1.5 && grosorN <= 5) {
			EnvioD envio = new EnvioD(codigo,pesoN, grosorN);
			le.add(envio);
			return true;
		}
		return false;
	}
	@Override
	public boolean addEnvioE(String codigo, int peso, int largo, int ancho, int profundidad) {
		double pesoN = peso/1000;
		double largoN = largo/100;
		double anchoN = ancho/100;
		double profundidadN = profundidad/100;
		if(pesoN <= 50 && largoN <= 1.2 && anchoN <= 0.8 && profundidadN <= 0.8) {
			EnvioE envio = new EnvioE(codigo,pesoN,largoN,anchoN,profundidadN);
			le.add(envio);
			return true;
		}
		return false;
	}

	@Override
	public boolean addEnvioV(String codigo, int peso, String material) {
		double pesoN = peso/1000;
		if(pesoN <= 2) {
			EnvioV envio = new EnvioV(codigo, peso , material);
			le.add(envio);
			return true;
		}
		return false;
	}
		
	@Override
	public void addCiudad(String nombre) {
		Ciudad ciudad = new Ciudad(nombre);
		lc.add(ciudad);
		
	}

	@Override
	public void addCliente(String rut, String nombre, String apellido, int saldo) {
		Cliente cliente = new Cliente(rut,nombre,apellido,saldo);
		lCliente.add(cliente);
	}
		

	@Override
	public boolean AsociarCiudad(String rut, String nombre) {
		Cliente clienteN = null;
		Ciudad ciudadN = null;
		try {
			for(Cliente cliente: lCliente) {
				if(cliente.getRut().equals(rut)) {
					clienteN = cliente;
					break;
				}
			}
			for(Ciudad ciudad: lc) {
				if(ciudad.getNombre().equals(nombre)) {
					ciudadN = ciudad;
					break;
				}
			}
			clienteN.setCiudad(ciudadN);
		}catch(NullPointerException e) {
			StdOut.println("Error elemento nulo");
		}
		return true;
	}
	
	public boolean AsociarEnvios(String rut1, String rut2, String codigo) {
		Cliente cliente1 = null;
		Cliente cliente2 = null;
		Ciudad ciudadE = null;
		Ciudad ciudadD = null;
		Envio envioN = null;
		try {
			for(Cliente cliente: lCliente) {
				if(cliente.getRut().equals(rut1)) {
					cliente1 = cliente;
					break;
				}
			}
			for(Cliente cliente: lCliente) {
				if(cliente.getRut().equals(rut2)) {
					cliente2 = cliente;
					break;
				}
			}
			for(Ciudad ciudad: lc) {
				if(ciudad == cliente1.getCiudad()) {
					ciudadE = ciudad;
					break;
				}
			}
			for(Ciudad ciudad: lc) {
				if(ciudad == cliente2.getCiudad()) {
					ciudadD = ciudad;
					break;
				}
			}
			for(int i = 0; i < le.EnviosTotales();i++) {
				if(le.getEnvioParaDato(i).getCodigo().equals(codigo)) {
					envioN = le.getEnvioParaDato(i);
					break;
				}
			}
			envioN.setCiudad(ciudadE);
			envioN.setCliente1(cliente1);
			envioN.setCliente2(cliente2);
			cliente1.getEnviados().add(envioN);
			cliente2.getRecibidos().add(envioN);
			ciudadD.getRecibidos().add(envioN);
			ciudadE.getEnviados().add(envioN);
		}catch(NullPointerException e) {
			StdOut.println("Error elemento nulo");
		}
		return true;
	}
	public boolean recargarSaldo(String rut, int recarga) {
		Cliente clienteN = null;
		for(Cliente cliente: lCliente) {
			if(cliente.getRut().equals(rut)) {
				clienteN = cliente;
				break;
			}
		}
		int saldo = clienteN.getSaldo();
		recarga += saldo;
		clienteN.setSaldo(recarga);
		return true;
	}
	public String verEntregas(String rut) {
		String data = "";
		Cliente clienteN = null;
		for(Cliente cliente: lCliente) {
			if(cliente.getRut().equals(rut)) {
				clienteN = cliente;
				break;
			}
		}
		data += "Recibidos: " + "\n";
		for(int i = 0; i < clienteN.getRecibidos().EnviosTotales(); i++ ) {
			data += clienteN.getRecibidos().getEnvioParaDato(i).getCodigo() + "\n";
		}
		data += "Enviados: " + "\n";
		for(int i = 0; i < clienteN.getEnviados().EnviosTotales(); i++) {
			data += clienteN.getEnviados().getEnvioParaDato(i).getCodigo() + "\n";
		}
		return data;
	}
	@Override
	public String obtenerEnviosPorTipo() {
		String data = "";
		try {
		data += "Documentos:" + "\n";  
		for(int i = 0; i < le.EnviosTotales();i++) {
			Envio envio = le.getEnvioParaDato(i);
			if(envio instanceof EnvioD) {
				EnvioD envioD = (EnvioD) envio;
				int costo = envioD.calcularEnvio();
				data  += envioD.getCodigo() + " " + costo + "\n";
			}
			
		}
		data += "Encomiendas:" + "\n";  
		for(int i = 0; i < le.EnviosTotales();i++) {
			Envio envio = le.getEnvioParaDato(i);
			if(envio instanceof EnvioE) {
				EnvioE envioE = (EnvioE) envio;
				int costo = envioE.calcularEnvio();
				data  += envioE.getCodigo() + " " + costo + "\n";
			}
			
		}
		data += "Valijas:" + "\n";  
		for(int i = 0; i < le.EnviosTotales();i++) {
			Envio envio = le.getEnvioParaDato(i);
			if(envio instanceof EnvioV) {
				EnvioV envioV = (EnvioV) envio;
				int costo = envioV.calcularEnvio();
				data  += envioV.getCodigo() + " " + costo + "\n";
			}
			
		}
		}catch(NullPointerException e){
			StdOut.println("Error elemento nulo");
		}
		return data;
	}

	@Override
	public String obtenerEnviosPorRut() {
		String data = "";
		for(Cliente cliente: lCliente) {
			data += "Cliente: " + cliente.getRut() +"\n";
			for(int i = 0; i < cliente.getEnviados().EnviosTotales();i++) {
				data  += cliente.getEnviados().getEnvioParaDato(i).getCodigo() + "\n";
			}
			for(int i = 0; i < cliente.getRecibidos().EnviosTotales(); i++) {
				data += cliente.getRecibidos().getEnvioParaDato(i).getCodigo() + "\n";
			}
		}
		return data;
	}

	@Override
	public String obtenerEnviosPorCiudad() {
		String data = "";
		for(Ciudad ciudad: lc) {
			data += (ciudad.getNombre() + " realizo " + ciudad.getEnviados().EnviosTotales() + " envios y recibio " + ciudad.getRecibidos().EnviosTotales() + " envios"+ "\n");
		}
		return data;
	}

	public boolean inicioSesion(String rut) {
		for(Cliente cliente: lCliente) {
			if(cliente.getRut().equals(rut)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public String dataCl() {
		String data = "";
		for(Cliente cliente: lCliente) {
			data+= cliente.getRut() + "," + cliente.getNombre() + "," + cliente.getApellido() + "," + cliente.getSaldo() + "," + cliente.getCiudad().getNombre() + "\n";
		}
		return data;
	}

	@Override
	public String dataEnv() {
		Envio envio = null;
		String data = "";
		for(int i = 0; i < le.EnviosTotales(); i++) {
			envio = le.getEnvioParaDato(i);
			if(envio instanceof EnvioD) {
				EnvioD envioD = (EnvioD) envio;
				data += envioD.getCodigo() + ",D," + envioD.getCliente1().getRut() + "," + envioD.getCliente2().getRut() + "," + envioD.getPeso() + "," + envioD.getGrosor() + "\n";
			}
			if(envio instanceof EnvioE) {
				EnvioE envioE = (EnvioE) envio;
				data += envioE.getCodigo() + ",E," + envioE.getCliente1().getRut() + "," + envioE.getCliente2().getRut() + "," + envioE.getPeso() + "," + envioE.getLargo() + "," +envioE.getAncho() + "," + envioE.getProfundidad() + "\n";
			}
			if(envio instanceof EnvioV) {
				EnvioV envioV = (EnvioV) envio;
				data += envioV.getCodigo() + ",V," + envioV.getCliente1().getRut() + "," + envioV.getCliente2().getRut() + "," + envioV.getMaterial() + "," + envioV.getPeso() + "\n";
			}
		}
		
		return data;
	}



	

}
