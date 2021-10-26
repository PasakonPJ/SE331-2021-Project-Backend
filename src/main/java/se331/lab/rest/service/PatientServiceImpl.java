package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.PatientDao;
import se331.lab.rest.entity.Patient;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    PatientDao patientDao;
    @Override
    public Page<Patient> getPatients(Integer perPage, Integer page) {
        return patientDao.getPatient(perPage,page);
    }

    @Override
    public void save(Patient build) {
        patientDao.save(build);
    }

    @Override
    public Patient findById(Long id) {
        return patientDao.findById(id);
    }

    @Override
    public Page<Patient> getPatientsDoctor(Integer perPage, Integer page) {
        return patientDao.getPatientDoctor( perPage,  page);
    }

    @Override
    public List<Patient> findByUsername(String id) {
        return patientDao.findByUsername(id);
    }

    @Override
    public Page<Patient> getPatientReady(Integer perPage, Integer page) {
        return patientDao.getPatientReady(perPage,page);
    }
}
