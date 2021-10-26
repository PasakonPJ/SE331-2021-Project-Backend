package se331.lab.rest.entity;

import lombok.*;

import javax.persistence.*;
import javax.print.Doc;
import java.util.List;

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
    @ManyToOne
    Doctor doctorThatComment;
    @ManyToOne
    Patient patientThatComment;
}
