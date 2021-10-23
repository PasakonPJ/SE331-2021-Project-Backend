package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import se331.lab.rest.entity.Patient;

public interface PatientService {
    Page<Patient> getPatients(Integer perPage, Integer page);
}
