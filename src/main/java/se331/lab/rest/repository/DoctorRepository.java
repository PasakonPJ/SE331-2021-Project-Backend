package se331.lab.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.entity.Doctor;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    Page<Doctor> findById(Long id, Pageable pageRequest);
    List<Doctor> findByComment_Id(Long id);

    Page<Doctor> findByUsername(String id, Pageable pageRequest);
}
