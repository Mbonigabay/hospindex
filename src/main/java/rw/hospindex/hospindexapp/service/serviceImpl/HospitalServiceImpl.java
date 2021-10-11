package rw.hospindex.hospindexapp.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rw.hospindex.hospindexapp.mapper.Mapper;
import rw.hospindex.hospindexapp.model.Hospital;
import rw.hospindex.hospindexapp.model.dto.HospitalDto;
import rw.hospindex.hospindexapp.model.request.HospitalRequest;
import rw.hospindex.hospindexapp.model.response.HospitalResponse;
import rw.hospindex.hospindexapp.repository.HospitalRepository;
import rw.hospindex.hospindexapp.service.HospitalService;

@Service
@Slf4j
@AllArgsConstructor
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;
    
    @Autowired
    Mapper<Hospital, HospitalDto, HospitalRequest, HospitalResponse> hospitalMapper;

    @Override
    public List<HospitalDto> getAllHospitals(int page, int limit) {
        List<HospitalDto> hospitalDtos = new ArrayList<>();
        if(page>0) page = page - 1;
        List<Hospital> hospitals = hospitalRepository.findAllHospitals(PageRequest.of(page, limit)).getContent();
        log.info("HospitalServiceImpl.java 32 page {}", hospitals);
        for (Hospital hospital : hospitals) {
            hospitalDtos.add(hospitalMapper.mapEntityToDto(hospital));
        }
        return hospitalDtos;
    }

    @Override
    public HospitalDto getHospitalById(Long id) {
        Optional<Hospital> Hospital = hospitalRepository.findByHospitalId(id);
        return hospitalMapper.mapEntityToDto(Hospital.get());
    }
    
}
