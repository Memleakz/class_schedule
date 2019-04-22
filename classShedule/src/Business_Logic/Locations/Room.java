/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.Locations;

import Business_Logic.Common.Period;
import Business_Logic.IServices.BookingLocationsInterface;
import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.LocationInterface;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author lawar15
 */
public class Room implements LocationInterface, Serializable{

    int id;
    String nameOfTheRoom;
    String area;
    List<String> closeNeighborAreas;
    Map<CourseInterface,List<BookingLocationsInterface>> bookings;
    List<Period> bookedPeriods;
    int numberOfPlaces;

    public Room(int id, String nameOfTheRoom, String area, List<String> closeNeighborAreas, List<BookingLocationsInterface> bookings, int numberOfPlaces) {
	this.id = id;
	this.nameOfTheRoom = nameOfTheRoom;
	this.area = area;
	this.closeNeighborAreas = closeNeighborAreas;
	this.bookings = new HashMap<CourseInterface,List<BookingLocationsInterface>>();
	this.bookedPeriods = this.getbookedPeriods(bookings);
	this.numberOfPlaces = numberOfPlaces;
    }
    @Override
    public Map <CourseInterface,List<BookingLocationsInterface>> getBookings(){
	return this.bookings;
    }
    public List<Period> getbookedPeriods(List<BookingLocationsInterface> bookings) {
	List<Period> bookedPeriodsForTheRoom = new ArrayList<Period>();
	for (BookingLocationsInterface bookingTMP : bookings) {
	    Period bookingsPeriod = bookingTMP.getPeriodOfBooking();
	    bookedPeriodsForTheRoom.add(bookingsPeriod);
	}
	return bookedPeriodsForTheRoom;
    }

 

    public int getNumberOfPlaces() {
	return this.numberOfPlaces;
    }

    private String makeIDforBooking() {
	/*int idOfBooking = Integer.getInteger(this.bookings.get(this.bookings.size() - 1).getId());
	idOfBooking++;
	String returnId = Integer.toString(idOfBooking);
	return returnId;*/
	return null;
    }

    @Override
    public List<BookingLocationsInterface> getCourseBookings(CourseInterface c)
    {
	return this.bookings.get(c);
    }
    @Override
    public List<BookingLocationsInterface> getAllBookings()
    {
	List<BookingLocationsInterface> all = new ArrayList<BookingLocationsInterface>();
	for (Map.Entry<CourseInterface, List<BookingLocationsInterface>> entry : this.bookings.entrySet())
	{
	    List<BookingLocationsInterface> booklist = entry.getValue();
	    all.addAll(booklist);
	}
	
	Collections.sort(all);
	return all;
    }
    @Override
    public boolean cancelBooking(BookingLocationsInterface booking)
    {
	List<BookingLocationsInterface> bookings = this.bookings.get(booking.getCourse());
	if(bookings.contains(booking))
	{
	    bookings.remove(booking);
	    this.bookings.put(booking.getCourse(), bookings);
	    return true;
	}
	return false;
    }

    @Override
    public void addNewBooking(BookingLocationsInterface bookingOfTheRoom, CourseInterface c) {
	
	List<BookingLocationsInterface> old_bookings = this.bookings.get(c);
	if(old_bookings != null)
	{
	    old_bookings.add(bookingOfTheRoom);
	    this.bookings.put(c, old_bookings);
	}
	else
	{
	    old_bookings = new ArrayList<BookingLocationsInterface>();
	    old_bookings.add(bookingOfTheRoom);
	    this.bookings.put(c, old_bookings);
	}
    }

    @Override
    public boolean isAvailable(BookingLocationsInterface b) {
	Period p = b.getPeriodOfBooking();
	if(this.bookings.isEmpty())
	{
	    return true;
	}
	boolean no_overlap = true;
	for (Map.Entry<CourseInterface, List<BookingLocationsInterface>> entry : this.bookings.entrySet())
	{
	    CourseInterface c = entry.getKey();
	    List<BookingLocationsInterface> booklist = entry.getValue();
	    for(BookingLocationsInterface br : booklist)
	    {
		if(!br.IsAvailable(p))
		{
		    no_overlap = false;
		    break;
		}
	    }
	}
	
	return no_overlap;
    }

    @Override
    public String getNameOfTheLocation() {
	return this.nameOfTheRoom;
    }
        @Override
    public String toString()
    {
	return this.nameOfTheRoom;
    }
}
