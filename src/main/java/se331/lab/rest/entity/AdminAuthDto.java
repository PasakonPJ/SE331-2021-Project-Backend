package se331.lab.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import se331.lab.rest.security.repository.UserRepository;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminAuthDto {
    Long id;
    String username;
    String firstname;
    String lastname;
    String imageurl;
    List<String> authorities;
}
