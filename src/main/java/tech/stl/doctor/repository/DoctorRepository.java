package tech.stl.doctor.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.stl.doctor.entity.Doctor;
import tech.stl.doctor.entity.Specialization;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {


    public List<Doctor> findBySpecialization(Specialization specialization);
    public Doctor findById(int doctorId);
    public List<Doctor> findBydoctorName(String doctorName);

}