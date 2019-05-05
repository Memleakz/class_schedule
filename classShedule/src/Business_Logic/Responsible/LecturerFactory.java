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
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author lawar15
 */
public class LecturerFactory {
       static Map<String,TeacherInterface> teachers_cache = new TreeMap<String,TeacherInterface>();
       public static TeacherInterface getTeacher(String id, String name, List<Period> bookedPeriods, List<CourseInterface> possibleCourses)
  {
      //we only want to create 1 teacher object pr. teacher.
      //could singleton be relevant ? 
      TeacherInterface new_t = teachers_cache.get(id);
      if(new_t == null)
      {
	 new_t = new Lecturer(id,name,bookedPeriods,possibleCourses);
	 teachers_cache.put(new_t.getTeachersId(), new_t);
      }
      return new_t;
  } 
}
