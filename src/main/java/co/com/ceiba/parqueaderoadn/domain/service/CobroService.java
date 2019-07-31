package co.com.ceiba.parqueaderoadn.domain.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import co.com.ceiba.parqueaderoadn.domain.Precio;
import co.com.ceiba.parqueaderoadn.domain.TipoVehiculo;

@Service
public class CobroService {

	public double calcularValorCobroParqueo(LocalDateTime fechaIngreso, LocalDateTime fechaSalida,
			TipoVehiculo tipoVehiculo) {
		Precio precio = new Precio().getPrecio(tipoVehiculo);

		int horasPorCalcular = (int) calcularTiempoEnHoras(fechaIngreso, fechaSalida);

		int diasPorFacturar = 0;
		int horasPorFacturar = 0;

		while (horasPorCalcular > 0) {
			if (horasPorCalcular >= 9 && horasPorCalcular > 24) {
				// Resta 24 hrs a la pila y suma un dia
				horasPorCalcular = horasPorCalcular - 24;
				diasPorFacturar = diasPorFacturar + 1;
			} else if (horasPorCalcular >= 9 && horasPorCalcular <= 24) {
				// Hace que la pila de horas sea cero y agrega un dia
				horasPorCalcular = 0;
				diasPorFacturar = diasPorFacturar + 1;
			} else if (horasPorCalcular < 9) {
				// Sumar las horas restantes de la pila a las horas por facturar
				horasPorFacturar = horasPorCalcular;
				horasPorCalcular = 0;
			}
		}

		return (diasPorFacturar * precio.getValorDia()) + (horasPorFacturar * precio.getValorHora())
				+ precio.getValorAdicional();
	}

	public long calcularTiempoEnHoras(LocalDateTime fechaIngreso, LocalDateTime fechaSalida) {
		long minutes = ChronoUnit.MINUTES.between(fechaIngreso, fechaSalida);
		return (minutes + (60 - minutes % 60)) / 60;
	}

}
