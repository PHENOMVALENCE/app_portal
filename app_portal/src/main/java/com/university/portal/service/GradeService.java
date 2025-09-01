package com.university.portal.service;

import com.university.portal.model.Grade;
import com.university.portal.model.Student;
import com.university.portal.model.Course;
import com.university.portal.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Grade entity business logic
 */
@Service
@Transactional
public class GradeService {
    
    @Autowired
    private GradeRepository gradeRepository;
    
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }
    
    public Optional<Grade> getGradeById(Long id) {
        return gradeRepository.findById(id);
    }
    
    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }
    
    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }
    
    public List<Grade> getGradesByStudent(Student student) {
        return gradeRepository.findByStudent(student);
    }
    
    public List<Grade> getGradesByCourse(Course course) {
        return gradeRepository.findByCourse(course);
    }
    
    public List<Grade> getGradesByStudentId(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }
    
    public List<Grade> getGradesByCourseId(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }
    
    public Optional<Grade> getGradeByStudentAndCourse(Student student, Course course) {
        return gradeRepository.findByStudentAndCourse(student, course);
    }
    
    public Double calculateStudentGpa(Long studentId) {
        Double gpa = gradeRepository.calculateGpaByStudentId(studentId);
        return gpa != null ? Math.round(gpa * 100.0) / 100.0 : 0.0;
    }
    
    public Double calculateCourseAverage(Long courseId) {
        Double average = gradeRepository.calculateAverageGradeByCourseId(courseId);
        return average != null ? Math.round(average * 100.0) / 100.0 : 0.0;
    }
    
    public Grade.LetterGrade calculateLetterGrade(Double gradeValue) {
        if (gradeValue >= 3.85) return Grade.LetterGrade.A_PLUS;
        if (gradeValue >= 3.7) return Grade.LetterGrade.A;
        if (gradeValue >= 3.5) return Grade.LetterGrade.A_MINUS;
        if (gradeValue >= 3.3) return Grade.LetterGrade.B_PLUS;
        if (gradeValue >= 2.85) return Grade.LetterGrade.B;
        if (gradeValue >= 2.7) return Grade.LetterGrade.B_MINUS;
        if (gradeValue >= 2.3) return Grade.LetterGrade.C_PLUS;
        if (gradeValue >= 1.85) return Grade.LetterGrade.C;
        if (gradeValue >= 1.7) return Grade.LetterGrade.C_MINUS;
        if (gradeValue >= 1.3) return Grade.LetterGrade.D_PLUS;
        if (gradeValue >= 1.0) return Grade.LetterGrade.D;
        return Grade.LetterGrade.F;
    }
}
