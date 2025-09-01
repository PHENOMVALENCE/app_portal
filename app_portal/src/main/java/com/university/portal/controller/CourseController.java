package com.university.portal.controller;

import com.university.portal.model.Course;
import com.university.portal.model.Student;
import com.university.portal.service.CourseService;
import com.university.portal.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for Course management operations
 */
@RestController
@RequestMapping("/api/courses")
@Tag(name = "Course Management", description = "APIs for managing course information")
@CrossOrigin(origins = "*")
public class CourseController {
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private StudentService studentService;
    
    @Operation(summary = "Get all courses", description = "Retrieve a list of all courses")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved courses")
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    
    @Operation(summary = "Get course by ID", description = "Retrieve a specific course by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Course found"),
        @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(
            @Parameter(description = "Course ID") @PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Get course by course code", description = "Retrieve a course by its course code")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Course found"),
        @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @GetMapping("/code/{courseCode}")
    public ResponseEntity<Course> getCourseByCourseCode(
            @Parameter(description = "Course code") @PathVariable String courseCode) {
        Optional<Course> course = courseService.getCourseByCourseCode(courseCode);
        return course.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Create new course", description = "Create a new course")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Course created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "409", description = "Course already exists")
    })
    @PostMapping
    public ResponseEntity<?> createCourse(@Valid @RequestBody Course course) {
        try {
            if (courseService.existsByCourseCode(course.getCourseCode())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Course with code " + course.getCourseCode() + " already exists");
            }
            Course savedCourse = courseService.saveCourse(course);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating course: " + e.getMessage());
        }
    }
    
    @Operation(summary = "Update course", description = "Update an existing course")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Course updated successfully"),
        @ApiResponse(responseCode = "404", description = "Course not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(
            @Parameter(description = "Course ID") @PathVariable Long id,
            @Valid @RequestBody Course course) {
        try {
            Optional<Course> existingCourse = courseService.getCourseById(id);
            if (existingCourse.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            course.setId(id);
            Course updatedCourse = courseService.saveCourse(course);
            return ResponseEntity.ok(updatedCourse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating course: " + e.getMessage());
        }
    }
    
    @Operation(summary = "Delete course", description = "Delete a course")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Course deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(
            @Parameter(description = "Course ID") @PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Get courses by department", description = "Retrieve courses by department")
    @GetMapping("/department/{department}")
    public ResponseEntity<List<Course>> getCoursesByDepartment(
            @Parameter(description = "Department name") @PathVariable String department) {
        List<Course> courses = courseService.getCoursesByDepartment(department);
        return ResponseEntity.ok(courses);
    }
    
    @Operation(summary = "Get available courses", description = "Retrieve courses with available spots")
    @GetMapping("/available")
    public ResponseEntity<List<Course>> getAvailableCourses() {
        List<Course> courses = courseService.getAvailableCourses();
        return ResponseEntity.ok(courses);
    }
    
    @Operation(summary = "Enroll student in course", description = "Enroll a student in a specific course")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student enrolled successfully"),
        @ApiResponse(responseCode = "404", description = "Course or student not found"),
        @ApiResponse(responseCode = "400", description = "Course is full or student already enrolled")
    })
    @PostMapping("/{courseId}/enroll/{studentId}")
    public ResponseEntity<String> enrollStudent(
            @Parameter(description = "Course ID") @PathVariable Long courseId,
            @Parameter(description = "Student ID") @PathVariable Long studentId) {
        Optional<Student> student = studentService.getStudentById(studentId);
        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        boolean enrolled = courseService.enrollStudent(courseId, student.get());
        if (enrolled) {
            return ResponseEntity.ok("Student enrolled successfully");
        } else {
            return ResponseEntity.badRequest().body("Unable to enroll student - course may be full");
        }
    }
    
    @Operation(summary = "Unenroll student from course", description = "Remove a student from a specific course")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student unenrolled successfully"),
        @ApiResponse(responseCode = "404", description = "Course or student not found")
    })
    @DeleteMapping("/{courseId}/unenroll/{studentId}")
    public ResponseEntity<String> unenrollStudent(
            @Parameter(description = "Course ID") @PathVariable Long courseId,
            @Parameter(description = "Student ID") @PathVariable Long studentId) {
        Optional<Student> student = studentService.getStudentById(studentId);
        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        boolean unenrolled = courseService.unenrollStudent(courseId, student.get());
        if (unenrolled) {
            return ResponseEntity.ok("Student unenrolled successfully");
        } else {
            return ResponseEntity.badRequest().body("Unable to unenroll student");
        }
    }
}
