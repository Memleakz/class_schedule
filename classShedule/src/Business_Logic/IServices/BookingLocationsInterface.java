/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.IServices;

import Business_Logic.Common.Period;
import Business_Logic.Common.Schedule;

/**
 *
 * @author lawar15
 */
public interface BookingLocationsInterface extends Comparable{
    public Period getPeriodOfBooking();
    public int getId();
    public boolean IsAvailable(Period p);
    public CourseInterface getCourse();
    public void setCourse(CourseInterface bcourse);
    public TeacherInterface getTeacher();
    public Schedule getScheduleOfBooking();
    
}
