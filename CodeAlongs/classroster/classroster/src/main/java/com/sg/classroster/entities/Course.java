package com.sg.classroster.entities;

import java.util.List;

/**
 *
 * @author jake
 */

// Courses are the focus of the program, hence why they're handling the relationships with other classes

public class Course {

    private int id;
    private String name;
    private String description;
    private Teacher teacher;
    private List<Student> students;
}
