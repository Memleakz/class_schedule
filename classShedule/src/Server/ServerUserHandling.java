/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Business_Logic.Bookings.BookingFactory;
import Business_Logic.Common.Period;
import Business_Logic.Courses.Course;
import Business_Logic.IServices.BookingLocationsInterface;
import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.StudentsInterface;
import Business_Logic.IServices.TeacherInterface;
import Business_Logic.IServices.UserInterface;
import Business_Logic.Participants.ClassOfTheStudents;
import Business_Logic.Responsible.Lecturer;
import Business_Logic.User.User;
import Server.database.DatabaseManager;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lawar15
 */
public class ServerUserHandling {

    private static DatabaseManager dbm = DatabaseManager.getInstance();

    private static String hashPassword(String password) {

	if (password == null) {
	    return null;
	}

	byte[] hashBytes = null;
	// Salting the password
	password += password.substring(0, 4);
	MessageDigest md;
	try {
	    md = MessageDigest.getInstance("SHA-256");
	    md.update(password.getBytes());
	    hashBytes = md.digest();
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	}

	return String.format("%064x", new java.math.BigInteger(1, hashBytes)).toLowerCase();

    }

    public static UserInterface signIn(String login, String password) {
	if (login.isEmpty() || login == null) {
	    throw new IllegalArgumentException("User's login is null or empty.");
	}
	if (password == null || password.isEmpty()) {
	    throw new IllegalArgumentException("User's password is null or empty.");
	}

	String sqllogin = "'" + login + "'";
	String sqlPassword = "'" + password + "'";

	String query = "SELECT \"Login\", \"Password\", \"name\", \"users_teacher\", \"isClassScheduler\" "
		+ "FROM \"Users\" "
		+ "WHERE \"Login\" = " + sqllogin;

	try {
	    ResultSet userrs = dbm.executeQuery(query);
	    TeacherInterface teacher;
	    if (userrs.next()) {
		String usersLogin = userrs.getString("Login");
		String usersPassword = userrs.getString("Password");
		String usersname = userrs.getString("name");
		String teachersId = userrs.getString("users_teacher");
		String hashedControlPassword = hashPassword(usersPassword);
		if (!password.equals(hashedControlPassword)) {
		    //wrong password - return status ? 
		    return null;
		}
		if (userrs.getBoolean("isClassScheduler") == true) {
		    teacher = null;
		} else {
		    teacher = getTeacher(teachersId);
		}

		return new User(usersLogin, usersPassword, teacher, usersname);
	    }

	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}

	return null;

    }

    private static TeacherInterface getTeacher(String teacherID) {
	TeacherInterface teacher;
	String query = "SELECT * FROM \"Teachers\" WHERE \"teachers_id\" = \'" + teacherID + "\' ;";
	List<CourseInterface> possibleCcourses = new ArrayList<CourseInterface>();
	String id = "";
	String name = "";

	try {
	    ResultSet teacherRs = dbm.executeQuery(query);

	    while (teacherRs.next()) {
		name = teacherRs.getString("teachersName");
		id = teacherRs.getString("teachers_id");
	    }

	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	possibleCcourses = getCoursesForTeacher(teacherID);
	for (CourseInterface c : possibleCcourses) {
	    List<StudentsInterface> classOfStudents = getClassForCourse(c.getId());
	    c.getParticipants().addAll(classOfStudents);

	}
	List<Period> teachersBookedPeriods = getBookedPeriodsForTeacher(teacherID);

	teacher = new Lecturer(id, name, teachersBookedPeriods, possibleCcourses);
	return teacher;
    }

    private static List<Period> getBookedPeriodsForTeacher(String teachersID) {
	List<Period> teachersBookedPeriods = new ArrayList<Period>();
	String query5 = "SELECT \"StartDate\", \"FinishDate\" FROM \"BookedPeriodsForTeachers\" NATURAL JOIN \"Periods\" WHERE \"teacher_of_booking\" = \'" + teachersID + "\' ;";
	try {
	    ResultSet periodsteachers = dbm.executeQuery(query5);

	    while (periodsteachers.next()) {
		String tStart = periodsteachers.getString("StartDate");
		String tFinish = periodsteachers.getString("FinishDate");
		Period newPeriod = new Period(tStart, tFinish);
		teachersBookedPeriods.add(newPeriod);

	    }

	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return teachersBookedPeriods;
    }

    private static int setNewBookedPeriodForTeacher(String teachersID, String startdate, String finishdate) {
	Period newPeriod = changeDataPickersToPeriod(startdate, finishdate);
	if (newPeriod == null || newPeriod.getStartDate() == null || newPeriod.getEndDate() == null) {
	    throw new IllegalArgumentException("Period is null or empty.");
	}
	String teachersId = "null";
	if (teachersID != null && !teachersID.isEmpty()) {
	    teachersId = "'" + teachersID + "'";
	}
	String periodQuery = "INSERT INTO \"Periods\" (\"StartDate\",\"FinishDate\",\"periods_id\") VALUES ( " + newPeriod.getStartDate() + ", " + newPeriod.getEndDate() + ", " + "DEFAULT" + "?)";
	int periodsid = dbm.executeInsertAndGetId(periodQuery);
	String periodTeacherQuery = "INSERT INTO \"BookedPeriodsForTeachers\" (\"bookings_teachers_id\", \"periods_bookings_teacher\", \"teacher_of_booking\") VALUES (DEFAULT, " + periodsid + ", /" + teachersID + "\' ;";
	return dbm.executeInsertAndGetId(periodTeacherQuery);
    }

    private static List<CourseInterface> getCoursesForTeacher(String teachersID) {
	List<CourseInterface> possibleCoursesss = new ArrayList<CourseInterface>();
	String cName = "";
	int cParticipants = 0;
	int cNumberLessons = 0;
	int cNumberHoursTogether = 0;
	int cMaximalWeek = 0;
	int cDesiredDays = 0;
	String cId = "";
	String query2 = "SELECT \"courseName\", \"courses_id\", \"numberOfParticipants\", \"numberOfLessons\", \"numberOfHoursTogether\",\"maximalTimesOfWeek\" FROM \"Courses\" NATURAL JOIN \"PossibleCoursesForTeacher\" WHERE \"teacher_to_possible\" = \'" + teachersID + "\' ;";
	try {
	    ResultSet coursess = dbm.executeQuery(query2);

	    while (coursess.next()) {
		cName = coursess.getString("courseName");
		cNumberLessons = coursess.getInt("numberOfLessons");
		cNumberHoursTogether = coursess.getInt("numberOfHoursTogether");
		cMaximalWeek = coursess.getInt("maximalTimesOfWeek");
		cId = coursess.getString("courses_id");
		Course course = new Course(cId, cName, new ArrayList<StudentsInterface>(), cNumberLessons, cNumberHoursTogether, cMaximalWeek);
		possibleCoursesss.add(course);
	    }

	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return possibleCoursesss;
    }

    private static List<StudentsInterface> getClassForCourse(String coursesID) {
	List<StudentsInterface> classesOfStudents = new ArrayList<StudentsInterface>();
	String query3 = "SELECT \"classes_id\", \"field_of_study\", \"numberOfMembers\" FROM \"ClassesOfStudents\" NATURAL JOIN \"StudentsForCourses\" WHERE \"course\" = \'" + coursesID/*c.getId()*/ + "\' ;";

	try {
	    ResultSet studentss = dbm.executeQuery(query3);

	    while (studentss.next()) {
		String studentsid = studentss.getString("classes_id");
		String studentsfield = studentss.getString("field_of_study");
		int studentsNumber = studentss.getInt("numberOfMembers");
		List<Period> bookedPeriodsForClass = getBookedPeriodsForClass(studentsid);

		StudentsInterface classOfStudents = new ClassOfTheStudents(studentsid, studentsfield, studentsNumber, bookedPeriodsForClass);
		classesOfStudents.add(classOfStudents);

	    }

	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return classesOfStudents;
    }

    private static List<Period> getBookedPeriodsForClass(String classesID) {
	List<Period> studentsbookedperiods = new ArrayList<Period>();
	String query4 = "SELECT \"StartDate\", \"FinishDate\" FROM \"Periods\" NATURAL JOIN \"BookedPeriodsForClassesOfStudents\" WHERE \"classOfStudents\" = \'" + classesID + "\' ;";
	try {
	    ResultSet periodssforss = dbm.executeQuery(query4);

	    while (periodssforss.next()) {
		String start = periodssforss.getString("StartDate");
		String finish = periodssforss.getString("FinishDate");
		Period period = new Period(start, finish);
		studentsbookedperiods.add(period);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return studentsbookedperiods;
    }

    static TeacherInterface handleTeachersAbsence(String teacherID, String startDateOfAbsence, String finishDateOfAbsence) {
	TeacherInterface myTeacher = null;
	List<CourseInterface> teachersCourses = new ArrayList<CourseInterface>();
	teachersCourses = getCoursesForTeacher(teacherID);

	setNewBookedPeriodForTeacher(teacherID, startDateOfAbsence, finishDateOfAbsence);
	myTeacher = getTeacher(teacherID);
	return myTeacher;
    }

    static void moveCoursesForTeachersAbsence(String teacherID, String startDateOfAbsence, String finishDateOfAbsence, List<CourseInterface> teachersCourses) {
	Period periodForAbsence = null;
	periodForAbsence = changeDataPickersToPeriod(startDateOfAbsence, finishDateOfAbsence);
	List<Period> bookedPeriods = null;
	for (CourseInterface course : teachersCourses) {

	}
    }

    static Period changeDataPickersToPeriod(String startDateOfAbsence, String finishDateOfAbsence) {
	String[] partsStart = startDateOfAbsence.split("-");
	String[] partsFinish = finishDateOfAbsence.split("-");
	String replaceStringStart = partsStart[2] + "/" + partsStart[1] + "/" + partsStart[0] + " 00:00:01";
	String replaceStringFinish = partsFinish[2] + "/" + partsFinish[1] + "/" + partsFinish[0] + " 23:59:59";
	Period newPeriod = new Period(replaceStringStart, replaceStringFinish);
	return newPeriod;
    }

    static Period getPeriodForId(String periodsId) {
	Period periodwithId = null;
	String query = "SELECT \"StartDate\", \"FinishDate\" FROM \"Periods\" WHERE \"periods_id\" = \'" + periodsId + "\' ;";
	try {
	    ResultSet periods = dbm.executeQuery(query);

	    while (periods.next()) {
		String start = periods.getString("StartDate");
		String finish = periods.getString("FinishDate");
		Period period = new Period(start, finish);
		periodwithId = period;
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return periodwithId;
    }

    static List<BookingLocationsInterface> getAllBookingsForTeacher(String teacherID) {
	List<BookingLocationsInterface> teachersBookings = new ArrayList<BookingLocationsInterface>();
	String query = "SELECT * FROM \"Bookings\" WHERE \"teacher_for_booking\" = \'" + teacherID + "\' ;";
	try {
	    ResultSet bookingsForTeacher = dbm.executeQuery(query);

	    while (bookingsForTeacher.next()) {
		String studentsClassId = studentsClassId = bookingsForTeacher.getString("studentsForBooking");
		String bookedPeriodId = bookedPeriodId = bookingsForTeacher.getString("period_for_booking");
		String courseId = courseId = bookingsForTeacher.getString("course_for_booking");
		int bookingsId = bookingsId = bookingsForTeacher.getInt("bookings_id");
		List<StudentsInterface> classForCourse = getClassForCourse(courseId);
		Period periodforbooking = getPeriodForId(bookedPeriodId);
		CourseInterface courseforBooking = getCourseById(courseId);
		TeacherInterface teacherForCourse = getTeacher(teacherID);
		teachersBookings.add(BookingFactory.getBookingOfTheRoom(periodforbooking.getStartDate().toString(), periodforbooking.getEndDate().toString(), courseforBooking, teacherForCourse, classForCourse));

	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}

	return teachersBookings;

    }

    static Map<Integer, Map<Period, CourseInterface>> getClassScheduleForTeacher(String teachersID) {
	List<BookingLocationsInterface> bookings = getAllBookingsForTeacher(teachersID);
	Map<Period, CourseInterface> mapForTeacher = new TreeMap<Period, CourseInterface>();
	Map<Integer, Map<Period, CourseInterface>> maptoreturn = new TreeMap<Integer, Map<Period, CourseInterface>>();
	TeacherInterface teacher = getTeacher(teachersID);
	List<Period> bookedperiodsForTeacher = getBookedPeriodsForTeacher(teachersID);
	List<CourseInterface> coursesForTeacher = getCoursesForTeacher(teachersID);
	for (Period period : bookedperiodsForTeacher) {
	    for (BookingLocationsInterface booking : bookings) {
		if (booking.getPeriodOfBooking() == period && period != null && booking != null) {
		    CourseInterface courseToSet = booking.getCourse();
		    mapForTeacher.put(period, courseToSet);
		}
	    }

	}
	for (Map.Entry<Period, CourseInterface> entry : mapForTeacher.entrySet()) {
	    int weeknumber = getNumberOfWeekForPeriod(entry.getKey());
	    maptoreturn.put(weeknumber, (Map<Period, CourseInterface>) entry);
	}
	return maptoreturn;
    }

    static Integer getNumberOfWeekForPeriod(Period p) {
	Calendar cal = Calendar.getInstance();
	Date dateOfStart = Date.from(p.getStartDate().atZone(ZoneId.systemDefault()).toInstant());
	cal.setTime(dateOfStart);
	int week = cal.get(Calendar.WEEK_OF_YEAR);
	return week;
    }

    static CourseInterface getCourseById(String courseId) {
	CourseInterface coursewithId = null;
	String query = "SELECT * FROM \"Courses\" WHERE \"courses_id\" = \'" + courseId + "\' ;";
	try {
	    ResultSet courses = dbm.executeQuery(query);

	    while (courses.next()) {
		String nameOfTheCourse = courses.getString("courseName");
		int numberLessons = courses.getInt("numberOfLessons");
		int numberhoursTogether = courses.getInt("numberOfHoursTogether");
		int maxLecturesInWeek = courses.getInt("maximalTimesOfWeek");
		List<StudentsInterface> studentsinCourse = getClassForCourse(courseId);
		//, int numberOflessons, int numberOfHourstogether, int maxOnWeek
		CourseInterface course = new Course(courseId, nameOfTheCourse, studentsinCourse, numberLessons, numberhoursTogether, maxLecturesInWeek);
		coursewithId = course;
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return coursewithId;

    }

    static String getTeachersIDByName(String name) {
	String teachersid = "";
	String query = "SELECT \"teachers_id\" FROM \"Teachers\" WHERE \"teachersName\" = \'" + name + "\' ;";
	try {
	    ResultSet teachers = dbm.executeQuery(query);

	    while (teachers.next()) {
		teachersid = teachers.getString("teachers_id");
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ServerUserHandling.class.getName()).log(Level.SEVERE, null, ex);
	}
	return teachersid;
    }

}
