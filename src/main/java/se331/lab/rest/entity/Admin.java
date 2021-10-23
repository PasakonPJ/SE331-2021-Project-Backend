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
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String username;
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @Builder.Default
    private List<Authority> authorities = new ArrayList<>();
}
