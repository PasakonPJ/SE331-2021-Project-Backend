package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.repository.DoctorRepository;
import se331.lab.rest.security.entity.*;
import se331.lab.rest.security.repository.AuthorityRepository;
import se331.lab.rest.security.repository.UserRepository;
import se331.lab.rest.service.PatientService;
import se331.lab.rest.service.UserService;
import se331.lab.rest.util.LabMapper;

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
                    .password(passwordEncoder.encode(updatedUser.getPassword()))
                    .email(updatedUser.getEmail())
                    .doctor(null)
                    .build()
            );
        }else{
            doctorRepository.save(Doctor.builder().username(updatedUser.getUsername())
                    .password(passwordEncoder.encode(updatedUser.getPassword()))
                    .email(updatedUser.getEmail())
                    .patients(null)
                    .build());
        }
        return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(updatedUser));
    }
}