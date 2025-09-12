package application.com.portal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.com.portal.model.User;
import application.com.portal.repository.StudentRepository;
import valence.com.studentmanagement.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

	  @Autowired
			private  StudentRepository studentRepository;
	  
	@Override
	public Student saveStudent(Student student) {
		try {
			return studentRepository.save(student);
			
		} catch (Exception e) {
			throw new RuntimeException("Error saving student: " + e.getMessage());
		}
	}

	@Override
	public Optional<Student> getByUser(User user) {
		try {
			return studentRepository.findByUser(user);
			
		} catch (Exception e) {
			throw new RuntimeException("Cant Find User: " + e.getMessage());
		}
	}

}
