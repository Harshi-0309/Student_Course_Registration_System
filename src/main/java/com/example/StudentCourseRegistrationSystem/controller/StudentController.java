package com.example.StudentCourseRegistrationSystem.controller;

import com.example.StudentCourseRegistrationSystem.entity.Course;
import com.example.StudentCourseRegistrationSystem.entity.Enrollment;
import com.example.StudentCourseRegistrationSystem.entity.Student;
import com.example.StudentCourseRegistrationSystem.exception.StudentException;
import com.example.StudentCourseRegistrationSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        if (student.getName() == null || student.getName().isEmpty()) {
            throw new StudentException("Student name is required");
        }
        return studentService.createStudent(student);
    }

    @GetMapping("/getStudents")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public Student getStudByEnrolledCourses(@PathVariable("id") Long id) {
        Student student = studentService.getStudByEnrolledCourses(id);
        if (student == null) {
            throw new StudentException("Student not found with ID: " + id);  // Exception if not found
        }
        return student;
    }

    @PutMapping("/update/{id}")
    public Student updateStudentMajor(@PathVariable("id") Long id, @RequestBody String newMajor) {
        Student updatedStudent = studentService.updateStudentByMajor(id, newMajor);
        if (updatedStudent == null) {
            throw new StudentException("Student not found with ID: " + id);  // Exception if not found
        }
        return updatedStudent;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteByStudentId(@PathVariable("id") Long id) {
        boolean found = studentService.deleteByStudentId(id);
        if (!found) {
            throw new StudentException("Student not found with ID: " + id);  // Exception if not found
        }
        return "Student with ID " + id + " has been deleted.";
    }

    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course) {
        return studentService.createCourse(course);
    }

    @GetMapping("/allcourses")
    public List<Course> getAllCourses() {
        return studentService.getAllCourses();
    }

    @PostMapping("/courses/enroll")
    public String enrollStudentInCourse(@RequestParam Long studentId, @RequestParam Long courseId) {
        return studentService.enrollStudentInCourse(studentId, courseId);
    }

}
