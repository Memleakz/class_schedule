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
import java.util.List;

/**
 *
 * @author lawar15
 */
public class BookingFactory {
    
  public static BookingLocationsInterface getBookingOfTheRoom(String start , String end,CourseInterface course,TeacherInterface lecturer, List<StudentsInterface> students)
  {
      Period p = new Period(start,end);
      return new BookingOfTheRoom(course,lecturer,students,p);
  }
}
