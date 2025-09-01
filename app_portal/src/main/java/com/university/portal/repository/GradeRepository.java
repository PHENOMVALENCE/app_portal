package com.university.portal.repository;

import com.university.portal.model.Grade;
import com.university.portal.model.Student;
import com.university.portal.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Grade entity operations
 */
@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    
    List<Grade> findByStudent(Student student);
    
    List<Grade> findByCourse(Course course);
    
    Optional<Grade> findByStudentAndCourse(Student student, Course course);
    
    List<Grade> findByStudentId(Long studentId);
    
    List<Grade> findByCourseId(Long courseId);
    
    @Query("SELECT g FROM Grade g WHERE g.student.id = :studentId AND g.course.id = :courseId")
    List<Grade> findByStudentIdAndCourseId(@Param("studentId") Long studentId, @Param("courseId") Long courseId);
    
    @Query("SELECT AVG(g.gradeValue) FROM Grade g WHERE g.student.id = :studentId")
    Double calculateGpaByStudentId(@Param("studentId") Long studentId);
    
    @Query("SELECT AVG(g.gradeValue) FROM Grade g WHERE g.course.id = :courseId")
    Double calculateAverageGradeByCourseId(@Param("courseId") Long courseId);
}
