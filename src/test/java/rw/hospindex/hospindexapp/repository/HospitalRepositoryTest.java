package rw.hospindex.hospindexapp.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;

import rw.hospindex.hospindexapp.model.Hospital;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class HospitalRepositoryTest {
    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void setUp() {
        Hospital bwiza = new Hospital();
        bwiza.setName("Bwiza");
        testEntityManager.persistAndFlush(bwiza);
    }

    @Test
    @DisplayName("Test should pass when a list of hospitals is retrieved")
    void shouldRetrieveAllHospitals() {
        List<Hospital> hospitals = hospitalRepository.findAllHospitals(PageRequest.of(0, 25)).getContent();
        long count = hospitals.stream().count();
        Assertions.assertFalse(hospitals.isEmpty());
        Assertions.assertEquals("Bwiza", hospitals.stream().skip(count - 1).findFirst().get().getName());
    }


    @Test
    @DisplayName("Test should pass when a hospital is retrieved by id")
    void shouldRetrieveHospitalById() {
        List<Hospital> hospitals = hospitalRepository.findAllHospitals(PageRequest.of(0, 25)).getContent();
        Optional<Hospital> hospital = hospitalRepository.findByHospitalId(hospitals.stream().findFirst().get().getId());
        Assertions.assertFalse(hospital.isEmpty());
    }
}
