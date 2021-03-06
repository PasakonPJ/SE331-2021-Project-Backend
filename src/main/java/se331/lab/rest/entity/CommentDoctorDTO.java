package se331.lab.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDoctorDTO {
    Long id;
    String topic;
    String recommend;
    DoctorDTO doctorThatComment;
}
