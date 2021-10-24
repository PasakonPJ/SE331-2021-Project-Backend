package se331.lab.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se331.lab.rest.entity.*;
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
    //@Mapping(target = "patients", expression = "java(content.getPatient().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    List<DoctorDTO> getDoctorDTO(List<Doctor> content);

    List<UserApproveDTO> getUserApproveDTO(List<User> content);
    PatientDTO getPatientDTO(Patient content);
}
