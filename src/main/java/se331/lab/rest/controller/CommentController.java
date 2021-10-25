package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import se331.lab.rest.entity.*;
import se331.lab.rest.repository.CommentRepository;
import se331.lab.rest.repository.DoctorRepository;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.service.PatientService;
import se331.lab.rest.util.LabMapper;

import java.util.Objects;

@CrossOrigin(maxAge = 3600)
@Controller
public class CommentController {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PatientService patientService;
    @Autowired
    DoctorRepository doctorRepository;

    @PostMapping("/comment/{id}")
    public ResponseEntity<?> saveComment(@PathVariable("id") Long id, @RequestBody CommentDTO commentDTO){
        Patient output = patientService.findById(id);
        Doctor doctor = doctorRepository.findById(commentDTO.getId()).get();
        if (!Objects.equals(output.getDoctor().getId(), doctor.getId())){
            return ResponseEntity.ok(false);
        }else{
            Comment comment = Comment.builder().topic(commentDTO.getTopic()).recommend(commentDTO.getRecommend()).doctor(doctor).patient(output).build();
            output.setComment(comment);
            doctor.setComment(comment);
            commentRepository.save(comment);
            return ResponseEntity.ok(LabMapper.INSTANCE.getCommentDTO(comment));
        }
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<?> listComment(@PathVariable("id") Long id,@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page){
        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;
        Page<Comment> pageOutput = commentRepository.findByPatient_Id(id, PageRequest.of(page-1,perPage));
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getCommentDoctorDTO(pageOutput.getContent()), responseHeader, HttpStatus.OK);
    }
}
