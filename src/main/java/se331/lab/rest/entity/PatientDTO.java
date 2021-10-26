package se331.lab.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    Long id;
    String username;
    String firstname;
    String lastname;
    String imageurl;
    DoctorPatientDTO doctor;
    List<VaccinePatientDTO> vaccine;
}
