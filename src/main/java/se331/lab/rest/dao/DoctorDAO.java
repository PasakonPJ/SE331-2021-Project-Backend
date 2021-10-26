package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patient;

import java.util.List;

public interface DoctorDAO {
    Page<Doctor> getDoctors(Integer perPage, Integer page);

    Page<Doctor> getMyPatient(String id, PageRequest pageRequest);

    Page<Doctor> findByid(Long id, PageRequest of);

    List<Doctor> findByUsername(String username);
}
