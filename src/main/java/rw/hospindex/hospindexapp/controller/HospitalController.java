package rw.hospindex.hospindexapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rw.hospindex.hospindexapp.mapper.Mapper;
import rw.hospindex.hospindexapp.model.Hospital;
import rw.hospindex.hospindexapp.model.dto.HospitalDto;
import rw.hospindex.hospindexapp.model.request.HospitalRequest;
import rw.hospindex.hospindexapp.model.response.ApiResponse;
import rw.hospindex.hospindexapp.model.response.HospitalResponse;
import rw.hospindex.hospindexapp.service.HospitalService;

@RestController
@RequestMapping("api/v1/hospitals")
public class HospitalController {
    @Autowired
    HospitalService hospitalService;

    @Autowired
    Mapper<Hospital, HospitalDto, HospitalRequest, HospitalResponse> hospitalMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<List<HospitalResponse>>> index(@RequestParam(value="page", defaultValue="1") int page,@RequestParam(value="limit", defaultValue="25") int limit){
        List<HospitalDto> hospitalDtos = hospitalService.getAllHospitals(page, limit);
        List<HospitalResponse> hospitalResponses = new ArrayList<>();
        for (HospitalDto hospitalDto : hospitalDtos) {
            hospitalResponses.add(hospitalMapper.mapDtoToResponse(hospitalDto));
        }

        final String message = "Hospitals retrieved successfully..";
        ApiResponse<List<HospitalResponse>> body = new ApiResponse<>(HttpStatus.CREATED, message,
        hospitalResponses);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<HospitalResponse>> show(@PathVariable("id") Long id){
        HospitalDto HospitalDto = hospitalService.getHospitalById(id);
        HospitalResponse HospitalResponse = hospitalMapper.mapDtoToResponse(HospitalDto);

        final String message = "Hospital retrieved successfully..";
        ApiResponse<HospitalResponse> body = new ApiResponse<>(HttpStatus.OK, message,
        HospitalResponse);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
}
