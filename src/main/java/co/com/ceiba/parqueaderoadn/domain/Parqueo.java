package co.com.ceiba.parqueaderoadn.domain;

import java.time.LocalDateTime;

public class Parqueo {

	private long id;

	private LocalDateTime fechaIngreso;

	private LocalDateTime fechaSalida;

	private double valorCobro;

	private Vehiculo vehiculo;

	public Parqueo() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public double getValorCobro() {
		return valorCobro;
	}

	public void setValorCobro(double valorCobro) {
		this.valorCobro = valorCobro;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}
