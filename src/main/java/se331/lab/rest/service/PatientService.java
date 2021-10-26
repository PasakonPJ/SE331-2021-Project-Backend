package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import se331.lab.rest.entity.Patient;

import java.util.List;

public interface PatientService {
    Page<Patient> getPatients(Integer perPage, Integer page);

    void save(Patient build);

    Patient findById(Long id);

    Page<Patient> getPatientsDoctor(Integer perPage, Integer page);

    List<Patient> findByUsername(String id);

    Page<Patient> getPatientReady(Integer perPage, Integer page);
}
