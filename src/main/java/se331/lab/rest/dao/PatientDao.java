package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import se331.lab.rest.entity.Patient;

public interface PatientDao {
    Page<Patient> getPatient(Integer perPage, Integer page);

    void save(Patient build);
}
