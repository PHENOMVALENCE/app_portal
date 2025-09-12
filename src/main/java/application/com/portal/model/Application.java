package application.com.portal.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.Data;


@Entity
@Data

public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String internshipType; 
    private String status = "PENDING"; 
    private String resumePath; 
    private LocalDateTime appliedAt = LocalDateTime.now();

    
    @ManyToMany
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}