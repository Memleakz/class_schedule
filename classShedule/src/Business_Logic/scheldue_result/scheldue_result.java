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
 * @author Laura
 * we only use this for multiple retun ?
 */
public class scheldue_result implements Serializable {
    public List<CourseInterface> bookedCourses = new ArrayList<CourseInterface>();
    public List<CourseInterface> BookingFails = new ArrayList<CourseInterface>();
   
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
