/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.scheldue_result;

import Business_Logic.IServices.CourseInterface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lawar15
 * we only use this for multiple retun ?
 */
public class Scheldue_result implements Serializable {
    private List<CourseInterface> bookedCourses = new ArrayList<CourseInterface>();
    public List<CourseInterface> BookingFails = new ArrayList<CourseInterface>();
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
    
}
