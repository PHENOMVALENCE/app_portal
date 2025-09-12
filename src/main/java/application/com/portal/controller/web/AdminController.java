package application.com.portal.controller.web;

import application.com.portal.model.Application;
import application.com.portal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Application> applications = applicationService.getAllApplications();
        model.addAttribute("applications", applications);
        return "admin/dashboard";
    }

    @PostMapping("/application/{id}/approve")
    public String approve(@PathVariable Long id) {
        applicationService.updateStatus(id, "APPROVED");
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/application/{id}/reject")
    public String reject(@PathVariable Long id) {
        applicationService.updateStatus(id, "REJECTED");
        return "redirect:/admin/dashboard";
    }
}
