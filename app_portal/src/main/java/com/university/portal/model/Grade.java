package com.university.portal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Grade entity representing a student's grade in a course
 */
@Entity
@Table(name = "grades")
public class Grade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    
    @NotNull(message = "Grade value is required")
    @DecimalMin(value = "0.0", message = "Grade must be at least 0.0")
    @DecimalMax(value = "4.0", message = "Grade must not exceed 4.0")
    private Double gradeValue;
    
    @NotNull(message = "Letter grade is required")
    @Enumerated(EnumType.STRING)
    private LetterGrade letterGrade;
    
    private String assignmentType; // e.g., "Midterm", "Final", "Assignment", "Quiz"
    
    private String comments;
    
    @Column(nullable = false)
    private LocalDateTime dateRecorded = LocalDateTime.now();
    
    // Constructors
    public Grade() {}
    
    public Grade(Student student, Course course, Double gradeValue, LetterGrade letterGrade) {
        this.student = student;
        this.course = course;
        this.gradeValue = gradeValue;
        this.letterGrade = letterGrade;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
    
    public Double getGradeValue() { return gradeValue; }
    public void setGradeValue(Double gradeValue) { this.gradeValue = gradeValue; }
    
    public LetterGrade getLetterGrade() { return letterGrade; }
    public void setLetterGrade(LetterGrade letterGrade) { this.letterGrade = letterGrade; }
    
    public String getAssignmentType() { return assignmentType; }
    public void setAssignmentType(String assignmentType) { this.assignmentType = assignmentType; }
    
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
    
    public LocalDateTime getDateRecorded() { return dateRecorded; }
    public void setDateRecorded(LocalDateTime dateRecorded) { this.dateRecorded = dateRecorded; }
    
    public enum LetterGrade {
        A_PLUS("A+", 4.0),
        A("A", 4.0),
        A_MINUS("A-", 3.7),
        B_PLUS("B+", 3.3),
        B("B", 3.0),
        B_MINUS("B-", 2.7),
        C_PLUS("C+", 2.3),
        C("C", 2.0),
        C_MINUS("C-", 1.7),
        D_PLUS("D+", 1.3),
        D("D", 1.0),
        F("F", 0.0);
        
        private final String display;
        private final Double value;
        
        LetterGrade(String display, Double value) {
            this.display = display;
            this.value = value;
        }
        
        public String getDisplay() { return display; }
        public Double getValue() { return value; }
    }
}
