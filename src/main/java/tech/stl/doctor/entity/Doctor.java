package tech.stl.doctor.entity;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name="doctor")
@SQLDelete(sql = "UPDATE doctor SET deleted = true WHERE doctorId=?")
@Where(clause = "deleted = false")
public class Doctor {
    @Id
    private int doctorId;
    private String doctorName;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    @NotEmpty(message = "Mention your qualification details")
    private String qualification;
  
    
    private boolean deleted = Boolean.FALSE;
    
     public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
  
    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public String getDoctorName() {
        return doctorName;
    }
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    public Specialization getSpecialization() {
        return specialization;
    }
    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
    public String getQualification() {
        return qualification;
    }
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
   

    public Doctor(int doctorId, @NotEmpty String doctorName, @NotEmpty Specialization specialization,
            @NotEmpty String qualification) {
        super();
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.qualification = qualification;
       
    }
    public Doctor() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public String toString() {
        return "Doctor [doctorId=" + doctorId + ", doctorName=" + doctorName + ", specialization=" + specialization
                + ", qualification=" + qualification +  "]";
    }
}