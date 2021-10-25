package se331.lab.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.repository.DoctorRepository;

@Repository
public class DoctorDAOImpl implements DoctorDAO{
    @Autowired
    DoctorRepository doctorRepository;
    @Override
    public Page<Doctor> getDoctors(Integer perPage, Integer page) {
        return doctorRepository.findAll(PageRequest.of(page-1,perPage));
    }

    @Override
    public Page<Doctor> getMyPatient(String id, PageRequest pageRequest) {
        return doctorRepository.findByUsername(id,pageRequest);
    }

    @Override
    public Page<Doctor> findByid(Long id, PageRequest of) {
        return doctorRepository.findById(id,of);
    }
}
