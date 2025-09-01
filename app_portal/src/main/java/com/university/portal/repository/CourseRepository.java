package com.university.portal.repository;

import com.university.portal.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Course entity operations
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    Optional<Course> findByCourseCode(String courseCode);
    
    List<Course> findByDepartment(String department);
    
    List<Course> findByInstructor(String instructor);
    
    List<Course> findBySemester(String semester);
    
    @Query("SELECT c FROM Course c WHERE c.courseName LIKE %:name%")
    List<Course> findByCourseNameContaining(@Param("name") String name);
    
    @Query("SELECT c FROM Course c WHERE c.currentEnrollment < c.maxCapacity")
    List<Course> findAvailableCourses();
    
    @Query("SELECT c FROM Course c WHERE c.creditHours = :creditHours")
    List<Course> findByCreditHours(@Param("creditHours") Integer creditHours);
    
    @Query("SELECT COUNT(c) FROM Course c WHERE c.department = :department")
    Long countByDepartment(@Param("department") String department);
}
