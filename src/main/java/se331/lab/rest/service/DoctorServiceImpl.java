package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.DoctorDAO;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patient;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService{
    @Autowired
    DoctorDAO doctorDAO;
    @Override
    public Page<Doctor> getDoctors(Integer perPage, Integer page) {
        return doctorDAO.getDoctors(perPage,page);
    }

    @Override
    public Page<Doctor> getMyPatient(String id, PageRequest pageRequest) {
        return doctorDAO.getMyPatient(id,pageRequest);
    }

    @Override
    public Page<Doctor> getMyPatientById(Long id, PageRequest of) {
        return doctorDAO.findByid(id,of);
    }

    @Override
    public List<Doctor> findByUsername(String username) {
        return doctorDAO.findByUsername(username);
    }
}
