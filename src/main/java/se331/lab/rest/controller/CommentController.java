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
import se331.lab.rest.repository.PatientRepository;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.service.PatientService;
import se331.lab.rest.util.LabMapper;

import java.util.List;
import java.util.Objects;

@CrossOrigin(maxAge = 3600)
@Controller
public class CommentController {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PatientService patientService;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @PostMapping("/comment/{id}")
    public ResponseEntity<?> saveComment(@PathVariable("id") Long id, @RequestBody CommentDTO commentDTO){
        Patient output = patientService.findById(id);
        Doctor doctor = doctorRepository.findById(commentDTO.getId()).get();
        if (!Objects.equals(output.getDoctor().getId(), doctor.getId())){
            return ResponseEntity.ok(false);
        }else{
            Comment comment = Comment.builder().topic(commentDTO.getTopic()).recommend(commentDTO.getRecommend()).doctorThatComment(doctor).patientThatComment(output).build();
            output.getCommentedPatient().add(comment);
            doctor.getCommentedDoctor().add(comment);
            commentRepository.save(comment);
            return ResponseEntity.ok(LabMapper.INSTANCE.getCommentDTO(comment));
        }
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<?> listComment(@PathVariable("id") Long id,@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page){
        perPage = perPage == null ? 6 : perPage;
        page = page == null ? 1 : page;
        Page<Comment> pageOutput = commentRepository.findByPatientThatComment_Id(id, PageRequest.of(page-1,perPage));
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getCommentDoctorDTO(pageOutput.getContent()), responseHeader, HttpStatus.OK);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id){
       Patient patient= patientRepository.findByCommentedPatient_Id(id).get(0);
       int index = Integer.parseInt(String.valueOf(id-1));
       patient.getCommentedPatient().set(index,null);
        Doctor doctor = doctorRepository.findByCommentedDoctor_Id(id).get(0);
        doctor.getCommentedDoctor().set(index,null);
        commentRepository.deleteById(id);
        return ResponseEntity.ok("delete successfully");
    }
    @PutMapping("/comment/edit")
    public ResponseEntity<?>  editComment(@RequestBody CommentDTO commentDTO){
        Comment comment = commentRepository.findById(commentDTO.getId()).get();
        comment.setTopic(commentDTO.getTopic());
        comment.setRecommend(commentDTO.getRecommend());
        commentRepository.save(comment);
        return ResponseEntity.ok(LabMapper.INSTANCE.getCommentDTO(comment));
    }

    @GetMapping("/comment/only/{id}")
    public ResponseEntity<?> getOnlyThatComment(@PathVariable("id") Long id){
        Comment comments =  commentRepository.findById(id).get();
        return ResponseEntity.ok(LabMapper.INSTANCE.getCommentDTO(comments));
    }
}
