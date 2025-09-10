package application.com.portal.repository;

import application.com.portal.model.*;

import java.util.Optional;


import org.springframework.stereotype.Repository;

@Repository

public class UserRepository extends JpaRepository<User, Long> {
	
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}