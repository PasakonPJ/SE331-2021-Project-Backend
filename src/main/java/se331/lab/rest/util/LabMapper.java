package se331.lab.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.entity.PatientDTO;
import se331.lab.rest.entity.AdminAuthDto;
import se331.lab.rest.entity.VaccinePatientDTO;
import se331.lab.rest.security.entity.Authority;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.entity.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);

    List<PatientDTO> getPatientDTO(List<Patient> content);

    List<VaccinePatientDTO> getVaccinePatientDTO(List<VaccinePatientDTO> content);

    @Mapping(target = "authorities", expression = "java(user.getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    AdminAuthDto getAdminAuthDTO(User user);

    UserDTO getUserDTO(User u);
}
