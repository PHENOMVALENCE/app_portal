package com.university.portal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Data Transfer Object for Course entity
 */
@Schema(description = "Course information")
public class CourseDTO {
    
    @Schema(description = "Course's unique identifier", example = "1")
    private Long id;
    
    @Schema(description = "Course code", example = "CS101", required = true)
    @NotBlank(message = "Course code is required")
    private String courseCode;
    
    @Schema(description = "Course name", example = "Introduction to Programming", required = true)
    @NotBlank(message = "Course name is required")
    private String courseName;
    
    @Schema(description = "Course description", example = "Basic programming concepts using Java")
    private String description;
    
    @Schema(description = "Credit hours", example = "3", required = true)
    @NotNull(message = "Credit hours is required")
    @Positive(message = "Credit hours must be positive")
    private Integer creditHours;
    
    @Schema(description = "Department", example = "Computer Science", required = true)
    @NotBlank(message = "Department is required")
    private String department;
    
    @Schema(description = "Instructor name", example = "Dr. Sarah Johnson", required = true)
    @NotBlank(message = "Instructor is required")
    private String instructor;
    
    @Schema(description = "Semester", example = "Fall 2024")
    private String semester;
    
    @Schema(description = "Maximum capacity", example = "30")
    private Integer maxCapacity;
    
    @Schema(description = "Current enrollment", example = "25")
    private Integer currentEnrollment;
    
    // Constructors
    public CourseDTO() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Integer getCreditHours() { return creditHours; }
    public void setCreditHours(Integer creditHours) { this.creditHours = creditHours; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    
    public Integer getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(Integer maxCapacity) { this.maxCapacity = maxCapacity; }
    
    public Integer getCurrentEnrollment() { return currentEnrollment; }
    public void setCurrentEnrollment(Integer currentEnrollment) { this.currentEnrollment = currentEnrollment; }
}
