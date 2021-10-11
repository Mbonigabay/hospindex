package rw.hospindex.hospindexapp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import rw.hospindex.hospindexapp.mapper.Mapper;
import rw.hospindex.hospindexapp.model.Hospital;
import rw.hospindex.hospindexapp.model.dto.HospitalDto;
import rw.hospindex.hospindexapp.model.request.HospitalRequest;
import rw.hospindex.hospindexapp.model.response.HospitalResponse;
import rw.hospindex.hospindexapp.repository.HospitalRepository;
import rw.hospindex.hospindexapp.service.serviceImpl.HospitalServiceImpl;

@WebMvcTest(HospitalController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class HospitalControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    HospitalRepository hospitalRepository;

    @MockBean
    Mapper<Hospital, HospitalDto, HospitalRequest, HospitalResponse> hospitalMapper;

    @MockBean
    HospitalServiceImpl hospitalService;

    @BeforeEach
    public void setUp(){
        Hospital bwiza = new Hospital();
        bwiza.setId(1000L);
        bwiza.setName("Bwiza");
        Mockito.when(hospitalRepository.findAllHospitals(any(Pageable.class))).thenReturn(new PageImpl<Hospital>(List.of(bwiza)));
        Mockito.when(hospitalRepository.findByHospitalId(1000L)).thenReturn(Optional.of(bwiza));
    }

    @Test
    public void retrieveHospitals() throws Exception {
        HospitalDto bwiza = new HospitalDto();
        bwiza.setName("Bwiza");
        List<HospitalDto> hospitalDtos = Arrays.asList(bwiza);
        Mockito.when(hospitalService.getAllHospitals(0, Integer.MAX_VALUE)).thenReturn(hospitalDtos);

        mvc.perform(get("/api/v1/hospitals").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }


    @Test
    public void retrieveHospitalById() throws Exception {
        HospitalDto bwiza = new HospitalDto();
        bwiza.setName("Bwiza");
        Mockito.when(hospitalService.getHospitalById(1000L)).thenReturn(bwiza);

        mvc.perform(get("/api/v1/hospitals/{id}", 1000L).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

}