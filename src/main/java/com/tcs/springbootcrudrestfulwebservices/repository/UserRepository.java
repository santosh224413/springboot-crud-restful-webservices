package com.tcs.springbootcrudrestfulwebservices.repository;

import com.tcs.springbootcrudrestfulwebservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
