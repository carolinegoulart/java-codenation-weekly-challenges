package com.challenge.repository;

import com.challenge.entity.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

    Optional<Company> findById(Long id);

    @Query(value = "select distinct company.* " +
            "from company " +
            "inner join candidate " +
            "on company.id = candidate.company_id " +
            "where candidate.acceleration_id = :accelerationId", nativeQuery = true)
    List<Company> findByAccelerationId(@Param("accelerationId") Long accelerationId);

    @Query(value = "select distinct company.* " +
            "from company " +
            "inner join candidate " +
            "on company.id = candidate.company_id " +
            "where candidate.user_id = :userId", nativeQuery = true)
    List<Company> findByUserId(@Param("userId") Long userId);

}
