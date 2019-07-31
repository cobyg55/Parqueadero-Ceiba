package co.com.ceiba.parqueaderoadn.rest;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.parqueaderoadn.domain.Parqueo;
import co.com.ceiba.parqueaderoadn.domain.service.ParqueoService;

@RestController
@RequestMapping(value = "/api/v1/parqueo", produces = MediaType.APPLICATION_JSON_VALUE)
public class ParqueoController {

	@Autowired
	private ParqueoService parqueoService;

	@GetMapping("/{placa}")
	public @ResponseBody Parqueo consultarPorPlaca(@PathVariable String placa) throws Exception {
		return parqueoService.consultarParqueoConValorCobro(placa, LocalDateTime.now());
	}

	@PostMapping
	public @ResponseBody Parqueo registrarIngreso(@RequestBody Parqueo parqueo) throws Exception {
		return parqueoService.registrarIngreso(parqueo);
	}

	@PutMapping("/{placa}")
	public @ResponseBody Parqueo registrarSalida(@PathVariable String placa) throws Exception {
		return parqueoService.registrarSalida(placa);
	}
}
