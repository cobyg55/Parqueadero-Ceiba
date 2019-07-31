package co.com.ceiba.parqueaderoadn.infraestructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.ceiba.parqueaderoadn.infraestructure.entity.VehiculoEntity;

public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Long> {

	public VehiculoEntity findByPlaca(String placa);
}
