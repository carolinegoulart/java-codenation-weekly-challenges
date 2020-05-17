package com.challenge.repository;

import com.challenge.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(Long userId);

    @Query(value = "select users.* from users " +
            "inner join candidate " +
            "on users.id = candidate.user_id " +
            "inner join acceleration " +
            "on candidate.acceleration_id = acceleration.id " +
            "where acceleration.name like %:name%", nativeQuery = true)
    List<User> findByAccelerationName(@Param("name") String name);

    @Query(value = "select users.* from users " +
            "inner join candidate " +
            "on users.id = candidate.user_id " +
            "where candidate.company_id = :id", nativeQuery = true)
    List<User> findByCompanyId(@Param("id") Long id);

    List<User> findAll();

}
