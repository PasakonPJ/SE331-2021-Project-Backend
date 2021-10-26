package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.repository.DoctorRepository;
import se331.lab.rest.security.entity.*;
import se331.lab.rest.security.repository.AuthorityRepository;
import se331.lab.rest.security.repository.UserRepository;
import se331.lab.rest.service.PatientService;
import se331.lab.rest.service.UserService;
import se331.lab.rest.util.LabMapper;
@CrossOrigin(maxAge = 3600)
@RestController
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    PatientService patientService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    DoctorRepository doctorRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @PutMapping("/user/{id}")
    public ResponseEntity<UserDTO> updateUserStatus(@PathVariable(value = "id") Long id, @RequestBody AuthUserDTO user){
        User newUser = userRepository.findById(id).get();
        newUser.setApprove(true);
        newUser.getAuthorities().remove(0);
        if(user.getRole().get(0).equals("ROLE_PATIENT")){
            Authority a= authorityRepository.findByName(AuthorityName.ROLE_PATIENT);
            newUser.getAuthorities().add(a);
        }else{
            Authority a= authorityRepository.findByName(AuthorityName.ROLE_DOCTOR);
            newUser.getAuthorities().add(a);
        }
        final User updatedUser = userService.save(newUser);
        if(updatedUser.getAuthorities().get(0).getName().name().equals("ROLE_PATIENT")){
            patientService.save(Patient.builder().username(updatedUser.getUsername())
                            .firstname(updatedUser.getFirstname())
                            .lastname(updatedUser.getLastname())
                    .imageurl(updatedUser.getImageurl())
                    .password(passwordEncoder.encode(updatedUser.getPassword()))
                    .email(updatedUser.getEmail())
                    .doctor(null)
                    .build()
            );
        }else{
            doctorRepository.save(Doctor.builder().username(updatedUser.getUsername())
                    .password(passwordEncoder.encode(updatedUser.getPassword()))
                            .imageurl(updatedUser.getImageurl())
                    .firstname(updatedUser.getFirstname())
                    .lastname(updatedUser.getLastname())
                    .email(updatedUser.getEmail())
                    .patients(null)
                    .build());
        }
        return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(updatedUser));
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAwaitingApprovalUsers(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page){
        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;
        Page<User> pageOutput = userService.getWaitUsers(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getUserApproveDTO(pageOutput.getContent()), responseHeader, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getIndividualUser(@PathVariable(value = "id") Long id) {
        User output = userService.findById(id).get();
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
}
