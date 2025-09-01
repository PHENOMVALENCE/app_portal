package com.university.portal.service;

import com.university.portal.model.Student;
import com.university.portal.repository.StudentRepository;
import com.university.portal.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Student entity business logic
 */
@Service
@Transactional
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private GradeRepository gradeRepository;
    
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }
    
    public Optional<Student> getStudentByStudentId(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }
    
    public Optional<Student> getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }
    
    public Student saveStudent(Student student) {
        // Update GPA before saving
        updateStudentGpa(student);
        return studentRepository.save(student);
    }
    
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    
    public List<Student> getStudentsByMajor(String major) {
        return studentRepository.findByMajor(major);
    }
    
    public List<Student> getStudentsByYear(Integer year) {
        return studentRepository.findByYear(year);
    }
    
    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByNameContaining(name);
    }
    
    public List<Student> getStudentsByMinGpa(Double minGpa) {
        return studentRepository.findByGpaGreaterThanEqual(minGpa);
    }
    
    public Long getActiveStudentCount() {
        return studentRepository.countByStatus(Student.StudentStatus.ACTIVE);
    }
    
    private void updateStudentGpa(Student student) {
        Double gpa = gradeRepository.calculateGpaByStudentId(student.getId());
        if (gpa != null) {
            student.setGpa(Math.round(gpa * 100.0) / 100.0); // Round to 2 decimal places
        }
    }
    
    public boolean existsByStudentId(String studentId) {
        return studentRepository.findByStudentId(studentId).isPresent();
    }
    
    public boolean existsByEmail(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }
}
