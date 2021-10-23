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
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String username;
    String password;
    String email;
    @ManyToOne
    Doctor doctor;
    @ManyToMany
    @Builder.Default
    List<Vaccine> vaccine = new ArrayList<>();

//    @ManyToMany(fetch = FetchType.EAGER)
//    @Builder.Default
//    private List<Authority> authorities = new ArrayList<>();
}