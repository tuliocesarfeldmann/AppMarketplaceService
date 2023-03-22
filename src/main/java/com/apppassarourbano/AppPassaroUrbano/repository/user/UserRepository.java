package com.apppassarourbano.AppPassaroUrbano.repository.user;

import com.apppassarourbano.AppPassaroUrbano.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
