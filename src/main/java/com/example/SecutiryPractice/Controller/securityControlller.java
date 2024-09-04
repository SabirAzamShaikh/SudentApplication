package com.example.SecutiryPractice.Controller;

import com.example.SecutiryPractice.Entity.Student;
import com.example.SecutiryPractice.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class securityControlller
{
@Autowired
    StudentService studentService;

    @GetMapping("/getallstudent")
    public List<Student> getall()
    {
return studentService.getall();
    }
    @PostMapping("/createstudent")
    public Student save(@RequestBody Student Student)
    {
studentService.save(Student);
return Student;
    }
    @DeleteMapping("/{id}")
    public void getbyid(@PathVariable int id)
    {
        studentService.DeleteById(id);

    }
}
