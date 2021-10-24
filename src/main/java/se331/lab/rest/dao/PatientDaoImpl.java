package se331.lab.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.repository.PatientRepository;

@Repository
@Profile("db")
public class PatientDaoImpl implements PatientDao{
    @Autowired
    PatientRepository patientRepository;
    @Override
    public Page<Patient> getPatient(Integer perPage, Integer page) {
        return patientRepository.findAll(PageRequest.of(page-1,perPage));
    }

    @Override
    public void save(Patient build) {
        patientRepository.save(build);
    }
}
