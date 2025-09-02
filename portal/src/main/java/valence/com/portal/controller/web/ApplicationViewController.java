/**
  * @ Author: Valence Mwigani
  * @ Role: Full Stack Dev
  * @ Create Time: 2025-09-01 23:45:31
  * @ Modified by: Valence Mwigani
  * @ Modified time: 2025-09-02 07:58:46
  * @ Description:
  */

package valence.com.portal.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import valence.com.portal.model.Application;
import valence.com.portal.repository.ApplicationRepository;

@Controller
@RequestMapping("/application")
public class ApplicationViewController {
    @GetMapping("/view")
    public String index(Model model, @RequestParam Integer id, ApplicationRepository applicationRepository) {
        Application application = applicationRepository.findById(id).orElse(null);
        model.addAttribute("application", application);
        return "application/view";
    }
    @GetMapping("/list")
    public String list(Model model, ApplicationRepository applicationRepository) {
        model.addAttribute("applications", applicationRepository.findAll());
        return "application/list";
    }
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("application", new Application());
        return "application/create";
    }
    @GetMapping("/edit")
    public String edit(Model model, @RequestParam Integer id, ApplicationRepository applicationRepository) {
        Application application = applicationRepository.findById(id).orElse(null);
        model.addAttribute("application", application);
        return "application/edit";
    }
    @GetMapping("/delete")
    public String delete(Model model, @RequestParam Integer id, ApplicationRepository applicationRepository) {
        applicationRepository.deleteById(id);
        return "redirect:/application/list";
    }
}
