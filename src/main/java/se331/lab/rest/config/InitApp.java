package se331.lab.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.lab.rest.entity.Admin;
import se331.lab.rest.entity.Doctor;
import se331.lab.rest.entity.Patient;
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.repository.AdminRepository;
import se331.lab.rest.repository.DoctorRepository;
import se331.lab.rest.repository.PatientRepository;
import se331.lab.rest.repository.VaccineRepository;
import se331.lab.rest.security.entity.Authority;
import se331.lab.rest.security.entity.AuthorityName;
import se331.lab.rest.security.repository.AuthorityRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    VaccineRepository vaccineRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
//        Authority authPatient = Authority.builder().name(AuthorityName.ROLE_PATIENT).build();
        Authority authDoctor = Authority.builder().name(AuthorityName.ROLE_DOCTOR).build();
        Authority authAdmin = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();
        authorityRepository.save(authAdmin);
        authorityRepository.save(authDoctor);
//        authorityRepository.save(authPatient);
       Admin admin=adminRepository.save(Admin.builder().username("admin").password(passwordEncoder.encode("123456")).build());
        Doctor doctor1,doctor2;
        doctor1 = doctorRepository.save( Doctor.builder().firstname("passakon").lastname("paingjai").email("kong@test.com").username("kong")
                .password(passwordEncoder.encode("123456"))
                .build());
        doctor2 = doctorRepository.save(Doctor.builder().firstname("thitisan").lastname("chailuek").email("doctor@test.com").username("doctor2")
                .password(passwordEncoder.encode("doctor")).build());
        Patient patient1,patient2,patient3;

        patient1 = Patient.builder().username("kp").email("kp@kp.com")
                .password(passwordEncoder.encode("123456")).build() ;
        patient2 = Patient.builder().username("fax").email("fsx@fax.com")
                .password(passwordEncoder.encode("123456")).build() ;
        patient3 = Patient.builder().username("kong").email("kong@kong.com")
                .password(passwordEncoder.encode("123456")).build() ;
        admin.getAuthorities().add(authAdmin);
        doctor1.getAuthorities().add(authDoctor);
        doctor2.getAuthorities().add(authDoctor);
//        patient1.getAuthorities().add(authPatient);
//        patient2.getAuthorities().add(authPatient);
//        patient3.getAuthorities().add(authPatient);
        doctor1.getPatients().add(patient1);
        doctor1.getPatients().add(patient2);
        doctor2.getPatients().add(patient3);
        patient1.setDoctor(doctor1);
        patient2.setDoctor(doctor1);
        patient3.setDoctor(doctor2);
        Vaccine vaccine1,vaccine2,vaccine3;
        vaccine1 =vaccineRepository.save(Vaccine.builder().vaccineName("Shinovac")
                .vaccinatedDate("23/10/2564").build());
        vaccine2 = vaccineRepository.save(Vaccine.builder().vaccineName("Moderna")
                .vaccinatedDate("23/10/2564").build());
        vaccine3 = vaccineRepository.save(Vaccine.builder().vaccineName("Shinovac")
                .vaccinatedDate("23/10/2564").build());
    patient1.getVaccine().add(vaccine1);
    patient2.getVaccine().add(vaccine2);
        patient3.getVaccine().add(vaccine3);
        patientRepository.save(patient1);
        patientRepository.save(patient2);
        patientRepository.save(patient3);
//        vaccine1.getPatientsVaccine().add(patient1);
//        vaccine2.getPatientsVaccine().add(patient2);
//        vaccine3.getPatientsVaccine().add(patient3);
    }
}
