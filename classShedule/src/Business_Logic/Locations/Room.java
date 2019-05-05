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
import Business_Logic.IServices.TeacherInterface;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


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
    public boolean cancelBooking(BookingLocationsInterface b)
    {
	List<BookingLocationsInterface> bookings = this.bookings.get(b.getCourse());
	for( BookingLocationsInterface booking : bookings)
	{
	    if(booking.getPeriodOfBooking().getStartDate().compareTo(b.getPeriodOfBooking().getStartDate()) == 0
	    && booking.getPeriodOfBooking().getEndDate().compareTo(b.getPeriodOfBooking().getEndDate()) == 0)
	    {
		bookings.remove(booking);
		this.bookings.put(booking.getCourse(), bookings);
	        return true;
	    }
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
    @Override
    public Map<Integer, List<BookingLocationsInterface>> getBookingsForWeek(int weeknr)
    {
	SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy");
	Calendar cal = Calendar.getInstance();
	cal.set(Calendar.WEEK_OF_YEAR, weeknr);        
	cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	
	List<BookingLocationsInterface> all = new ArrayList<BookingLocationsInterface>();
	List<BookingLocationsInterface> booklist = new ArrayList<BookingLocationsInterface>();
	for (Map.Entry<CourseInterface, List<BookingLocationsInterface>> entry : this.bookings.entrySet())
	{
	     booklist.addAll(entry.getValue());
	    
	}
	all.addAll(booklist);
	Map<String, List<BookingLocationsInterface>> hour_sortet_bookings = new TreeMap<String, List<BookingLocationsInterface>>();
	Map<Integer, List<BookingLocationsInterface>> day_sortet_bookings = new TreeMap<Integer,List<BookingLocationsInterface>>(); 
	//Sort all bookings by weekday and exclude by weeknr
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy");
	for(int i = 0;i < 7;i++)
	{
	
	for(BookingLocationsInterface b : all)
	{
	    String date = sdf.format(cal.getTime());
	    String date1 = b.getPeriodOfBooking().getStartDate().format(formatter);
	    if(date.equals(date1))
	    {
		int dayOFWeek = b.getPeriodOfBooking().getStartDate().getDayOfWeek().getValue();
		List<BookingLocationsInterface> bookingsfordate = day_sortet_bookings.get(dayOFWeek);
		if(bookingsfordate == null)
		{
		  bookingsfordate = new ArrayList<BookingLocationsInterface>();
		}
		bookingsfordate.add(b);
		day_sortet_bookings.put(dayOFWeek, bookingsfordate);
	    }	
	}
	    cal.add(Calendar.DAY_OF_WEEK, 1);
	}
	    
	
	//sort all weekdays by hour start
	
	return day_sortet_bookings;
    }
    public boolean cancel_booking(BookingLocationsInterface b)
    {
	/*List<BookingLocationsInterface> bookings = this.bookings.get(b.getCourse());
	for( BookingLocationsInterface booking : bookings)
	{
	    if(booking.getPeriodOfBooking().getStartDate().compareTo(b.getPeriodOfBooking().getStartDate()) == 0
	    && booking.getPeriodOfBooking().getEndDate().compareTo(b.getPeriodOfBooking().getEndDate()) == 0)
	    {
		bookings.remove(booking);
	    }
	}
	return bookings.remove(b);*/
	return false;
    }
    public List<BookingLocationsInterface> getAllBookingsForTeacher(TeacherInterface t,Period p)
    {
	List<BookingLocationsInterface> all = new ArrayList<BookingLocationsInterface>();
	for (Map.Entry<CourseInterface, List<BookingLocationsInterface>> entry : this.bookings.entrySet())
	{
	    List<BookingLocationsInterface> booklist = entry.getValue();
	    for(BookingLocationsInterface b :booklist)
	    {
		if(b.getTeacher().getTeachersId().equals(t.getTeachersId()))
		{
		    if(p != null)
		    {
			//filter on period also.
			if(b.getPeriodOfBooking().getStartDate().compareTo(p.getStartDate()) >= 0
			&& b.getPeriodOfBooking().getEndDate().compareTo(p.getEndDate()) <= 0)
			{
			    all.add(b);
			}
		    }
		    else
		    {
			all.add(b);
		    }
		}
	    }
	    
	}
	
	Collections.sort(all);
	return all;
    }

    @Override
    public int getId() {
	return this.id;
    }
}