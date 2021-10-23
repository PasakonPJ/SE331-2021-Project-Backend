package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.PatientDao;
import se331.lab.rest.entity.Patient;

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    PatientDao patientDao;
    @Override
    public Page<Patient> getPatients(Integer perPage, Integer page) {
        return patientDao.getPatient(perPage,page);
    }
}
