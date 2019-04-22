/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.Bookings;


import Business_Logic.Common.Period;
import Business_Logic.IServices.BookingLocationsInterface;
import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.StudentsInterface;
import Business_Logic.IServices.TeacherInterface;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author lawar15
 */
public class BookingOfTheRoom implements BookingLocationsInterface,Serializable{
    String id;
    TeacherInterface lecturer;
    List<StudentsInterface> students;
    Period periodOfBooking;
    CourseInterface course;
    public BookingOfTheRoom(CourseInterface course,TeacherInterface lecturer, List<StudentsInterface> students, Period periodOfBooking){
	this.lecturer = lecturer;
	this.students = students;
	this.periodOfBooking = periodOfBooking;
	this.course = course;
    }
    @Override
    public Period getPeriodOfBooking(){
	return this.periodOfBooking;
    }
    @Override
    public String getId(){
	return this.id;
    }
    @Override
    public boolean IsAvailable(Period p)
    {
	int start_compare = p.getStartDate().compareTo(this.periodOfBooking.getStartDate());
	int end_compare = p.getEndDate().compareTo(this.periodOfBooking.getStartDate());
	int start_compare_end = p.getStartDate().compareTo(this.periodOfBooking.getEndDate());
	
	if(start_compare <= 0 && end_compare <= 0)
	{
	    return true;
	}
	if(start_compare_end >= 0)
	{
	    return true;
	}
	
	return false;
    }

    @Override
    public int compareTo(Object o) {
	BookingLocationsInterface bli = (BookingLocationsInterface) o;
	
	if(this.periodOfBooking.getStartDate().isEqual(bli.getPeriodOfBooking().getStartDate()))
	{
	    return 0;
	}
	if(this.periodOfBooking.getStartDate().isBefore(bli.getPeriodOfBooking().getStartDate()))
	{
	    return -1;
	}
	if(this.periodOfBooking.getStartDate().isAfter(bli.getPeriodOfBooking().getStartDate()))
	{
	    return 1;
	}
	//return 0;
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CourseInterface getCourse() {
	return this.course;
    }

    @Override
    public void setCourse(CourseInterface bcourse) {
	this.course = bcourse;
    }

    @Override
    public TeacherInterface getTeacher() {
	return this.lecturer;
    }


}
