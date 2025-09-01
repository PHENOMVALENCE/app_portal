package com.university.portal.controller;

import com.university.portal.model.Student;
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
 * REST Controller for Student management operations
 */
@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Management", description = "APIs for managing student information")
@CrossOrigin(origins = "*")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    @Operation(summary = "Get all students", description = "Retrieve a list of all students")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved students")
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
    
    @Operation(summary = "Get student by ID", description = "Retrieve a specific student by their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student found"),
        @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @Parameter(description = "Student ID") @PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Get student by student ID", description = "Retrieve a student by their student ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student found"),
        @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/student-id/{studentId}")
    public ResponseEntity<Student> getStudentByStudentId(
            @Parameter(description = "Student ID") @PathVariable String studentId) {
        Optional<Student> student = studentService.getStudentByStudentId(studentId);
        return student.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @Operation(summary = "Create new student", description = "Create a new student record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Student created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data"),
        @ApiResponse(responseCode = "409", description = "Student already exists")
    })
    @PostMapping
    public ResponseEntity<?> createStudent(@Valid @RequestBody Student student) {
        try {
            if (studentService.existsByStudentId(student.getStudentId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Student with ID " + student.getStudentId() + " already exists");
            }
            if (studentService.existsByEmail(student.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Student with email " + student.getEmail() + " already exists");
            }
            Student savedStudent = studentService.saveStudent(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating student: " + e.getMessage());
        }
    }
    
    @Operation(summary = "Update student", description = "Update an existing student record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Student updated successfully"),
        @ApiResponse(responseCode = "404", description = "Student not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(
            @Parameter(description = "Student ID") @PathVariable Long id,
            @Valid @RequestBody Student student) {
        try {
            Optional<Student> existingStudent = studentService.getStudentById(id);
            if (existingStudent.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            student.setId(id);
            Student updatedStudent = studentService.saveStudent(student);
            return ResponseEntity.ok(updatedStudent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating student: " + e.getMessage());
        }
    }
    
    @Operation(summary = "Delete student", description = "Delete a student record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Student deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @Parameter(description = "Student ID") @PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Search students by name", description = "Search students by first or last name")
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudents(
            @Parameter(description = "Name to search for") @RequestParam String name) {
        List<Student> students = studentService.searchStudentsByName(name);
        return ResponseEntity.ok(students);
    }
    
    @Operation(summary = "Get students by major", description = "Retrieve students by their major")
    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(
            @Parameter(description = "Major") @PathVariable String major) {
        List<Student> students = studentService.getStudentsByMajor(major);
        return ResponseEntity.ok(students);
    }
    
    @Operation(summary = "Get students by year", description = "Retrieve students by their academic year")
    @GetMapping("/year/{year}")
    public ResponseEntity<List<Student>> getStudentsByYear(
            @Parameter(description = "Academic year") @PathVariable Integer year) {
        List<Student> students = studentService.getStudentsByYear(year);
        return ResponseEntity.ok(students);
    }
    
    @Operation(summary = "Get active student count", description = "Get the count of active students")
    @GetMapping("/count/active")
    public ResponseEntity<Long> getActiveStudentCount() {
        Long count = studentService.getActiveStudentCount();
        return ResponseEntity.ok(count);
    }
}
