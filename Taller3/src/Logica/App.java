package Logica;
import java.io.IOException;
import java.util.Random;

import ucn.ArchivoEntrada;
import ucn.ArchivoSalida;
import ucn.Registro;
import ucn.StdIn;
import ucn.StdOut;

public class App {
    /**
     * This is the main of the program 
     * @param args
     * @throws IOException
     */
	public static void main(String[] args) throws IOException {
		SistemaEnvios sistema = new SistemaEnviosImpl();
		LeerCiudades(sistema);
		LeerClientes(sistema);
		LeerEnvios(sistema);
		InicioSesion(sistema);
		Cierre_sistema(sistema);
	}
	/**
	 * This function is the login of the program
	 * @param sistema
	 */
	public static void InicioSesion(SistemaEnvios sistema) {
		boolean ingreso = false;
		String rut = "";
		String clave = "";
		do {
			StdOut.print("Digite su rut: ");
			rut = StdIn.readString();
			if(rut.equals("Admin")) {
				StdOut.print("Digite clave: ");
				clave = StdIn.readString();
				if(clave.equals("choripan123")) {
					MenuAdmin(sistema);
					ingreso = true;
				}
			}
			else {
				ingreso = sistema.inicioSesion(rut);
				if(ingreso == false) {
					StdOut.print("Desea registrarse (si/no): ");
					String respuesta = StdIn.readString();
					while(!respuesta.equals("si") && !respuesta.equals("no")) {
						StdOut.println("Respuesta incorrecta");
						StdOut.print("Desea registrarse (si/no): ");
						respuesta = StdIn.readString();
					}
					if(respuesta.equals("si")) {
						Registro(sistema,rut);
					}
				}
			}
		}while(ingreso == false);
		if(!rut.equals("Admin")){
			MenuCliente(sistema,rut);
		}
	}
	/**
	 * this function register a user to the system
	 * @param sistema
	 * @param rut
	 */
	public static void Registro(SistemaEnvios sistema, String rut) {
		StdOut.print("Nombre: ");
		String nombre = StdIn.readString();
		StdOut.print("Apellido: ");
		String apellido = StdIn.readString();
		sistema.addCliente(rut, nombre, apellido, 0);
	}
	/**
	 * this is the menu of the admin
	 * @param sistema
	 */
	public static void MenuAdmin(SistemaEnvios sistema) {
		String respuesta = "";
		do {
			StdOut.println("1.- Entregas por tipo");
			StdOut.println("2.- Entregas por localizacion");
			StdOut.println("3.- Entregas por cliente");
			StdOut.println("4.- Registro de ganancias");
			StdOut.println("5.- Salir");
			respuesta = StdIn.readString();
			if(respuesta.equals("1")) {
				StdOut.println(sistema.obtenerEnviosPorTipo());
			}
			if(respuesta.equals("2")) {
				StdOut.println(sistema.obtenerEnviosPorCiudad());
			}
			if(respuesta.equals("3")) {
				StdOut.println(sistema.obtenerEnviosPorRut());
			}
			if(respuesta.equals("4")) {
				
			}
			
		}while(!respuesta.equals("5"));
	}
	/**
	 * this is the menu of the client
	 * @param sistema
	 * @param rut
	 */
	public static void MenuCliente(SistemaEnvios sistema, String rut) {
		String respuesta = "";
		do {
			StdOut.println("1.- Realizar entrega");
			StdOut.println("2.- Recargar Saldo");
			StdOut.println("3.- Ver entregas");
			StdOut.println("4.- Salir");
			respuesta = StdIn.readString();
			if(respuesta.equals("1")) {
			
			}
			if(respuesta.equals("2")) {
				
			}
			if(respuesta.equals("3")) {
				StdOut.println(sistema.verEntregas(rut));
			}
		}while(!respuesta.equals("4"));
	}
	/**
	 * this function add money to the client
	 * @param sistema
	 * @param rut
	 */
	public static void RecargarSaldo(SistemaEnvios sistema, String rut) {
		StdOut.print("Ingrese saldo a ingresar: ");
		int saldo = StdIn.readInt();
		while(saldo <= 0) {
			StdOut.print("Valor invalido, ingrese nuevamente: ");
			saldo = StdIn.readInt();
		}
		sistema.recargarSaldo(rut, saldo);
	}
	/**
	 * This function read the file "Entregas.txt"
	 * @param sistema
	 * @throws IOException
	 */
	public static void LeerEnvios(SistemaEnvios sistema) throws IOException {
		try {
			ArchivoEntrada arch1 = new ArchivoEntrada("Entregas.txt");
			while(!arch1.isEndFile()) {
				Registro reg1 = arch1.getRegistro();
				String codigo   = reg1.getString();
				String tipo = reg1.getString();
				String rutRemitente = reg1.getString();
				String rutDestinatario = reg1.getString();
			    if (tipo.equals("D")) {
			    	int peso = reg1.getInt();
			    	int grosor = reg1.getInt();
			    	sistema.addEnvioD(codigo, peso,grosor);
			    }
			    if(tipo.equals("E")) {
			    		int peso = reg1.getInt();
			    		int largo = reg1.getInt();
			    		int ancho = reg1.getInt();
			    		int profundidad = reg1.getInt();
			    		sistema.addEnvioE(codigo,peso,largo,ancho,profundidad);
			    }
			    if(tipo.equals("V")) {
		    		String material = reg1.getString();
			    	int peso = reg1.getInt();
		    		sistema.addEnvioV(codigo,peso,material);
			    }
			    sistema.AsociarEnvios(rutRemitente, rutDestinatario, codigo);
		   }
		   arch1.close();
			
		}catch(IOException ex) {
			StdOut.println("Ha ocurrido un error");
		}
		
	}
	/**
	 * This function read the file "Cliente.txt"
	 * @param sistema
	 * @throws IOException
	 */
	public static void LeerClientes(SistemaEnvios sistema) throws IOException {
		try {
			ArchivoEntrada arch1 = new ArchivoEntrada("Cliente.txt");
			while(!arch1.isEndFile()) {
				Registro reg1 = arch1.getRegistro();
				String rut   = reg1.getString();
				String nombre = reg1.getString();
				String apellido = reg1.getString();
				int saldo = reg1.getInt();
				String nombreCiudad = reg1.getString();
				sistema.addCliente(rut, nombre, apellido, saldo);
				sistema.AsociarCiudad(rut, nombreCiudad);
		   }
		   arch1.close();
			
		}catch(IOException ex) {
			StdOut.println("Ha ocurrido un error");
		}
		
	}
	/**
	 * this function makes a shipping
	 * @param sistema
	 * @param rut
	 */
	public static void RealizarEnvio(SistemaEnvios sistema, String rut) {
		StdOut.print("Tipo de envio: ");
		String tipo = StdIn.readString();
		StdOut.print("Peso: ");
		int peso = StdIn.readInt();
		StdOut.print("");
		int codigo = new Random().nextInt(900000) + 100000;
		String codigoS = Integer.toString(codigo);
		if(tipo.equals("Documento")) {
			StdOut.print("Ingrese grosor: ");
			int grosor = StdIn.readInt();
			sistema.addEnvioD(codigoS, peso, grosor);	
		}
		if(tipo.equals("Encomienda")) {
			StdOut.print("Ingrese largo: ");
			int largo = StdIn.readInt();
			StdOut.print("Ingrese ancho: ");
			int ancho = StdIn.readInt();
			StdOut.print("Ingrese profundidad: ");
			int profundidad = StdIn.readInt();
			sistema.addEnvioE(codigoS,peso,largo,ancho,profundidad);
		}
		if(tipo.equals("Valija")) {
			StdOut.print("Ingrese material: ");
			String material = StdIn.readString();
			sistema.addEnvioV(codigoS, peso,material);	
		}
		StdOut.print("Rut destinatario: ");
		String rut2 = StdIn.readString();
		sistema.AsociarEnvios(rut, rut2, codigoS);
	}
	/**
	 * This function read the file "localización.txt"
	 * @param sistema
	 * @throws IOException
	 */
	public static void LeerCiudades(SistemaEnvios sistema) throws IOException {
		try {
			ArchivoEntrada arch1 = new ArchivoEntrada("localización.txt");
			while(!arch1.isEndFile()) {
				Registro reg1 = arch1.getRegistro();
				String nombre = reg1.getString();
				sistema.addCiudad(nombre);
		   }
		   arch1.close();
			
		}catch(IOException ex) {
			StdOut.println("Ha ocurrido un error");
		}
		
	}
	/**
	 * This function close the system and whrite the files
	 * @param sistema
	 * @throws IOException
	 */
	public static void Cierre_sistema(SistemaEnvios sistema) throws IOException {
		ArchivoSalida arch1 = new ArchivoSalida("Cliente.txt");
		Registro rg = new Registro(1);
		rg.agregarCampo(sistema.dataCl());
		arch1.writeRegistro(rg);
		arch1.close();
		
		ArchivoSalida arch2 = new ArchivoSalida("Entregas.txt");
		Registro rg1 = new Registro(1);
		rg1.agregarCampo(sistema.dataEnv());
		arch2.writeRegistro(rg);
		arch2.close();
		
	}
	

}
