/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.Courses;

import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.LocationInterface;
import Business_Logic.IServices.StudentsInterface;
import Business_Logic.IServices.TeacherInterface;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author lawar15
 */
public class Course implements CourseInterface, Serializable{
    String id;
    String nameOfTheCourse;
    List<StudentsInterface> students;
    TeacherInterface assignedLecturer;
    int numberOfParticipants;

    int numberOflessons;
    int numberOfHourstogether;
    int maximalTimesOfWeek;
    int deseiredDaysBetweenLectures;
    List<LocationInterface> bookedRooms;
    
   public Course(String id, String nameOfTheCourse,List<StudentsInterface> students, int numberOflessons, int numberOfHourstogether, int maxOnWeek ) {
       this.id = id;
       this.nameOfTheCourse = nameOfTheCourse;
       this.students = students;
       this.numberOfParticipants= 0;
       this.numberOflessons = numberOflessons;
       this.numberOfHourstogether = numberOfHourstogether;
       this.maximalTimesOfWeek = maxOnWeek;
       this.assignedLecturer = null;
       for (StudentsInterface oneOfTheClasses: students){
	  int studentsInOneClass= oneOfTheClasses.getNumberOfStuents();
	  this.numberOfParticipants= this.numberOfParticipants+studentsInOneClass;
       }
       
       
   }
    @Override
   public void setDesiredDaysBetweenLectures(int space){
       this.deseiredDaysBetweenLectures = space;
   }
    @Override
   public int getNumberOfParticipants(){
       return this.numberOfParticipants;
   }

   public void setBookedRooms(List<LocationInterface> rooms)
   {
       this.bookedRooms = rooms;
   }
   public List<LocationInterface> getBookedRooms()
   {
       return this.bookedRooms;
   }

    @Override
    public String getNameOfTheCourse() {
	return this.nameOfTheCourse;
    }

    @Override
    public int getNumberOfLessons() {
	return this.numberOflessons;
    }

    @Override
    public int getNumberOfHourstogether() {
	return this.numberOfHourstogether;
    }

    @Override
    public List<StudentsInterface> getParticipants() {
	return this.students;
    }

    @Override
    public int getDeseiredDaysBetweenLectures() {
	return this.deseiredDaysBetweenLectures;
    }

    @Override
    public CourseInterface getCourseByName(List<CourseInterface> list,String name) {
	CourseInterface returnCourse= null;
	for(CourseInterface randomCourse: list){
	    if(randomCourse.getNameOfTheCourse()==name){
		returnCourse = randomCourse;
	    }
	}
	return returnCourse;
    }

    @Override
    public String getId() {
    return this.id;
    }
    @Override
    public String toString()
    {
	return this.nameOfTheCourse;
    }

    @Override
    public TeacherInterface getAssignedLecturer() {
	return this.assignedLecturer;
    }

    @Override
    public void setAssignedLecturer(TeacherInterface Teacher) {
	this.assignedLecturer = Teacher;
    }
}
