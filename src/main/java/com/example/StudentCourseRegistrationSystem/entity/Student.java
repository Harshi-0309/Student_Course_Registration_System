package com.example.StudentCourseRegistrationSystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String major;

    @OneToMany(mappedBy = "student")
    @JsonManagedReference
    private List<Enrollment> enrollments;

    public Student(String name, String email, String major) {
        this.name = name;
        this.email = email;
        this. major = major;
    }

    public Student(){
    }


}
