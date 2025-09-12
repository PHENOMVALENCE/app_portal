package application.com.portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import application.com.portal.model.Student;
import application.com.portal.model.User;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	  Optional<Student> findByUser(User user);

}
