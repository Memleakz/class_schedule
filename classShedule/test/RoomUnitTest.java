/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Business_Logic.Common.Period;
import Business_Logic.IServices.BookingLocationsInterface;
import Business_Logic.IServices.LocationInterface;
import Business_Logic.Locations.RoomFactory;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author Laura
 */
public class RoomUnitTest {
    
    public RoomUnitTest() {
    }
    
  @org.junit.Test
	public void testRoomUpdateAvalible() {
	    int id = 111;
	    String name= "U1";
	    String area= "HovedIndgang";
	    BookingLocationsInterface booking = Mockito.mock(BookingLocationsInterface.class);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	    Period period = Mockito.mock(Period.class);
	    Mockito.when(period.getStartDate()).thenReturn(LocalDateTime.of(2019, Month.MAY, 07, 8, 00, 00));
	    String formattedStartDateTime = period.getStartDate().format(formatter);
	    Mockito.when(period.getEndDate()).thenReturn(LocalDateTime.of(2019, Month.MAY, 07, 12, 00, 00));
	    String formattedEndDateTime = period.getEndDate().format(formatter);
	    Mockito.when(booking.getPeriodOfBooking()).thenReturn(period);
	    List<BookingLocationsInterface> bookings = new ArrayList<BookingLocationsInterface>();
	    bookings.add(booking);
	    List<String> listOfNeighbor = new ArrayList<String>();
	    listOfNeighbor.add("TEK");
	    
	    LocationInterface room = RoomFactory.getRoom(id, name, area, listOfNeighbor, bookings, 1);
	    LocalDateTime dateStartTime = LocalDateTime.of(2019, Month.MAY, 07, 13, 0, 0);
	    LocalDateTime dateEndTime = LocalDateTime.of(2019, Month.MAY, 07, 16, 0, 0);
	    Period periodtoCompare = Mockito.mock(Period.class);
	    Mockito.when(periodtoCompare.getStartDate()).thenReturn(dateStartTime);
	    Mockito.when(periodtoCompare.getEndDate()).thenReturn(dateEndTime);
	    BookingLocationsInterface bookingToCompare = Mockito.mock(BookingLocationsInterface.class);
	    Mockito.when(bookingToCompare.getPeriodOfBooking()).thenReturn(periodtoCompare);
	    Boolean avail = room.isAvailable(bookingToCompare);
	    Assert.assertTrue(avail);

	}
}
