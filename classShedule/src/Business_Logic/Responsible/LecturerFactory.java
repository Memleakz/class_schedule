/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.Responsible;

import Business_Logic.Common.Period;
import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.TeacherInterface;
import java.util.List;

/**
 *
 * @author lawar15
 */
public class LecturerFactory {
       public static TeacherInterface getTeacher(String id, String name, List<Period> bookedPeriods, List<CourseInterface> possibleCourses)
  {
      
      return new Lecturer(id,name,bookedPeriods,possibleCourses);
  } 
}
