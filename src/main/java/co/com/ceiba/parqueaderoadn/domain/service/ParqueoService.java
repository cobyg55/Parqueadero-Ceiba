package co.com.ceiba.parqueaderoadn.domain.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.parqueaderoadn.domain.Parqueo;
import co.com.ceiba.parqueaderoadn.domain.TipoVehiculo;
import co.com.ceiba.parqueaderoadn.infraestructure.entity.ParqueoEntity;
import co.com.ceiba.parqueaderoadn.infraestructure.entity.VehiculoEntity;
import co.com.ceiba.parqueaderoadn.infraestructure.repository.ParqueoRepository;
import co.com.ceiba.parqueaderoadn.infraestructure.repository.VehiculoRepository;

@Service
public class ParqueoService {

	public static final int MAX_CUPO_MOTOS = 1;
	public static final int MAX_CUPO_CARROS = 20;

	@Autowired
	private ParqueoRepository parqueoRepository;

	@Autowired
	private VehiculoRepository vehiculoRepository;

	@Autowired
	private CobroService CobroService;

	@Autowired
	private ModelMapper modelMapper;

	public Parqueo consultarParqueoConValorCobro(String placa, LocalDateTime fechaSalida) {
		ParqueoEntity parqueoEntity = parqueoRepository.obtenerParqueoPorPlaca(placa);
		parqueoEntity.setFechaSalida(fechaSalida);
		parqueoEntity.setValorCobro(CobroService.calcularValorCobroParqueo(parqueoEntity.getFechaIngreso(),
				parqueoEntity.getFechaSalida(), TipoVehiculo.valueOf(parqueoEntity.getVehiculo().getTipo())));
		return modelMapper.map(parqueoEntity, Parqueo.class);
	}

	public Parqueo registrarIngreso(Parqueo parqueo) throws Exception {
		boolean existeCupo = exiteDisponibilidadPorTipoVehiculo(parqueo.getVehiculo().getTipo());
		String placa = parqueo.getVehiculo().getPlaca();
		LocalDateTime fechaIngreso = LocalDateTime.now();
		if (!existeCupo) {
			throw new Exception("No hay cupo disponible");
		}

		if (placa.toUpperCase().startsWith("A") && fechaIngreso.getDayOfWeek().equals(DayOfWeek.MONDAY)
				|| placa.toUpperCase().startsWith("A") && fechaIngreso.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			throw new Exception("No esta autorizado a ingresa");
		}
		VehiculoEntity vehiculoEntity = vehiculoRepository.findByPlaca(parqueo.getVehiculo().getPlaca());
		if (vehiculoEntity == null) {
			vehiculoEntity = new VehiculoEntity();
			vehiculoEntity.setPlaca(parqueo.getVehiculo().getPlaca());
			vehiculoEntity.setTipo(parqueo.getVehiculo().getTipo().toString());
			vehiculoEntity.setCilindraje(parqueo.getVehiculo().getCilindraje());
			vehiculoRepository.save(vehiculoEntity);
		}
		ParqueoEntity parqueoEntity = modelMapper.map(parqueo, ParqueoEntity.class);
		parqueoEntity.setFechaIngreso(fechaIngreso);
		parqueoEntity.setVehiculo(vehiculoEntity);
		return modelMapper.map(parqueoRepository.save(parqueoEntity), Parqueo.class);
	}

	public Parqueo registrarSalida(String placa) {
		ParqueoEntity parqueoEntity = parqueoRepository.obtenerParqueoPorPlaca(placa);
		parqueoEntity.setFechaSalida(LocalDateTime.now());
		parqueoEntity.setValorCobro(CobroService.calcularValorCobroParqueo(parqueoEntity.getFechaIngreso(),
				parqueoEntity.getFechaSalida(), TipoVehiculo.valueOf(parqueoEntity.getVehiculo().getTipo())));
		return modelMapper.map(parqueoRepository.save(parqueoEntity), Parqueo.class);
	}

	public boolean exiteDisponibilidadPorTipoVehiculo(TipoVehiculo tipoVehiculo) {
		int celdasOcupadas = parqueoRepository.obtenerCeldasOcupadasPorTipoVehiculo(tipoVehiculo.toString());
		int maximoCupo = 0;
		if (TipoVehiculo.CARRO.equals(tipoVehiculo)) {
			maximoCupo = MAX_CUPO_CARROS;
		} else {
			maximoCupo = MAX_CUPO_MOTOS;
		}
		return celdasOcupadas == maximoCupo ? false : true;
	}
}
