# University Student Portal API Guide

## Overview

The University Student Portal API is a comprehensive REST API designed for managing university student information, courses, grades, and administrative operations. This guide provides detailed information on how to use the API effectively.

## Base URL

\`\`\`
http://localhost:8080/api
\`\`\`

## Authentication

The API uses HTTP Basic Authentication. Include the following credentials in your requests:

- **Username**: `admin`
- **Password**: `admin123`

### Example Authentication Header
\`\`\`
Authorization: Basic YWRtaW46YWRtaW4xMjM=
\`\`\`

## Response Format

All API responses follow a consistent JSON format:

\`\`\`json
{
  "status": "success|error",
  "message": "Descriptive message",
  "data": {}, // Response data (if applicable)
  "timestamp": "2024-01-15T10:30:00",
  "errors": {} // Error details (if applicable)
}
\`\`\`

## Student Management API

### Get All Students
\`\`\`http
GET /api/students
\`\`\`

**Response:**
\`\`\`json
[
  {
    "id": 1,
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
]
\`\`\`

### Get Student by ID
\`\`\`http
GET /api/students/{id}
\`\`\`

### Create New Student
\`\`\`http
POST /api/students
Content-Type: application/json

{
  "studentId": "STU002",
  "firstName": "Jane",
  "lastName": "Smith",
  "email": "jane.smith@student.edu",
  "phoneNumber": "555-5678",
  "major": "Mathematics",
  "year": 2,
  "status": "ACTIVE"
}
\`\`\`

### Update Student
\`\`\`http
PUT /api/students/{id}
Content-Type: application/json

{
  "firstName": "Jane",
  "lastName": "Doe",
  "email": "jane.doe@student.edu",
  "major": "Computer Science",
  "year": 3
}
\`\`\`

### Delete Student
\`\`\`http
DELETE /api/students/{id}
\`\`\`

### Search Students
\`\`\`http
GET /api/students/search?name=John
\`\`\`

### Get Students by Major
\`\`\`http
GET /api/students/major/Computer%20Science
\`\`\`

## Course Management API

### Get All Courses
\`\`\`http
GET /api/courses
\`\`\`

### Create New Course
\`\`\`http
POST /api/courses
Content-Type: application/json

{
  "courseCode": "CS201",
  "courseName": "Data Structures",
  "description": "Advanced data structures and algorithms",
  "creditHours": 4,
  "department": "Computer Science",
  "instructor": "Dr. Mark Thompson",
  "semester": "Fall 2024",
  "maxCapacity": 25
}
\`\`\`

### Enroll Student in Course
\`\`\`http
POST /api/courses/{courseId}/enroll/{studentId}
\`\`\`

### Get Available Courses
\`\`\`http
GET /api/courses/available
\`\`\`

## Grade Management API

### Get All Grades
\`\`\`http
GET /api/grades
\`\`\`

### Get Grades by Student
\`\`\`http
GET /api/grades/student/{studentId}
\`\`\`

### Calculate Student GPA
\`\`\`http
GET /api/grades/student/{studentId}/gpa
\`\`\`

### Create New Grade
\`\`\`http
POST /api/grades
Content-Type: application/json

{
  "student": {"id": 1},
  "course": {"id": 1},
  "gradeValue": 3.7,
  "assignmentType": "Final Exam",
  "comments": "Excellent work"
}
\`\`\`

## Dashboard API

### Get Dashboard Statistics
\`\`\`http
GET /api/dashboard/stats
\`\`\`

**Response:**
\`\`\`json
{
  "totalStudents": 150,
  "activeStudents": 145,
  "totalCourses": 25,
  "availableCourses": 20,
  "totalGrades": 500,
  "departmentCourseCount": {
    "Computer Science": 8,
    "Mathematics": 6,
    "Physics": 5
  }
}
\`\`\`

### Get Student Overview
\`\`\`http
GET /api/dashboard/student/{studentId}
\`\`\`

## Error Handling

The API returns appropriate HTTP status codes and detailed error messages:

### Validation Errors (400)
\`\`\`json
{
  "status": "error",
  "message": "Validation Failed",
  "errors": {
    "firstName": "First name is required",
    "email": "Please provide a valid email address"
  },
  "timestamp": "2024-01-15T10:30:00"
}
\`\`\`

### Not Found (404)
\`\`\`json
{
  "status": "error",
  "message": "Student not found",
  "timestamp": "2024-01-15T10:30:00"
}
\`\`\`

### Conflict (409)
\`\`\`json
{
  "status": "error",
  "message": "Student with ID STU001 already exists",
  "timestamp": "2024-01-15T10:30:00"
}
\`\`\`

## Rate Limiting

Currently, there are no rate limits imposed on API requests. However, please use the API responsibly and avoid excessive requests that could impact system performance.

## Testing the API

### Using cURL

\`\`\`bash
# Get all students
curl -X GET "http://localhost:8080/api/students" \
  -H "Authorization: Basic YWRtaW46YWRtaW4xMjM="

# Create a new student
curl -X POST "http://localhost:8080/api/students" \
  -H "Content-Type: application/json" \
  -H "Authorization: Basic YWRtaW46YWRtaW4xMjM=" \
  -d '{
    "studentId": "STU999",
    "firstName": "Test",
    "lastName": "Student",
    "email": "test@student.edu",
    "major": "Computer Science",
    "status": "ACTIVE"
  }'
\`\`\`

### Using Postman

1. Import the OpenAPI specification from `/api-docs`
2. Set up Basic Authentication with username `admin` and password `admin123`
3. Use the pre-configured requests or create your own

## Interactive Documentation

Visit the Swagger UI for interactive API documentation:
\`\`\`
http://localhost:8080/swagger-ui.html
\`\`\`

## Support

For API support and questions, please contact:
- **Email**: it-support@university.edu
- **Documentation**: http://localhost:8080/api-docs (this page)
- **Interactive Docs**: http://localhost:8080/swagger-ui.html
