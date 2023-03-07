package tech.stl.doctor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import tech.stl.doctor.entity.Doctor;
import tech.stl.doctor.entity.Specialization;
import tech.stl.doctor.repository.DoctorRepository;
import tech.stl.doctor.service.DoctorService;



@SpringBootTest(classes= {ServiceMockitoTest.class})
public class ServiceMockitoTest {
	
	
	private static final @NotEmpty Specialization Cardiologiost = null;
	
	@Mock
	DoctorRepository doctorRepository;
	@InjectMocks
	DoctorService doctorService;
	
	public List<Doctor> doctors;
	
	@Test
	@Order(1)
	public void test_getAllDoctor() {
		List<Doctor> doctors =new ArrayList<Doctor>();
		doctors.add(new Doctor(1,"Samuel",Cardiologiost,"MBBS"));
		doctors.add(new Doctor(2,"Sam",Cardiologiost,"MBBS"));
		when(doctorRepository.findAll()).thenReturn(doctors);	
		assertEquals(2,doctorService.getAllDoctor().size());
	}
	@Test
	@Order(2)
	public void test_getDoctorById() {
		Doctor doctors=(new Doctor(1,"Samuel",Cardiologiost,"MBBS"));
        when(doctorRepository.findById(1)).thenReturn(doctors);//Mocking
		
		assertEquals(doctors,doctorService.getDoctorById(1));
        
	}
	@Test
	@Order(3)
	public void test_updateDoctor() {
		Doctor doctor=(new Doctor(1,"Samuel",Cardiologiost,"MBBS"));
       doctor.setDoctorName("Sandra");
       doctorRepository.save(doctor);
       assertEquals("Sandra",doctor.getDoctorName());
	}
	@Test
	@Order(4)
	public void  test_saveDoctor() {
		Doctor doctor=(new Doctor(1,"Samuel",Cardiologiost,"MBBS"));
    	when(doctorRepository.save(doctor)).thenReturn(doctor);
    	assertEquals(doctor, doctorService.addDoctor(doctor));
		
	}
	@Test
     @Order(5)
	public void test_deleteDoctorById() {
		Doctor doctor=(new Doctor(1,"Samuel",Cardiologiost,"MBBS"));
		doctorRepository.deleteById(doctor.getDoctorId());        
        Doctor deletedDoctor = doctorRepository.findById(1);         
        assertThat(deletedDoctor).isNull();
    }
	@Test
	@Order(6)
	public void test_getDoctorBySpecialization() {
		List<Doctor> doctor =new ArrayList<Doctor>();
		doctor.add(new Doctor(1,"Samuel",Cardiologiost,"MBBS"));
        when(doctorRepository.findBySpecialization(Cardiologiost)).thenReturn(doctor);//Mocking
		
        assertEquals(doctor,doctorService.getDoctorBySpecialization(Cardiologiost));
      
	}
	@Test
	@Order(7)
	public void test_getDoctorBydoctorName() {
		List<Doctor> doctor =new ArrayList<Doctor>();
		doctor.add(new Doctor(1,"Samuel",Cardiologiost,"MBBS"));
        when(doctorRepository.findBydoctorName("Samuel")).thenReturn(doctor);//Mocking
		
        assertEquals(doctor,doctorService.getDoctorBydoctorName("Samuel"));
      
	}

}
