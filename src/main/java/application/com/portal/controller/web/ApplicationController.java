package application.com.portal.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("applications")
public class ApplicationController {
	
	@GetMapping
    public String dashboard(Model model) {
        model.addAttribute("Title", "Applications");
        model.addAttribute("content", "APPLICATION DASHBOARD");
        
        return "layout"; // This will use the base layout
    }

}
