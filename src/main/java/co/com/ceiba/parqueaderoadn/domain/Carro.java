package co.com.ceiba.parqueaderoadn.domain;

public class Carro extends Vehiculo {

	public Carro() {
		super(TipoVehiculo.CARRO);
	}

	public Carro(String placa, int cilindraje) {
		super(placa, cilindraje, TipoVehiculo.CARRO);
	}

}