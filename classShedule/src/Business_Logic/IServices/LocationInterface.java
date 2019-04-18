/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.IServices;

import Business_Logic.Common.Period;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lawar15
 */
public interface LocationInterface {
  public List<Period> getbookedPeriods(List<BookingLocationsInterface> bookings);
  public void addNewBooking(BookingLocationsInterface bookingOfTheRoom,CourseInterface c);
  public int getNumberOfPlaces();
  public boolean isAvailable(BookingLocationsInterface b);
  public List<BookingLocationsInterface> getCourseBookings(CourseInterface c);
  public List<BookingLocationsInterface> getAllBookings();
  public boolean cancelBooking(BookingLocationsInterface booking);
  public String getNameOfTheLocation();
  public Map <CourseInterface,List<BookingLocationsInterface>> getBookings();
}
