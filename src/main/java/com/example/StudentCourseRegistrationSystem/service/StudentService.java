package com.example.StudentCourseRegistrationSystem.service;

import com.example.StudentCourseRegistrationSystem.entity.Course;
import com.example.StudentCourseRegistrationSystem.entity.Enrollment;
import com.example.StudentCourseRegistrationSystem.entity.Student;
import com.example.StudentCourseRegistrationSystem.exception.StudentException;
import com.example.StudentCourseRegistrationSystem.repository.CourseRepo;
import com.example.StudentCourseRegistrationSystem.repository.EnrollmentRepo;
import com.example.StudentCourseRegistrationSystem.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudByEnrolledCourses(Long id) {
        return studentRepo.findById(id).orElseThrow(() -> new StudentException("Student not found with ID: " + id));
    }

    public Student updateStudentByMajor(Long id, String newMajor) {
        Student student = studentRepo.findById(id).orElseThrow(() -> new StudentException("Student not found with ID: " + id));
        student.setMajor(newMajor);
        return studentRepo.save(student);
    }

    public boolean deleteByStudentId(Long id) {
        Student student = studentRepo.findById(id).orElseThrow(() -> new StudentException("Student not found with ID: " + id));
        studentRepo.delete(student);
        return true;
    }

    public Course createCourse(Course course) {
        return courseRepo.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public String enrollStudentInCourse(Long studentId, Long courseId) {
        Student student = studentRepo.findById(studentId).orElseThrow(() -> new StudentException("Student not found with ID: " + studentId));
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new StudentException("Course not found with ID: " + courseId));

        if (enrollmentRepo.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new StudentException("Student is already enrolled in this course");
        }

        enrollmentRepo.save(new Enrollment(student, course));
        return "Student " + student.getName() + " successfully enrolled in " + course.getTitle() + " course!";
    }

}

