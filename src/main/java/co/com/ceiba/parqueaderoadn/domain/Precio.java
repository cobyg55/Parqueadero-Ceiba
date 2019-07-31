package co.com.ceiba.parqueaderoadn.domain;

public class Precio {

	private static final double VALOR_HORA_MOTO = 500;
	private static final double VALOR_HORA_CARRO = 1000;
	private static final double VALOR_DIA_MOTO = 4000;
	private static final double VALOR_DIA_CARRO = 8000;
	private static final double VALOR_ADICIONAL_MOTO = 2000;

	private double valorHora;
	private double valorDia;
	private double valorAdicional;

	public Precio() {
		super();
	}

	private Precio(double valorHoraMoto, double valorDiaMoto, double valorAdicionalMotos) {
		super();
		this.valorHora = valorHoraMoto;
		this.valorDia = valorDiaMoto;
		this.valorAdicional = valorAdicionalMotos;
	}

	private Precio(double valorHoraCarro, double valorDiaCarro) {
		super();
		this.valorHora = valorHoraCarro;
		this.valorDia = valorDiaCarro;
		this.valorAdicional = 0;
	}

	public Precio getPrecio(TipoVehiculo tipoVehiculo) {
		Precio precio = null;
		switch (tipoVehiculo) {
		case CARRO:
			precio = new Precio(VALOR_HORA_CARRO, VALOR_DIA_CARRO);
			break;
		case MOTO:
			precio = new Precio(VALOR_HORA_MOTO, VALOR_DIA_MOTO, VALOR_ADICIONAL_MOTO);
			break;
		}
		return precio;
	}

	public double getValorHora() {
		return valorHora;
	}

	public void setValorHora(double valorHora) {
		this.valorHora = valorHora;
	}

	public double getValorDia() {
		return valorDia;
	}

	public void setValorDia(double valorDia) {
		this.valorDia = valorDia;
	}

	public double getValorAdicional() {
		return valorAdicional;
	}

	public void setValorAdicional(double valorAdicional) {
		this.valorAdicional = valorAdicional;
	}

}
