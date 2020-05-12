package com.quiz.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.quiz.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsUserByUsername(String username);

    Boolean existsUserByEMail(String eMail);

    Boolean existsUserByUsernameAndPassword(String username, String password);

    @Query("SELECT CASE WHEN status = 'PANDING' THEN true ELSE false END from User where username = :username")
    Boolean authenticatedUser(@Param("username") String username);

    Boolean existsUserByEMailAndPassword(String eMail, String password);

    User findUserByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    User findUserById(Long id);

}
