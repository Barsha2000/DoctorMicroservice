package tech.stl.doctor.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.stl.doctor.entity.Doctor;
import tech.stl.doctor.entity.Specialization;
import tech.stl.doctor.exception.ResourceNotFoundException;
import tech.stl.doctor.service.DoctorService;

@RestController
public class DoctorController {
	@Autowired
	private DoctorService doctorService;

	@GetMapping("/doctors")
	public ResponseEntity<List<Doctor>> getDoctors() {
		List<Doctor> list = doctorService.getAllDoctor();
		if (list.size() <= 0) {
			throw new ResourceNotFoundException("Doctor List is empty");

		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}

	@GetMapping("/doctors/specialization/{specialization}")
	public ResponseEntity<List<Doctor>> getBySpecialization(
			@PathVariable("specialization") Specialization specialization) {
		List<Doctor> list = doctorService.getDoctorBySpecialization(specialization);
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}

	@GetMapping("/doctors/name/{doctorName}")
	public ResponseEntity<List<Doctor>> getBydoctorName(@Valid @PathVariable("doctorName") String doctorName) {
		List<Doctor> list = doctorService.getDoctorBydoctorName(doctorName);
		if (list.size() <= 0) {
			throw new ResourceNotFoundException("No Doctor present in the list with the given name");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}

	@GetMapping("/doctors/{id}")
	public ResponseEntity<Doctor> getDoctor(@Valid @PathVariable("id") int doctorId) {
		Doctor doctor = doctorService.getDoctorById(doctorId);
		if (doctor == null) {
			throw new ResourceNotFoundException("Doctor is not present with the id:" + doctorId);
		}
		return ResponseEntity.of(Optional.of(doctor));
	}

	@PostMapping("/doctors")
	public ResponseEntity<Doctor> addDoctor(@Valid @RequestBody Doctor doctor) {
		Doctor b = null;
		try {
			b = this.doctorService.addDoctor(doctor);
			System.out.println(doctor);
			return ResponseEntity.of(Optional.of(b));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

	@DeleteMapping("/doctors/{doctorid}")
	public ResponseEntity<?> deleteDoctor(@Valid @PathVariable("doctorid") int doctorId) {
		try {
			this.doctorService.deleteDoctor(doctorId);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok("Deleted succesfully");
	}

	@PutMapping("/doctors/{doctorId}")
	public ResponseEntity<Doctor> updateDoctor(@Valid @RequestBody Doctor doctor,
			@PathVariable("doctorId") int doctorId) {
		try {
			this.doctorService.updateDoctor(doctor, doctorId);
			return ResponseEntity.ok().body(doctor);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}