package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.security.entity.Authority;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.repository.UserRepository;
import se331.lab.rest.service.PatientService;
import se331.lab.rest.service.UserService;

@RestController
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    PatientService patientService;
    @Autowired
    UserRepository userRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUserStatus(@PathVariable(value = "id") Long id, @RequestBody User user){
        User newUser = userRepository.findByUsername(user.getUsername());
        newUser.getAuthorities().remove(0);
        newUser.getAuthorities().add(user.getAuthorities().get(0));
        final User updatedUser = userService.save(newUser);
//        if(updatedUser.getAuthorities().get(0).getName().name().equals("ROLE_PATIENT")){
//            patientService.save(Patient.builder().username(updatedUser.getUsername())
//                    .password(passwordEncoder.encode(updatedUser.getPassword()))
//                    .email(updatedUser.getEmail())
//                    .doctor(null)
//                    .build()
//            );
//        }
        return ResponseEntity.ok(updatedUser);
    }
}
