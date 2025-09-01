package com.university.portal.controller;

import com.university.portal.model.Student;
import com.university.portal.model.Course;
import com.university.portal.model.Grade;
import com.university.portal.service.StudentService;
import com.university.portal.service.CourseService;
import com.university.portal.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.Optional;

/**
 * Web Controller for serving Thymeleaf templates
 */
@Controller
public class WebController {
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private GradeService gradeService;
    
    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalStudents", studentService.getAllStudents().size());
        model.addAttribute("activeStudents", studentService.getActiveStudentCount());
        model.addAttribute("totalCourses", courseService.getAllCourses().size());
        model.addAttribute("availableCourses", courseService.getAvailableCourses().size());
        model.addAttribute("recentStudents", studentService.getAllStudents().stream().limit(5).toList());
        model.addAttribute("recentCourses", courseService.getAllCourses().stream().limit(5).toList());
        return "dashboard";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    // Student Management
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students/list";
    }
    
    @GetMapping("/students/add")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/add";
    }
    
    @PostMapping("/students/add")
    public String addStudent(@Valid @ModelAttribute Student student, 
                           BindingResult result, 
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "students/add";
        }
        
        if (studentService.existsByStudentId(student.getStudentId())) {
            result.rejectValue("studentId", "error.student", "Student ID already exists");
            return "students/add";
        }
        
        if (studentService.existsByEmail(student.getEmail())) {
            result.rejectValue("email", "error.student", "Email already exists");
            return "students/add";
        }
        
        studentService.saveStudent(student);
        redirectAttributes.addFlashAttribute("successMessage", "Student added successfully!");
        return "redirect:/students";
    }
    
    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isEmpty()) {
            return "redirect:/students";
        }
        model.addAttribute("student", student.get());
        return "students/edit";
    }
    
    @PostMapping("/students/edit/{id}")
    public String editStudent(@PathVariable Long id, 
                            @Valid @ModelAttribute Student student, 
                            BindingResult result, 
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "students/edit";
        }
        
        student.setId(id);
        studentService.saveStudent(student);
        redirectAttributes.addFlashAttribute("successMessage", "Student updated successfully!");
        return "redirect:/students";
    }
    
    @GetMapping("/students/view/{id}")
    public String viewStudent(@PathVariable Long id, Model model) {
        Optional<Student> student = studentService.getStudentById(id);
        if (student.isEmpty()) {
            return "redirect:/students";
        }
        model.addAttribute("student", student.get());
        model.addAttribute("grades", gradeService.getGradesByStudentId(id));
        model.addAttribute("gpa", gradeService.calculateStudentGpa(id));
        return "students/view";
    }
    
    @PostMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        studentService.deleteStudent(id);
        redirectAttributes.addFlashAttribute("successMessage", "Student deleted successfully!");
        return "redirect:/students";
    }
    
    // Course Management
    @GetMapping("/courses")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "courses/list";
    }
    
    @GetMapping("/courses/add")
    public String addCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "courses/add";
    }
    
    @PostMapping("/courses/add")
    public String addCourse(@Valid @ModelAttribute Course course, 
                          BindingResult result, 
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "courses/add";
        }
        
        if (courseService.existsByCourseCode(course.getCourseCode())) {
            result.rejectValue("courseCode", "error.course", "Course code already exists");
            return "courses/add";
        }
        
        courseService.saveCourse(course);
        redirectAttributes.addFlashAttribute("successMessage", "Course added successfully!");
        return "redirect:/courses";
    }
    
    @GetMapping("/courses/view/{id}")
    public String viewCourse(@PathVariable Long id, Model model) {
        Optional<Course> course = courseService.getCourseById(id);
        if (course.isEmpty()) {
            return "redirect:/courses";
        }
        model.addAttribute("course", course.get());
        model.addAttribute("grades", gradeService.getGradesByCourseId(id));
        model.addAttribute("average", gradeService.calculateCourseAverage(id));
        return "courses/view";
    }
    
    // Grade Management
    @GetMapping("/grades")
    public String listGrades(Model model) {
        model.addAttribute("grades", gradeService.getAllGrades());
        return "grades/list";
    }
    
    @GetMapping("/grades/add")
    public String addGradeForm(Model model) {
        model.addAttribute("grade", new Grade());
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("courses", courseService.getAllCourses());
        return "grades/add";
    }
    
    @PostMapping("/grades/add")
    public String addGrade(@Valid @ModelAttribute Grade grade, 
                         BindingResult result, 
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("courses", courseService.getAllCourses());
            return "grades/add";
        }
        
        // Calculate letter grade
        if (grade.getGradeValue() != null) {
            grade.setLetterGrade(gradeService.calculateLetterGrade(grade.getGradeValue()));
        }
        
        gradeService.saveGrade(grade);
        redirectAttributes.addFlashAttribute("successMessage", "Grade added successfully!");
        return "redirect:/grades";
    }
}
