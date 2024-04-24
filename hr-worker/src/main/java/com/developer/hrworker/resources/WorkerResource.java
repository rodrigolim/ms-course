package com.developer.hrworker.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

import com.developer.hrworker.entities.Worker;
import com.developer.hrworker.exception.ResourceNotFoundException;
import com.developer.hrworker.repositories.WorkerRepository;

@RefreshScope
@RestController
@RequestMapping("/api/workers")
public class WorkerResource {

	private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);

	@Value("${test.config}")
	private String testConfig;

	@Autowired
	private WorkerRepository workerRepository;

	@GetMapping(value = "/configs")
	public ResponseEntity<Void> getConfigs() {
		logger.info("CONFIG = " + testConfig);
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public  ResponseEntity<Worker> createWorker(@RequestBody Worker worker) {
		Worker createdWorker = workerRepository.save(worker);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdWorker);
	}

	@GetMapping
	public ResponseEntity<List<Worker>> findFilter( Worker filtro ){
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(
						ExampleMatcher.StringMatcher.CONTAINING );

		Example example = Example.of(filtro, matcher);
		List<Worker> workers = workerRepository.findAll(example);
		return ResponseEntity.ok().body(workers);
	}

/*	@GetMapping
	public ResponseEntity<List<Worker>> findAllWorkers() {
		List<Worker> workers = workerRepository.findAll();
		return ResponseEntity.ok().body(workers);
	}*/

	@GetMapping("/{id}")
	public ResponseEntity<Worker> findWorkerById(@PathVariable Long id) {
		Worker worker = workerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Worker not found with id: " + id));
		return ResponseEntity.ok().body(worker);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Worker> updateWorker(@PathVariable Long id, @RequestBody Worker workerDetails) {
		Worker worker = workerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Worker not found with id: " + id));

		BeanUtils.copyProperties(workerDetails, worker, "id"); // Ignora a cópia do ID

		Worker updatedWorker = workerRepository.save(worker);
		return ResponseEntity.ok().body(updatedWorker);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteWorker(@PathVariable Long id) {
		Worker worker = workerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Worker not found with id: " + id));

		workerRepository.delete(worker);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/public")
	String publicRoute() {
		return "<h1>Public route, feel free to look around! 🔓 </h1>";
	}

	@GetMapping("/private")
	String privateRoute(@AuthenticationPrincipal OidcUser principal) {
		return String.format("""
				<h1>Private route, only authorized personal! 🔐  </h1>
				""");
	}

}
