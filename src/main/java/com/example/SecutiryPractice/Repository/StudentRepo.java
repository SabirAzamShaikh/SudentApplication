package com.example.SecutiryPractice.Repository;

import com.example.SecutiryPractice.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer>
{

}
