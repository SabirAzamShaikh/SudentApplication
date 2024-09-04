package com.example.SecutiryPractice.Service;

import com.example.SecutiryPractice.Entity.Student;
import com.example.SecutiryPractice.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
    @Autowired
    StudentRepo studentRepo;
    ArrayList<Student> arr = new ArrayList<Student>();
    Iterator<Student> itr=arr.iterator();
    public List<Student> getall()
    {
        return studentRepo.findAll();
    }
public Student save(Student student)
{
    studentRepo.save(student);
      arr.add(student);
return  student;
}

    public void DeleteById(int id)
    {
               studentRepo.deleteById(id);

            }
        } // Return null if not found

