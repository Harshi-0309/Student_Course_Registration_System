package com.example.StudentCourseRegistrationSystem.repository;

import com.example.StudentCourseRegistrationSystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
}
