package rw.hospindex.hospindexapp.mapper.mapperImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import rw.hospindex.hospindexapp.mapper.Mapper;
import rw.hospindex.hospindexapp.model.Hospital;
import rw.hospindex.hospindexapp.model.dto.HospitalDto;
import rw.hospindex.hospindexapp.model.request.HospitalRequest;
import rw.hospindex.hospindexapp.model.response.HospitalResponse;

@Service
@Qualifier("hospitalMapperImpl")
public class HospitalMapperImpl implements Mapper<Hospital, HospitalDto, HospitalRequest, HospitalResponse> {

    @Override
    public HospitalResponse mapDtoToResponse(HospitalDto d) {
        HospitalResponse HospitalResponse = new HospitalResponse();
        BeanUtils.copyProperties(d, HospitalResponse);
        return HospitalResponse;
    }

    @Override
    public HospitalDto mapRequestToDto(HospitalRequest q) {
        HospitalDto HospitalDto = new HospitalDto();
        BeanUtils.copyProperties(q, HospitalDto);
        return HospitalDto;
    }

    @Override
    public Hospital mapDtotoEntity(HospitalDto d) {
        Hospital Hospital = new Hospital();
        BeanUtils.copyProperties(d, Hospital);
        return Hospital;
    }

    @Override
    public HospitalDto mapEntityToDto(Hospital e) {
        HospitalDto HospitalDto = new HospitalDto();
        BeanUtils.copyProperties(e, HospitalDto);
        return HospitalDto;
    }
}
