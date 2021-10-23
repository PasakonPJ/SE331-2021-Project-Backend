package se331.lab.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    Long id;
    String username;
    String password;
    String email;
    DoctorPatientDTO doctor;
//    VaccinePatientDTO patientsVaccine;
}
