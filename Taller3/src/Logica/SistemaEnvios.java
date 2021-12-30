package Logica;

public interface SistemaEnvios {
	/**
	 * this function adds a envioD
	 * @param codigo
	 * @param peso
	 * @param grosor
	 * @return
	 */
	public boolean addEnvioD(String codigo,int peso, int grosor);
	/**
	 * this function adds a envioE
	 * @param codigo
	 * @param peso
	 * @param largo
	 * @param ancho
	 * @param profundidad
	 * @return
	 */
	public boolean addEnvioE(String codigo, int peso, int largo, int ancho, int profundidad);
	/**
	 * this function adds a envioV
	 * @param codigo
	 * @param peso
	 * @param material
	 * @return
	 */
	public boolean addEnvioV(String codigo, int peso, String material);
	/**
	 * this function associate a city to a client
	 * @param rut
	 * @param nombre
	 * @return
	 */
	public boolean AsociarCiudad(String rut, String nombre);
	/**
	 * this function associate a shipping to a clients 
	 * @param rut1
	 * @param rut2
	 * @param codigo
	 * @return
	 */
	public boolean AsociarEnvios(String rut1, String rut2, String codigo);
	/**
	 * this function adds a city to the system
	 * @param nombre
	 */
	public void addCiudad(String nombre);
	/**
	 * this function adds a client to the system
	 * @param rut
	 * @param nombre
	 * @param apellido
	 * @param saldo
	 */
    public void addCliente(String rut,String nombre,String apellido,int saldo);
    /**
     * this function returns the shipping for type
     * @return
     */
    public String obtenerEnviosPorTipo();
    /**
     * this function returns the shippings for clients
     * @return
     */
    public String obtenerEnviosPorRut();
    /**
     * this function returns the shippings for citys
     * @return
     */
    public String obtenerEnviosPorCiudad();
    /**
     * this function returns the data of a clients
     * @return
     */
    public String dataCl();
    /**
     * this function returns the data of a shippings
     * @return
     */
    public String dataEnv();
    /**
     * this function return a boolean for the login
     * @param rut
     * @return
     */
    public boolean inicioSesion(String rut);
    /**
     * this function returns the shippings of a client
     * @param rut
     * @return
     */
    public String verEntregas(String rut);
    /**
     * this function adds money to a client
     * @param rut
     * @param recarga
     * @return
     */
    public boolean recargarSaldo(String rut, int recarga);
}
