package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patient;

public interface DoctorDAO {
    Page<Doctor> getDoctors(Integer perPage, Integer page);
}
