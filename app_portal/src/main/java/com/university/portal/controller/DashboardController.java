package com.university.portal.controller;

import com.university.portal.service.StudentService;
import com.university.portal.service.CourseService;
import com.university.portal.service.GradeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller for Dashboard statistics and overview data
 */
@RestController
@RequestMapping("/api/dashboard")
@Tag(name = "Dashboard", description = "APIs for dashboard statistics and overview")
@CrossOrigin(origins = "*")
public class DashboardController {
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private GradeService gradeService;
    
    @Operation(summary = "Get dashboard statistics", description = "Retrieve overall system statistics for dashboard")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved dashboard statistics")
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Student statistics
        stats.put("totalStudents", studentService.getAllStudents().size());
        stats.put("activeStudents", studentService.getActiveStudentCount());
        
        // Course statistics
        stats.put("totalCourses", courseService.getAllCourses().size());
        stats.put("availableCourses", courseService.getAvailableCourses().size());
        
        // Grade statistics
        stats.put("totalGrades", gradeService.getAllGrades().size());
        
        // Department statistics
        Map<String, Long> departmentStats = new HashMap<>();
        departmentStats.put("Computer Science", courseService.getCourseCountByDepartment("Computer Science"));
        departmentStats.put("Mathematics", courseService.getCourseCountByDepartment("Mathematics"));
        departmentStats.put("Physics", courseService.getCourseCountByDepartment("Physics"));
        departmentStats.put("Chemistry", courseService.getCourseCountByDepartment("Chemistry"));
        departmentStats.put("English Literature", courseService.getCourseCountByDepartment("English Literature"));
        stats.put("departmentCourseCount", departmentStats);
        
        return ResponseEntity.ok(stats);
    }
    
    @Operation(summary = "Get student overview", description = "Get overview data for a specific student")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Map<String, Object>> getStudentOverview(
            @PathVariable Long studentId) {
        Map<String, Object> overview = new HashMap<>();
        
        // Student basic info
        var student = studentService.getStudentById(studentId);
        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        overview.put("student", student.get());
        overview.put("enrolledCourses", student.get().getCourses().size());
        overview.put("grades", gradeService.getGradesByStudentId(studentId));
        overview.put("gpa", gradeService.calculateStudentGpa(studentId));
        
        return ResponseEntity.ok(overview);
    }
}
