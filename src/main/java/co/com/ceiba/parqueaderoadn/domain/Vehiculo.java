package co.com.ceiba.parqueaderoadn.domain;

public class Vehiculo {

	private String placa;
	private TipoVehiculo tipo;
	private int cilindraje;

	public Vehiculo() {

	}

	public Vehiculo(String placa, int cilindraje, TipoVehiculo tipo) {
		super();
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.tipo = tipo;
	}

	public Vehiculo(TipoVehiculo tipoVehiculo) {
		super();
		this.tipo = tipoVehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}

	public TipoVehiculo getTipo() {
		return tipo;
	}

	public void setTipo(TipoVehiculo tipo) {
		this.tipo = tipo;
	}

}
