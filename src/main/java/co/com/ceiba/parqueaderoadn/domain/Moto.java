package co.com.ceiba.parqueaderoadn.domain;

public class Moto extends Vehiculo {

	public Moto() {
		super(TipoVehiculo.MOTO);
	}

	public Moto(String placa, int cilindraje) {
		super(placa, cilindraje, TipoVehiculo.MOTO);
	}

}
