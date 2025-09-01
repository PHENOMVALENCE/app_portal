package com.university.portal.controller;

import com.university.portal.model.Grade;
import com.university.portal.model.Student;
import com.university.portal.model.Course;
import com.university.portal.service.GradeService;
import com.university.portal.service.StudentService;
import com.university.portal.service.CourseService;
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
 * REST Controller for Grade management operations
 */
@RestController
@RequestMapping("/api/grades")
@Tag(name = "Grade Management", description = "APIs for managing student grades")
@CrossOrigin(origins = "*")
public class GradeController {
    
    @Autowired
    private GradeService gradeService;
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private CourseService courseService;
    
    @Operation(summary = "Get all grades", description = "Retrieve a list of all grades")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved grades")
    @GetMapping
    public ResponseEntity<List<Grade>> getAllGrades() {
        List<Grade> grades = gradeService.getAllGrades();
        return ResponseEntity.ok(grades);
    }
    
    @Operation(summary = "Get grade by ID", description = "Retrieve a specific grade by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Grade found"),
        @ApiResponse(responseCode = "404", description = "Grade not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGradeById(
            @Parameter(description = "Grade ID") @PathVariable Long id) {
        Optional<Grade> grade = gradeService.getGradeById(id);
        return grade.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Create new grade", description = "Create a new grade record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Grade created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "404", description = "Student or course not found")
    })
    @PostMapping
    public ResponseEntity<?> createGrade(@Valid @RequestBody Grade grade) {
        try {
            // Validate student and course exist
            if (grade.getStudent() != null && grade.getStudent().getId() != null) {
                Optional<Student> student = studentService.getStudentById(grade.getStudent().getId());
                if (student.isEmpty()) {
                    return ResponseEntity.badRequest().body("Student not found");
                }
                grade.setStudent(student.get());
            }
            
            if (grade.getCourse() != null && grade.getCourse().getId() != null) {
                Optional<Course> course = courseService.getCourseById(grade.getCourse().getId());
                if (course.isEmpty()) {
                    return ResponseEntity.badRequest().body("Course not found");
                }
                grade.setCourse(course.get());
            }
            
            // Calculate letter grade based on grade value
            if (grade.getLetterGrade() == null && grade.getGradeValue() != null) {
                grade.setLetterGrade(gradeService.calculateLetterGrade(grade.getGradeValue()));
            }
            
            Grade savedGrade = gradeService.saveGrade(grade);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedGrade);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating grade: " + e.getMessage());
        }
    }
    
    @Operation(summary = "Update grade", description = "Update an existing grade record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Grade updated successfully"),
        @ApiResponse(responseCode = "404", description = "Grade not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateGrade(
            @Parameter(description = "Grade ID") @PathVariable Long id,
            @Valid @RequestBody Grade grade) {
        try {
            Optional<Grade> existingGrade = gradeService.getGradeById(id);
            if (existingGrade.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            grade.setId(id);
            // Recalculate letter grade if grade value changed
            if (grade.getGradeValue() != null) {
                grade.setLetterGrade(gradeService.calculateLetterGrade(grade.getGradeValue()));
            }
            
            Grade updatedGrade = gradeService.saveGrade(grade);
            return ResponseEntity.ok(updatedGrade);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating grade: " + e.getMessage());
        }
    }
    
    @Operation(summary = "Delete grade", description = "Delete a grade record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Grade deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Grade not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(
            @Parameter(description = "Grade ID") @PathVariable Long id) {
        Optional<Grade> grade = gradeService.getGradeById(id);
        if (grade.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Get grades by student", description = "Retrieve all grades for a specific student")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Grade>> getGradesByStudent(
            @Parameter(description = "Student ID") @PathVariable Long studentId) {
        List<Grade> grades = gradeService.getGradesByStudentId(studentId);
        return ResponseEntity.ok(grades);
    }
    
    @Operation(summary = "Get grades by course", description = "Retrieve all grades for a specific course")
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Grade>> getGradesByCourse(
            @Parameter(description = "Course ID") @PathVariable Long courseId) {
        List<Grade> grades = gradeService.getGradesByCourseId(courseId);
        return ResponseEntity.ok(grades);
    }
    
    @Operation(summary = "Calculate student GPA", description = "Calculate GPA for a specific student")
    @GetMapping("/student/{studentId}/gpa")
    public ResponseEntity<Double> calculateStudentGpa(
            @Parameter(description = "Student ID") @PathVariable Long studentId) {
        Double gpa = gradeService.calculateStudentGpa(studentId);
        return ResponseEntity.ok(gpa);
    }
    
    @Operation(summary = "Calculate course average", description = "Calculate average grade for a specific course")
    @GetMapping("/course/{courseId}/average")
    public ResponseEntity<Double> calculateCourseAverage(
            @Parameter(description = "Course ID") @PathVariable Long courseId) {
        Double average = gradeService.calculateCourseAverage(courseId);
        return ResponseEntity.ok(average);
    }
}
