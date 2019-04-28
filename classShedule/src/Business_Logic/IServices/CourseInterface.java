/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.IServices;

import java.util.List;

/**
 *
 * @author lawar15
 */
public interface CourseInterface {

    public void setDesiredDaysBetweenLectures(int space);

    public int getNumberOfParticipants();
    public String getId();

    public void setBookedRooms(List<LocationInterface> rooms);

    public List<LocationInterface> getBookedRooms();
    public String getNameOfTheCourse();
    public int getNumberOfLessons();
    public int getNumberOfHourstogether();
    public List<StudentsInterface> getParticipants();
    public int getDeseiredDaysBetweenLectures();
    public CourseInterface getCourseByName(List<CourseInterface> list,String name);
    public TeacherInterface getAssignedLecturer();
    public void setAssignedLecturer(TeacherInterface Teacher);
    public LocationInterface getRoomReferencedByBooking(BookingLocationsInterface booking);
    public void updateRoom(LocationInterface room);
}
