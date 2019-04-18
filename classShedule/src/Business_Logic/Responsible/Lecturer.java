/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.Responsible;

import Business_Logic.Common.Period;
import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.TeacherInterface;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author lawar15
 */
public class Lecturer implements TeacherInterface, Serializable {
    String id;
    String name;
    List<Period> bookedPeriods;
    List<CourseInterface> possibleCourses;
    public Lecturer(String id, String name, List<Period> bookedPeriods, List<CourseInterface> possibleCourses){
	this.id = id;
	this.name = name;
	this.bookedPeriods =bookedPeriods;
	this.possibleCourses = possibleCourses;
    }

    @Override
    public List<Period> getbookedPeriods() {
	return this.bookedPeriods;
    }

    @Override
    public List<CourseInterface> getPossibleCourses() {
	return this.possibleCourses;
    }



    @Override
    public String getName() {
	return this.name;
    }

    @Override
    public boolean isPeriodAvailable(String startdate, String finishdate) {
    Period periodOfBooking = new Period(startdate, finishdate);
    int start_compare = 0;
    int end_compare = 0;
    int start_compare_end = 0;
    boolean isAvalible = true;
    for(Period periodOfAll : this.bookedPeriods)
    {
	start_compare = periodOfAll.getStartDate().compareTo(periodOfBooking.getStartDate());
	end_compare = periodOfAll.getEndDate().compareTo(periodOfBooking.getStartDate());
	start_compare_end = periodOfAll.getStartDate().compareTo(periodOfBooking.getEndDate());
	
	if(start_compare <= 0 && end_compare <= 0)
	{
	    
	    isAvalible = true;
	}
	if(start_compare_end >= 0)
	{
	    isAvalible = true;
	}
	
	return false;
    }
    if(isAvalible== true){
    return true;
    }
    return false;
}

    @Override
    public String getTeachersId() {
    return this.id;
    }


}
