package com.challenge.service.interfaces;

import com.challenge.entity.Acceleration;

import java.util.List;
import java.util.Optional;

public interface AccelerationServiceInterface extends ServiceInterface<Acceleration> {

    Optional<Acceleration> findById(Long id);

    List<Acceleration> findByName(String nome);

    List<Acceleration> findByCompanyId(Long id);

}
