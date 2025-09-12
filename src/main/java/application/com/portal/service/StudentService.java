package application.com.portal.service;

import java.util.Optional;

import application.com.portal.model.User;
import valence.com.studentmanagement.model.Student;

public interface StudentService {
	
	public Student saveStudent(Student student);
	 public Optional<Student> getByUser(User user);

}
