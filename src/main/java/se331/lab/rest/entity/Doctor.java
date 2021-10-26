package se331.lab.rest.entity;

import lombok.*;
import se331.lab.rest.security.entity.Authority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String firstname;
    String lastname;
    String imageurl;
    String email;
    String password;
    String username;
    @OneToMany(mappedBy = "doctor")
    @Builder.Default
    List<Patient> patients = new ArrayList<>();
    @OneToMany(mappedBy = "doctorThatComment")
            @Builder.Default
    List<Comment> commentedDoctor = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @Builder.Default
    private List<Authority> authorities = new ArrayList<>();

}
