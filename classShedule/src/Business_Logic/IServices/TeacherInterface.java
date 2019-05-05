/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.IServices;

import Business_Logic.Common.Period;
import java.util.List;

/**
 *
 * @author lawar15
 */
public interface TeacherInterface {
   public List<Period> getbookedPeriods(); 
   public List<CourseInterface> getPossibleCourses();
   public String getName();
   public boolean isTeacherAvailable(String startdate, String finishDate);
   public String getTeachersId();
   public void addbookedPeriods(Period p);
}
