package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patient;

public interface DoctorService {

    Page<Doctor> getDoctors(Integer perPage, Integer page);

    Page<Doctor> getMyPatient(Long id, PageRequest pageRequest);
}
