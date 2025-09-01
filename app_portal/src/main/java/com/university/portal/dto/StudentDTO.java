package com.university.portal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Data Transfer Object for Student entity
 */
@Schema(description = "Student information")
public class StudentDTO {
    
    @Schema(description = "Student's unique identifier", example = "1")
    private Long id;
    
    @Schema(description = "Student ID number", example = "STU001", required = true)
    @NotBlank(message = "Student ID is required")
    private String studentId;
    
    @Schema(description = "Student's first name", example = "John", required = true)
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50)
    private String firstName;
    
    @Schema(description = "Student's last name", example = "Doe", required = true)
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50)
    private String lastName;
    
    @Schema(description = "Student's email address", example = "john.doe@student.edu", required = true)
    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email is required")
    private String email;
    
    @Schema(description = "Student's phone number", example = "555-1234")
    private String phoneNumber;
    
    @Schema(description = "Student's date of birth", example = "2002-03-15")
    private LocalDate dateOfBirth;
    
    @Schema(description = "Student's major", example = "Computer Science", required = true)
    @NotBlank(message = "Major is required")
    private String major;
    
    @Schema(description = "Student's academic year", example = "3")
    private Integer year;
    
    @Schema(description = "Student's GPA", example = "3.75")
    private Double gpa;
    
    @Schema(description = "Student's enrollment status", example = "ACTIVE")
    private String status;
    
    // Constructors
    public StudentDTO() {}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    
    public Double getGpa() { return gpa; }
    public void setGpa(Double gpa) { this.gpa = gpa; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
