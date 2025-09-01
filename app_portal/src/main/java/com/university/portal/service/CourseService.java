package com.university.portal.service;

import com.university.portal.model.Course;
import com.university.portal.model.Student;
import com.university.portal.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Course entity business logic
 */
@Service
@Transactional
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;
    
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }
    
    public Optional<Course> getCourseByCourseCode(String courseCode) {
        return courseRepository.findByCourseCode(courseCode);
    }
    
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }
    
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
    
    public List<Course> getCoursesByDepartment(String department) {
        return courseRepository.findByDepartment(department);
    }
    
    public List<Course> getCoursesByInstructor(String instructor) {
        return courseRepository.findByInstructor(instructor);
    }
    
    public List<Course> getCoursesBySemester(String semester) {
        return courseRepository.findBySemester(semester);
    }
    
    public List<Course> searchCoursesByName(String name) {
        return courseRepository.findByCourseNameContaining(name);
    }
    
    public List<Course> getAvailableCourses() {
        return courseRepository.findAvailableCourses();
    }
    
    public boolean enrollStudent(Long courseId, Student student) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            if (course.hasAvailableSpots()) {
                course.addStudent(student);
                courseRepository.save(course);
                return true;
            }
        }
        return false;
    }
    
    public boolean unenrollStudent(Long courseId, Student student) {
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            course.removeStudent(student);
            courseRepository.save(course);
            return true;
        }
        return false;
    }
    
    public boolean existsByCourseCode(String courseCode) {
        return courseRepository.findByCourseCode(courseCode).isPresent();
    }
    
    public Long getCourseCountByDepartment(String department) {
        return courseRepository.countByDepartment(department);
    }
}
