package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se331.lab.rest.security.entity.Authority;
import se331.lab.rest.security.entity.AuthorityName;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.repository.AuthorityRepository;
import se331.lab.rest.security.repository.UserRepository;
import se331.lab.rest.util.LabMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RegisterController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    List<Authority> userAuth = new ArrayList<>();
    Authority authority = new Authority();
    @PostMapping("/signup")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        User isRegis = userRepository.findByUsername(user.getUsername());
        if(isRegis==null){
            authority.setName(AuthorityName.ROLE_USER);
            userAuth.add(authority);
//            user.getImageUrl().add(user.getImageUrl().get(0));
            String password = encoder.encode(user.getPassword());
            user.setPassword(password);
            user.setEnabled(true);
            user.setFirstname(user.getFirstname());
            user.setLastname(user.getLastname());
            user.getAuthorities().add(authorityRepository.findById(4L).get());
            user.setApprove(false);
            User u = userRepository.save(user);
            return ResponseEntity.ok(LabMapper.INSTANCE.getUserDTO(u));
        }else{
            return ResponseEntity.ok(false);
        }

    }
}
