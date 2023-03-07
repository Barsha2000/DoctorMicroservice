package tech.stl.doctor.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tech.stl.doctor.entity.Doctor;
import tech.stl.doctor.entity.Specialization;
import tech.stl.doctor.repository.DoctorRepository;

@Component
public class DoctorService {
	@Autowired
	private DoctorRepository doctorRepository;

	// get all doctor
	public List<Doctor> getAllDoctor() {
		return this.doctorRepository.findAll();

	}

	// get doctor by specialization
	public List<Doctor> getDoctorBySpecialization(Specialization specialization) {
		return this.doctorRepository.findBySpecialization(specialization);

	}

	// get doctor by id
	public Doctor getDoctorById(int doctorId) {

		return this.doctorRepository.findById(doctorId);

	}

	// add doctor
	public Doctor addDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);

	}

	// delete doctor
	public void deleteDoctor(int doctorId) {
		doctorRepository.deleteById(doctorId);

	}

	// update doctor
	public void updateDoctor(Doctor doctor, int doctorId) {
		doctor.setDoctorId(doctorId);
		doctorRepository.saveAndFlush(doctor);

	}

	// get doctor by name
	public List<Doctor> getDoctorBydoctorName(String doctorName) {

		return this.doctorRepository.findBydoctorName(doctorName);
	}
}
