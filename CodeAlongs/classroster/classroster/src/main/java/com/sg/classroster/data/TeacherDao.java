package com.sg.classroster.data;

import com.sg.classroster.entities.Teacher;
import java.util.List;

/**
 *
 * @author jake
 */
public interface TeacherDao {

    Teacher getTeacherById(int id);
    List<Teacher> getAllTeachers();
    Teacher addTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacherById(int id);
}
