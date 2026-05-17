package com.example.demo;

import java.io.IOException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;
    private final FileUploadService fileUploadService;
    private final EmailService emailService;

    public StudentController(StudentRepository studentRepository,
                             FileUploadService fileUploadService,
                             EmailService emailService) {
        this.studentRepository = studentRepository;
        this.fileUploadService = fileUploadService;
        this.emailService      = emailService;
    }

    @GetMapping("/students")
    public String listStudents(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "name") String sortBy,
            Model model) {

        Pageable pageable = PageRequest.of(page, 5, Sort.by(sortBy));
        Page<Student> studentPage = studentRepository
                .findByNameContainingIgnoreCase(search, pageable);

        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("totalPages", studentPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("search", search);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("totalStudents", studentRepository.count());
        model.addAttribute("newStudent", new Student());
        return "students";
    }

    @PostMapping("/students/add")
    public String addStudent(@Valid @ModelAttribute("newStudent") Student newStudent,
                             BindingResult result,
                             @RequestParam("photoFile") MultipartFile photoFile,
                             Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("students", studentRepository.findAll());
            model.addAttribute("totalStudents", studentRepository.count());
            return "students";
        }

        // Save photo if uploaded
        if (!photoFile.isEmpty()) {
            String filename = fileUploadService.saveFile(photoFile);
            newStudent.setPhoto(filename);
        }

        // Save student
        studentRepository.save(newStudent);

        // Send welcome email
        try {
            emailService.sendWelcomeEmail(newStudent.getEmail(), newStudent.getName());
        } catch (Exception e) {
            System.out.println("Email sending failed: " + e.getMessage());
        }

        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Student student = studentRepository.findById(id).orElse(null);
        model.addAttribute("student", student);
        return "edit";
    }

    @PostMapping("/students/edit/{id}")
    public String editStudent(@PathVariable int id,
                              @Valid @ModelAttribute("student") Student updated,
                              BindingResult result,
                              @RequestParam("photoFile") MultipartFile photoFile)
                              throws IOException {
        if (result.hasErrors()) {
            return "edit";
        }

        if (!photoFile.isEmpty()) {
            String filename = fileUploadService.saveFile(photoFile);
            updated.setPhoto(filename);
        } else {
            Student existing = studentRepository.findById(id).orElse(null);
            if (existing != null) {
                updated.setPhoto(existing.getPhoto());
            }
        }

        updated.setId(id);
        studentRepository.save(updated);
        return "redirect:/students";
    }
}