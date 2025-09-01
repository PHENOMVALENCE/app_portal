package com.university.portal.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Enhanced OpenAPI configuration for comprehensive API documentation
 */
@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI universityPortalOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("University Student Portal API")
                        .description("""
                            # University Student Portal REST API
                            
                            A comprehensive REST API for managing university student portal operations including:
                            
                            ## Features
                            - **Student Management**: Complete CRUD operations for student records
                            - **Course Management**: Course creation, enrollment, and management
                            - **Grade Management**: Grade recording and GPA calculations
                            - **Dashboard Analytics**: Statistical data and reporting
                            
                            ## Authentication
                            This API uses HTTP Basic Authentication. Use the following credentials for testing:
                            - **Username**: admin
                            - **Password**: admin123
                            
                            ## Response Format
                            All API responses follow a consistent format with status, message, data, and timestamp fields.
                            
                            ## Error Handling
                            The API provides detailed error messages with appropriate HTTP status codes:
                            - `400` - Bad Request (validation errors)
                            - `401` - Unauthorized (authentication required)
                            - `404` - Not Found (resource doesn't exist)
                            - `409` - Conflict (duplicate resource)
                            - `500` - Internal Server Error
                            
                            ## Rate Limiting
                            API requests are currently unlimited but may be subject to rate limiting in production.
                            """)
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("University IT Department")
                                .email("it-support@university.edu")
                                .url("https://university.edu/it"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Development Server"),
                        new Server()
                                .url("https://university-portal.edu")
                                .description("Production Server")
                ))
                .components(new Components()
                        .addSecuritySchemes("basicAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic")
                                .description("HTTP Basic Authentication"))
                        .addExamples("StudentExample", new Example()
                                .summary("Sample Student")
                                .description("Example of a student record")
                                .value("""
                                    {
                                        "studentId": "STU001",
                                        "firstName": "John",
                                        "lastName": "Doe",
                                        "email": "john.doe@student.edu",
                                        "phoneNumber": "555-1234",
                                        "major": "Computer Science",
                                        "year": 3,
                                        "gpa": 3.75,
                                        "status": "ACTIVE"
                                    }
                                    """))
                        .addExamples("CourseExample", new Example()
                                .summary("Sample Course")
                                .description("Example of a course record")
                                .value("""
                                    {
                                        "courseCode": "CS101",
                                        "courseName": "Introduction to Programming",
                                        "description": "Basic programming concepts using Java",
                                        "creditHours": 3,
                                        "department": "Computer Science",
                                        "instructor": "Dr. Sarah Johnson",
                                        "semester": "Fall 2024",
                                        "maxCapacity": 30,
                                        "currentEnrollment": 25
                                    }
                                    """))
                        .addExamples("GradeExample", new Example()
                                .summary("Sample Grade")
                                .description("Example of a grade record")
                                .value("""
                                    {
                                        "student": {"id": 1},
                                        "course": {"id": 1},
                                        "gradeValue": 3.7,
                                        "letterGrade": "A_MINUS",
                                        "assignmentType": "Final Exam",
                                        "comments": "Excellent work on the final project"
                                    }
                                    """)))
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"));
    }
}
