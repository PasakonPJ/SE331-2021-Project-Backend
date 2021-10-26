package se331.lab.rest.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String imageurl;
    String firstname;
    String lastname;
    String username;
    String password;
    String email;
    @OneToMany(mappedBy = "patientThatComment")
            @Builder.Default
    List<Comment> commentedPatient = new ArrayList<>();
    @ManyToOne
    Doctor doctor;
    @OneToMany(mappedBy = "patientGotVaccine")
            @Builder.Default
    List<Vaccine> vaccine = new ArrayList<>();

//    @ManyToMany(fetch = FetchType.EAGER)
//    @Builder.Default
//    private List<Authority> authorities = new ArrayList<>();
}
