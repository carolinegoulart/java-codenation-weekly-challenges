package com.challenge.repository;

import com.challenge.entity.Submission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SubmissionRepository extends CrudRepository<Submission, Long> {

    @Query(value = "select max(score) from submission " +
            "where submission.challenge_id = :challengeId", nativeQuery = true)
    BigDecimal findHigherScoreByChallengeId(@Param("challengeId") Long challengeId);

    @Query(value = "select submission.* from submission " +
            "inner join challenge " +
            "on submission.challenge_id = challenge.id " +
            "inner join acceleration " +
            "on challenge.id = acceleration.challenge_id " +
            "where submission.challenge_id = :challengeId " +
            "and acceleration.id = :accelerationId", nativeQuery = true)
    List<Submission> findByChallengeIdAndAccelerationId(@Param("challengeId") Long challengeId,
                                                        @Param("accelerationId") Long accelerationId);

}
