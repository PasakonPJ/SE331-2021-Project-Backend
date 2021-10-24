package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.DoctorDAO;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patient;

@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    DoctorDAO doctorDAO;
    @Override
    public Page<Doctor> getDoctors(Integer perPage, Integer page) {
        return doctorDAO.getDoctors(perPage,page);
    }
}
