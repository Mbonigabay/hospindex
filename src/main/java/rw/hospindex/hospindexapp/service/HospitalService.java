package rw.hospindex.hospindexapp.service;

import java.util.List;

import rw.hospindex.hospindexapp.model.dto.HospitalDto;

public interface HospitalService {
    List<HospitalDto> getAllHospitals(int page, int limit);
    HospitalDto getHospitalById(Long id);
}
