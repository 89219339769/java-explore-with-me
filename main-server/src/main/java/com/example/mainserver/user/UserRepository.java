package com.example.mainserver.user;

import com.example.mainserver.user.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.name = ?1 ")
    User getUserByName(String name);

    List<User> findAllByIdIn(List<Long> ids, Pageable pageable);
}
