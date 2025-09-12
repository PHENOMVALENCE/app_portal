package application.com.portal.service;

import java.util.Optional;

import application.com.portal.model.User;

public interface UserService {
	
	 public User registerUser(User user, String role);
	 public Optional<User> findByUsername(String username);
	 public Optional<User> findByEmail(String email);
	 

}
