package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String username);

    List<User> findAllByWeightGreaterThan(Double weight);



//TODO need to write query  get all users born between 1990 and 2000, sorted by last name

//    @Query(" SELECT c FROM User c WHERE dateOfBirth BETWEEN ‘1990’ AND ‘2000’")
//    @Query("SELECT c FROM User c WHERE c.dateOfBirth  >= :date")
    List<User> findAllByDateOfBirthAfter();

//    @Query("SELECT u FROM User u WHERE u.dateOfBirth BETWEEN :startDate AND :endDate AND order by u.lastname")
//    List<User> getUsers(@Param(‘’username’’) String username, @Param(’dateOfBirth’) Date dateOfBirth);
}
