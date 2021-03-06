package se331.lab.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.entity.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    List<Patient> findAll();
    Page<Patient> findByDoctorIsNullAndVaccineIsNull(Pageable pageable);
    List<Patient> findByCommentedPatient_Id(Long id);
    Page<Patient> findByDoctorIsNotNullAndVaccineIsNotNull(Pageable pageable);
    List<Patient> findByUsername(String id);
}
