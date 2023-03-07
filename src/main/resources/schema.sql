DROP TABLE IF EXISTS `Doctor`,`User`, `DoctorDetails`,`Appointment`;
CREATE TABLE `Doctor` (
  `doctorId` int PRIMARY KEY AUTO_INCREMENT,
  `doctorName` varchar (255),
  `specialization` ENUM ('Nutrition', 'Clinicalpsychology', 'NuclearMedicine', 'GeneralMedicine', 'Haemato_Oncology', 'Surgical_Oncology', 'Medical_Oncology', 'Radiation_Oncology'),
  `qualification` varchar(255),
  `deleted` boolean
);

CREATE TABLE `DoctorDetails` (
  `doctorId` int PRIMARY KEY,
  `education` varchar(255),
  `workExperience` varchar(255),
  `interest` varchar(255)
);

CREATE TABLE `Patient` (
  `patientId` int PRIMARY KEY AUTO_INCREMENT,
  `patientName` varchar(255),
  `emailId` varchar(255),
  `mobileNo` varchar(255),
  `deleted` boolean
);

CREATE TABLE `Appointment` (
  `appointmentId` int PRIMARY KEY AUTO_INCREMENT,
  `appointmentDate` date,
  `appointmentSlot` varchar(255),
  `status` ENUM ('INACTIVE', 'ACTIVE', 'DELETED'),
  `consultationType` varchar(255),
  `message` varchar(255),
  `deleted` boolean,
  `doctorId` int,
  `patientId` int
);

CREATE TABLE `User` (
  `username` varchar(255) PRIMARY KEY,
  `password` varchar(255)
);

ALTER TABLE `DoctorDetails` ADD FOREIGN KEY (`doctorId`) REFERENCES `Doctor` (`doctorId`);

ALTER TABLE `Appointment` ADD FOREIGN KEY (`doctorId`) REFERENCES `Doctor` (`doctorId`);

ALTER TABLE `Appointment` ADD FOREIGN KEY (`patientId`) REFERENCES `Patient` (`patientId`);

ALTER TABLE `User` ADD FOREIGN KEY (`username`) REFERENCES `Patient` (`patientName`);
