package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.DoctorVaccineDTO;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.repository.DoctorRepository;
import se331.lab.rest.repository.VaccineRepository;
import se331.lab.rest.service.PatientService;
import se331.lab.rest.util.LabMapper;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class PatientController {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientService patientService;
    @Autowired
    VaccineRepository vaccineRepository;

    @GetMapping("/patients")
    public ResponseEntity<?> getPatientList(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page){
        perPage = perPage == null ? 6 : perPage;
        page = page == null ? 1 : page;
        Page<Patient>  pageOutput = patientService.getPatients(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getPatientDTO(pageOutput.getContent()), responseHeader, HttpStatus.OK);

    }

    @GetMapping("/patients/doctor")
    public ResponseEntity<?> getPatientDoctorList(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page){
        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;
        Page<Patient>  pageOutput = patientService.getPatientsDoctor(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getPatientDTO(pageOutput.getContent()), responseHeader, HttpStatus.OK);

    }
    @GetMapping("/patients/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable("id") Long id){
        Patient output = patientService.findById(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    @PostMapping("/patients/vaccine/{id}")
    public ResponseEntity<?> addVaccineToPatient(@PathVariable("id") Long id,@RequestBody DoctorVaccineDTO vaccine){
        Patient output = patientService.findById(id);
        Doctor doctor;
        Vaccine v = Vaccine.builder().vaccineName(vaccine.getVaccine()).patientGotVaccine(output).vaccinatedDate(java.time.LocalDate.now().toString()).build();
        if(vaccine.getId()!=null){
             doctor = doctorRepository.findById(vaccine.getId()).get();
            output.setDoctor(doctor);
        }
        output.getVaccine().add(v);
        vaccineRepository.save(v);
        return ResponseEntity.ok(LabMapper.INSTANCE.getPatientDTO(output));
    }

}
