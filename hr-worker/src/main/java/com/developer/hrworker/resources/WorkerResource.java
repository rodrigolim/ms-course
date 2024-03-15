package com.developer.hrworker.resources;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.hrworker.entities.Worker;
import com.developer.hrworker.exception.ResourceNotFoundException;
import com.developer.hrworker.repositories.WorkerRepository;

@RestController
@RequestMapping("/api/workers")
public class WorkerResource {

	@Autowired
	private WorkerRepository workerRepository;

	@PostMapping
	public  ResponseEntity<Worker> createWorker(@RequestBody Worker worker) {
		Worker createdWorker = workerRepository.save(worker);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdWorker);
	}

	@GetMapping
	public ResponseEntity<List<Worker>> findAllWorkers() {
		List<Worker> workers = workerRepository.findAll();
		return ResponseEntity.ok().body(workers);
	}

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

}
