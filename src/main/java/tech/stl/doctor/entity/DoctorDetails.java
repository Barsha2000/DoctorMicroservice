package tech.stl.doctor.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class DoctorDetails {
        @Id
        private int doctorId;
        @NotEmpty(message = "Enter your education details")
        private String education;
        @NotEmpty(message = "Please specify your work experience")
        private String workExperience;
        @NotEmpty(message="Mention your areas of interest")
        private String interest;
        
        public String getEducation() {
            return education;
        }
        public void setEducation(String education) {
            this.education = education;
        }
        public String getWorkExperience() {
            return workExperience;
        }
        public void setWorkExperience(String workExperience) {
            this.workExperience = workExperience;
        }
        public String getInterest() {
            return interest;
        }
        public void setInterest(String interest) {
            this.interest = interest;
        }

}

