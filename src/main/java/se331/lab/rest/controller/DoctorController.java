package se331.lab.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.IdDTO;
import se331.lab.rest.repository.DoctorRepository;
import se331.lab.rest.service.DoctorService;
import se331.lab.rest.util.LabMapper;

@Controller
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping("/doctors")
    public ResponseEntity<?> getDoctorList(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page){
        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;
        Page<Doctor> pageOutput = doctorService.getDoctors(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getDoctorDTO(pageOutput.getContent()), responseHeader, HttpStatus.OK);

    }

    @PostMapping("/doctors/mypatient")
    public ResponseEntity<?> getMyPatient(@RequestBody IdDTO doctorDTO, @RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page){
        perPage = perPage == null ? 6 : perPage;
        page = page == null ? 1 : page;
        Page<Doctor> pageOutput = doctorService.getMyPatient(doctorDTO.getId(), PageRequest.of(page-1,perPage));
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getDoctorDTO(pageOutput.getContent()), responseHeader, HttpStatus.OK);
    }
}
