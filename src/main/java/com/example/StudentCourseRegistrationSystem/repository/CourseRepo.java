package com.example.StudentCourseRegistrationSystem.repository;

import com.example.StudentCourseRegistrationSystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
    Course findByCourseCode(String courseCode);
}
