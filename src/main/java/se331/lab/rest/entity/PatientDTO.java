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
    List<CommentIdDTO> commentedPatient;
    DoctorPatientDTO doctor;
    List<VaccinePatientDTO> vaccine;
}
