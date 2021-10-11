package rw.hospindex.hospindexapp.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rw.hospindex.hospindexapp.model.Hospital;

@Repository
public interface HospitalRepository extends PagingAndSortingRepository<Hospital, Long> {
    @Query(value = "SELECT * FROM hospitals",  countQuery = "SELECT count(*) FROM hospitals", nativeQuery = true)
    Page<Hospital> findAllHospitals(Pageable pageable);
    @Query(value = "SELECT  * FROM hospitals where id = :id", nativeQuery = true)
    Optional<Hospital> findByHospitalId(@Param("id") Long id);
}
