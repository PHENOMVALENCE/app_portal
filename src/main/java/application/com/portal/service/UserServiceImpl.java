package application.com.portal.service;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import application.com.portal.model.User;
import application.com.portal.repository.UserRepository;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository  userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User registerUser(User user, String role) {
		try {
		user.setPasssword(passwordEncoder.encode(user.getPassword()));
		user.setRole(role);
		return userRepository.save(user);
		} catch (Exception e) { 
			throw new RuntimeException("Error Registering user: " + e.getMessage());
		}
		
	}

	@Override
	public Optional<User> findByUsername(String username) {
           try {
           return userRepository.findByUsername(username);
           } catch (Exception e) {
        	   throw new RuntimeException("Error finding user by username: " + e.getMessage());
           }
		
	}

	@Override
	public Optional<User> findByEmail(String email) {
	    try {
	           return userRepository.findByEmail(email);
	           } catch (Exception e) {
	        	   throw new RuntimeException("Error finding user by email: " + e.getMessage());
	           }
}
}
