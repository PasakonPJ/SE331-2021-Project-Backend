package se331.lab.rest.entity;

import lombok.*;

import javax.persistence.*;
import javax.print.Doc;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String topic;
    String recommend;
    @OneToOne
    Doctor doctor;
    @OneToOne
    Patient patient;
}
