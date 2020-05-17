package com.challenge.repository;

import com.challenge.entity.Acceleration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccelerationRepository extends CrudRepository<Acceleration, Long> {

    Optional<Acceleration> findById(Long id);

    List<Acceleration> findByName(String name);

    @Query(value = "select acceleration.* " +
            "from acceleration " +
            "inner join candidate " +
            "on acceleration.id = candidate.acceleration_id " +
            "where candidate.company_id = :companyId", nativeQuery = true)
    List<Acceleration> findByCompanyId(@Param("companyId") Long companyId);

    List<Acceleration> findAll();

}