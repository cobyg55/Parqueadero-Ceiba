package co.com.ceiba.parqueaderoadn.infraestructure.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "parqueo")
public class ParqueoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "fechaIngreso", nullable = false)
	private LocalDateTime fechaIngreso;

	@Column(name = "fechaSalida", nullable = true)
	private LocalDateTime fechaSalida;

	@Column(name = "valorCobro", nullable = true)
	private double valorCobro;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private VehiculoEntity vehiculo;

	public ParqueoEntity() {

	}

	public ParqueoEntity(LocalDateTime fechaIngreso, LocalDateTime fechaSalida, double valorCobro,
			VehiculoEntity vehiculo) {
		super();
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.valorCobro = valorCobro;
		this.vehiculo = vehiculo;
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

	public VehiculoEntity getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(VehiculoEntity vehiculo) {
		this.vehiculo = vehiculo;
	}

}
