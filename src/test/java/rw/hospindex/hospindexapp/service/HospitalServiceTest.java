package rw.hospindex.hospindexapp.service;

import static org.mockito.ArgumentMatchers.anyLong;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.mock.mockito.MockBean;

import rw.hospindex.hospindexapp.mapper.Mapper;
import rw.hospindex.hospindexapp.model.Hospital;
import rw.hospindex.hospindexapp.model.dto.HospitalDto;
import rw.hospindex.hospindexapp.model.request.HospitalRequest;
import rw.hospindex.hospindexapp.model.response.HospitalResponse;
import rw.hospindex.hospindexapp.repository.HospitalRepository;
import rw.hospindex.hospindexapp.service.serviceImpl.HospitalServiceImpl;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class HospitalServiceTest {

    @MockBean
    HospitalRepository hospitalRepository;


    @MockBean
    Mapper<Hospital, HospitalDto, HospitalRequest, HospitalResponse> hospitalMapper;

    @MockBean
    HospitalServiceImpl hospitalService;

    @BeforeEach
    public void setUp() {
        Long id = 1001L;
        Hospital Faysal = new Hospital();
        Faysal.setId(id);
        Faysal.setName("Faysal");
        Mockito.when(hospitalRepository.findById(1001L)).thenReturn(Optional.of(Faysal));
    }

    // @Test
    // @DisplayName("Test should pass when a list of hospitals is retrieved")
    // void shouldRetrieveHospitals() {
    // }

    @Test
    @DisplayName("Test should pass when a hospital is retrieved")
    void shouldRetrieveHospitalById() {
        
        HospitalDto found = hospitalService.getHospitalById(1001L);
        Assertions.assertEquals(found.getId(), 1001L);
    }
}
