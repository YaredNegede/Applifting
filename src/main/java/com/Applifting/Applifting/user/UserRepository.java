package com.Applifting.Applifting.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository  extends JpaRepository<User,Long> {
    Optional<User> findByAccessToken(String accessToken);

}
