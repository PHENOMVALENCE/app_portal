package valence.com.portal.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import valence.com.portal.model.Student;


@Controller
@RequestMapping("/student")
public class StudentViewController {
    @GetMapping("/view")
    public String index(Model model, @RequestParam Integer id, StudentRepository studentRepository) {
        Student student = studentRepository.findById(id).orElse(null);
        model.addAttribute("student", student);
        model.addAttribute("application", student.getApplication());
        return "student/view";
    }
    
    
}
