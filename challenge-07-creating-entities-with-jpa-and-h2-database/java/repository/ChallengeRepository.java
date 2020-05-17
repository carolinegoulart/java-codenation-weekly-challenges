package com.challenge.repository;

import com.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends CrudRepository<Challenge, Long> {

    @Query(value = "select challenge.* from acceleration " +
            "inner join candidate " +
            "on acceleration.id = candidate.acceleration_id " +
            "inner join challenge " +
            "on acceleration.challenge_id = challenge.id " +
            "where candidate.acceleration_id = :accelerationId " +
            "and candidate.user_id = :userId", nativeQuery = true)
    List<Challenge> findByAccelerationIdAndUserId(@Param("accelerationId") Long accelerationId,
                                                  @Param("userId") Long userId);

}
