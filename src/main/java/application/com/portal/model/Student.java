package application.com.portal.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "students")

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String course;
    private String email;
    private String university;
    private Long phone;

  
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

   
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Application> applications;
}