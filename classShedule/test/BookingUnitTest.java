/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Business_Logic.Bookings.BookingFactory;
import Business_Logic.Common.Period;
import Business_Logic.Common.Schedule;
import Business_Logic.IServices.BookingLocationsInterface;
import Business_Logic.IServices.CourseInterface;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.mockito.Mockito;

/**
 *
 * @author lawar15
 */
public class BookingUnitTest {
    
    public BookingUnitTest() {
    }
@org.junit.Test
	public void testisPeriodAvalible() {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	    Period period = Mockito.mock(Period.class);
	    Mockito.when(period.getStartDate()).thenReturn(LocalDateTime.of(2019, Month.MAY, 07, 8, 00, 00));
	    String formattedStartDateTime = period.getStartDate().format(formatter);
	    Mockito.when(period.getEndDate()).thenReturn(LocalDateTime.of(2019, Month.MAY, 07, 12, 00, 00));
	    String formattedEndDateTime = period.getEndDate().format(formatter);
	    CourseInterface course= Mockito.mock(CourseInterface.class);
	    Schedule schedule = Mockito.mock(Schedule.class);
	    BookingLocationsInterface booking = BookingFactory.getBookingOfTheRoom(formattedStartDateTime, formattedEndDateTime, course, schedule);
	    LocalDateTime dateStartTime = LocalDateTime.of(2019, Month.MAY, 07, 13, 0, 0);
	    LocalDateTime dateEndTime = LocalDateTime.of(2019, Month.MAY, 07, 16, 0, 0);
	    Period periodtoCompare = Mockito.mock(Period.class);
	    Mockito.when(periodtoCompare.getStartDate()).thenReturn(dateStartTime);
	    Mockito.when(periodtoCompare.getEndDate()).thenReturn(dateEndTime);

	    Boolean avail = booking.IsAvailable(periodtoCompare);
	    Assert.assertTrue(avail);
	
	    
	}
}
