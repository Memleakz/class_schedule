/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.Courses;

import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.StudentsInterface;
import Business_Logic.IServices.TeacherInterface;
import java.util.List;

/**
 *
 * @author lawar15
 */
public class CourseFactory {
       public static CourseInterface getCourse(String id, String nameOfTheCourse,List<StudentsInterface> students, int numberOflessons, int numberOfHourstogether, int maxOnWeek, TeacherInterface teacher,
	       int desiredDaysBetweenLectures)
  {
      CourseInterface c = new Course(id,nameOfTheCourse,students,numberOflessons,numberOfHourstogether,maxOnWeek, teacher);
      c.setDesiredDaysBetweenLectures(desiredDaysBetweenLectures);
      return c;
  } 
}
