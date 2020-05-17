package com.challenge.entity;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class SubmissionId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

}
