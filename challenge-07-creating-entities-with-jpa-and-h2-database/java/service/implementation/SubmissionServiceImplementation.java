package com.challenge.service.implementation;

import com.challenge.entity.Submission;
import com.challenge.repository.SubmissionRepository;
import com.challenge.service.interfaces.SubmissionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SubmissionServiceImplementation implements SubmissionServiceInterface {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Override
    public Submission save(Submission submission) {
        return this.submissionRepository.save(submission);
    }

    @Override
    public BigDecimal findHigherScoreByChallengeId(Long challengeId) {
        if (this.submissionRepository.findHigherScoreByChallengeId(challengeId) == null) {
            return BigDecimal.ZERO;
        }
        return this.submissionRepository.findHigherScoreByChallengeId(challengeId);
    }

    @Override
    public List<Submission> findByChallengeIdAndAccelerationId(Long challengeId, Long accelerationId) {
        return this.submissionRepository.findByChallengeIdAndAccelerationId(challengeId, accelerationId);
    }

}
