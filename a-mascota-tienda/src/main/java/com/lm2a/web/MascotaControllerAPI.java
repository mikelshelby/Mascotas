package com.lm2a.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lm2a.data.MascotaRepository;
import com.lm2a.model.Mascota;

@RestController
@RequestMapping(path = "/rest", produces = "application/json")
public class MascotaControllerAPI {

	@Autowired
	private MascotaRepository mascotaRepo;

	// El user hara un get en localhost:8080/rest/1
	@GetMapping("/{id}")
	public ResponseEntity<Mascota> mascotaById(@PathVariable("id") Long id) {
		Optional<Mascota> optMascota = mascotaRepo.findById(id);
		if (optMascota.isPresent()) {
			return new ResponseEntity<>(optMascota.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping(path = "/maskotas", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Mascota postMascota(@RequestBody Mascota mascota) {
		return mascotaRepo.save(mascota);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteMascota(@PathVariable("id") Long id) {
		mascotaRepo.deleteById(id);
	}
	
	@GetMapping(path = "/all")
	public Iterable<Mascota> getAllMascotas() {
		return mascotaRepo.findAll();
	}

	@GetMapping(path = "/recent")
	public Iterable<Mascota> getRecentMascotas() {
		PageRequest page = PageRequest.of(0, 20, Sort.by("petBirth"));
		return mascotaRepo.findAll(page).getContent();
	}
}
