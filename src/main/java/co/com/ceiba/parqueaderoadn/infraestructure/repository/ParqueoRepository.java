package co.com.ceiba.parqueaderoadn.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.ceiba.parqueaderoadn.infraestructure.entity.ParqueoEntity;

public interface ParqueoRepository extends JpaRepository<ParqueoEntity, Long> {

	@Query("SELECT COUNT(*) FROM ParqueoEntity p WHERE p.vehiculo.tipo = :tipoVehiculo AND p.fechaSalida IS NULL")
	public int obtenerCeldasOcupadasPorTipoVehiculo(@Param(value = "tipoVehiculo") String tipoVehiculo);

	@Query("SELECT p FROM ParqueoEntity p WHERE p.vehiculo.placa = :placa")
	public ParqueoEntity obtenerParqueoPorPlaca(@Param(value = "placa") String placa);

	@Query("SELECT p FROM ParqueoEntity p WHERE p.vehiculo.placa = :placa AND p.fechaSalida IS NULL")
	public ParqueoEntity existeVehiculoEnParqueaderoPorPlaca(@Param(value = "placa") String placa);
}
