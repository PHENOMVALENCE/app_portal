package application.com.portal.controller.web;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import application.com.portal.model.Application;
import application.com.portal.model.Student;
import application.com.portal.model.User;
import application.com.portal.service.ApplicationService;
import application.com.portal.service.StudentService;
import valence.com.studentmanagement.service.user.UserService;

@Controller
@RequestMapping("students")
public class StudentWebController {
	
@Autowired
private ApplicationService applicationService;

@Autowired
private StudentService studentService;

@Autowired
private UserService userService;

	@GetMapping
	public String home(Model model, Principal principal ) {
		 User user = userService.findByUsername(principal.getName())
	                .orElseThrow(() -> new RuntimeException("User not found"));
	        Student student = studentService.getByUser(user)
	                .orElseThrow(() -> new RuntimeException("Student not found"));

	        List<Application> applications = applicationService.getApplicationsByStudent(student);
	        model.addAttribute("applications", applications);
	        return "student/dashboard";
}

    @PostMapping("/apply")
    public String apply(@ModelAttribute Application application, Principal principal) {
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Student student = studentService.getByUser(user)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        applicationService.apply(application, student);
        return "redirect:/student/dashboard";
    }
}
