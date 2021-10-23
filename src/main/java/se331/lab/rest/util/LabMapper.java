package se331.lab.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.entity.PatientDTO;
import se331.lab.rest.entity.VaccinePatientDTO;

import java.util.List;

@Mapper
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);
    List<PatientDTO> getPatientDTO(List<Patient> content);
    List<VaccinePatientDTO> getVaccinePatientDTO(List<VaccinePatientDTO> content);
}
