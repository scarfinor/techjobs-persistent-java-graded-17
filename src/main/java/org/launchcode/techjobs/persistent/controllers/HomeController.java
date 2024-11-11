package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("title", "MyJobs");
        Iterable<Job> jobs = jobRepository.findAll();
        model.addAttribute("jobs", jobs);
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	model.addAttribute("title", "Add Job");
    Iterable<Employer> employers = employerRepository.findAll();
    List<Skill> skills = (List<Skill>) skillRepository.findAll();
    model.addAttribute("employers", employers);
    model.addAttribute("skills", skills);
    model.addAttribute("job", new Job());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {

        Optional<Employer> employerOpt = employerRepository.findById(employerId);
        if (errors.hasErrors()) {
	    model.addAttribute("title", "Add Job");
        return "add";
        }

            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
            newJob.setSkills(skillObjs);

            jobRepository.save(newJob);

        return "redirect:/";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        Optional optJob = jobRepository.findById(jobId);
        if (optJob.isPresent()) {
            Job job = (Job) optJob.get();
            model.addAttribute("job", job);
            return "view";
    } else {
            return "redirect:../";
        }
    }
}

