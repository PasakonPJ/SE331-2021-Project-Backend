package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import se331.lab.rest.entity.Patient;

import java.util.List;

public interface PatientDao {
    Page<Patient> getPatient(Integer perPage, Integer page);

    void save(Patient build);

    Patient findById(Long id);

    Page<Patient> getPatientDoctor(Integer perPage, Integer page);

    List<Patient> findByUsername(String id);

    Page<Patient> getPatientReady(Integer perPage, Integer page);
}
