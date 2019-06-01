/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.Common;

import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.TeacherInterface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lawar15
 * I only use this for multiple return
 */
public class Schedule_result implements Serializable {
    private List<CourseInterface> bookedCourses = new ArrayList<CourseInterface>();
    public List<CourseInterface> BookingFails = new ArrayList<CourseInterface>();
    private String result_period_start = "";
    private String result_period_end = "";
    
    public String getResultPeriodStart()
    {
	return this.result_period_start;
    }
     public String getResultPeriodEnd()
    {
	return this.result_period_end;
    }
     public void setResultPeriodStart(String start)
    {
	this.result_period_start = start;
    }
     public void setResultPeriodEnd(String end)
    {
	this.result_period_end = end;
    }
    public void setBookedCourses(List<CourseInterface> bookedCourses){
	this.bookedCourses = bookedCourses;
    }
    public void setBookingFails( List<CourseInterface> failed){
	this.BookingFails = failed;
    }
    public List<CourseInterface> getBookedCourses (){
	return this.bookedCourses;
    }
    public CourseInterface getBookedCourse (String CourseName){
	for(CourseInterface c : bookedCourses)
	{
	    if(c.getNameOfTheCourse().equals(CourseName))
	    {
		return c;
	    }
	}
	return null;
    }
    public void replaceBookedCourse (String CourseName,CourseInterface course){
	for(CourseInterface c : this.bookedCourses)
	{
	    if(c.getNameOfTheCourse().equals(CourseName))
	    {
		this.bookedCourses.remove(c);
		this.bookedCourses.add(course);
	    }
	}
	return;
    }
    public List<CourseInterface> getbookedCoursesForTeacher(TeacherInterface teacher){
	List<CourseInterface> bookedCoursesforteacherall = new ArrayList<CourseInterface>() ;
	for(CourseInterface c : this.bookedCourses){
	    TeacherInterface teacherToCompare =c.getAssignedLecturer();
	    if(teacherToCompare.getTeachersId().compareTo(teacher.getTeachersId())==0){
		bookedCoursesforteacherall.add(c);
	    }
	}
	return bookedCoursesforteacherall;
    }
    
}
