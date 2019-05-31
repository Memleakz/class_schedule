/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.database;

/**
 *
 * @author lawar15
 */
public class SetupForDatabase {

    private static DatabaseManager dbm = DatabaseManager.getInstance();

    private static String createPeriodsQuery
            = "CREATE TABLE IF NOT EXISTS Periods ("
            + "StartDate date, "
            + "FinishDate date, "
            + "periods_id text NOT NULL, "
            + "PRIMARY KEY (periods_id)"
            + ");";
    private static String createUsersQuery
            = "CREATE TABLE IF NOT EXISTS Users ("
            + "Login text NOT NULL, "
            + "Password text, "
            + "users_teacher text, "
            + "name text, "
            + "isClassScheduler boolean, "
            + "PRIMARY KEY(Login), "
            + "FOREIGN KEY(users_teacher) REFERENCES Teachers(teachers_id)"
            + ");";
    private static String createTeachersQuery
            = "CREATE TABLE IF NOT EXISTS Teachers ("
            + "teachers_id text NOT NULL, "
            + "teachersName text, "
            + "PRIMARY KEY(teachers_id)"
            + ");";
    private static String createStudentsForCoursesQuery
            = "CREATE TABLE IF NOT EXISTS StudentsForCourses ("
            + "id_students_to_courses text NOT NULL, "
            + "student text, "
            + "course text, "
            + "PRIMARY KEY(id_students_to_courses), "
            + "FOREIGN KEY(student) REFERENCES ClassOfStudents(classes_id), "
            + "FOREIGN KEY(course) REFERENCES Courses(courses_id)"
            + ");";
    private static String createRoomsAndBookingsQuery
            = "CREATE TABLE IF NOT EXISTS RoomsAndBookings ("
            + "id_rooms_for_bookings text NOT NULL, "
            + "room_for_courses text, "
            + "booked_courses text, "
            + "PRIMARY KEY(id_rooms_for_bookings), "
            + "FOREIGN KEY(room_for_courses) REFERENCES Rooms(rooms_id), "
            + "FOREIGN KEY(booked_courses) REFERENCES BookedCoursesForRoom(id)"
            + ");";
    private static String createRoomsQuery
            = "CREATE TABLE IF NOT EXISTIS Rooms ("
            + "rooms_name text, "
            + "rooms_id text NOT NULL, "
            + "area text, "
            + "numberOfPlaces integer, "
            + "PRIMARY KEY(rooms_id)"
            + ");";
    private static String createPossibleCoursesForTeacherQuery
            = "CREATE TABLE IF NOT EXISTS PossibleCoursesForTeacher ("
            + "id_of_couse_to_teacher text NOT NULL, "
            + "teacher_to_possible text, "
            + "course_possible text, "
            + "PRIMARY KEY(id_of_couse_to_teacher), "
            + "FOREIGN KEY(teacher_to_possible) REFERENCES Teachers(teachers_id), "
            + "FOREIGN KEY(course_possible) REFERENCES Courses(courses_id)"
            + ");";
    private static String createNeighborAreasForRoomQuery
            = "CREATE TABLE IF NOT EXISTS NeighborAreasForRoom ("
            + "id_neighbors text NOT NULL, "
            + "room_for_areas text, "
            + "areas_for_room text, "
            + "PRIMARY KEY(id_neighbors), "
            + "FOREIGN KEY(room_for_areas) REFERENCES Rooms(rooms_id)"
            + ");";
    private static String createDaysQuery
            = "CREATE TABLE IF NOT EXISTS Days ("
            + "dateOfDay date NOT NULL, "
            + "isHollyDay boolean, "
            + "bestPeriod text, "
            + "mediumPeriod text, "
            + "emergencyPeriod text, "
            + "PRIMARY KEY(dateOfDay), "
            + "FOREIGN KEY(bestPeriod) REFERENCES Period(periods_id), "
            + "FOREIGN KEY(mediumPeriod) REFERENCES Period(periods_id), "
            + "FOREIGN KEY(emergencyPeriod) REFERENCES Period(periods_id)"
            + ");";
    private static String createCoursesQuery
            = "CREATE TABLE IF NOT EXISTS Courses ("
            + "courseName text, "
            + "courses_id text NOT NULL, "
            + "numberOfParticipants integer, "
            + "possibleTeachers text, "
            + "numberOfLessons integer, "
            + "numberOfHoursTogether integer, "
            + "maximalTimesOfWeek integer, "
            + "desiredDaysBetweenLectures integer, "
            + "PRIMARY KEY(courses_id), "
            + "FOREIGN KEY(possibleTeachers) REFERENCES PossibleCoursesForTeacher(id_of_couse_to_teacher)"
            + ");";
    private static String createClassesOfStudentsQuery
            = "CREATE TABLE IF NOT EXISTS ClassesOfStudents ("
            + "classes_id text NOT NULL, "
            + "field_of_study text, "
            + "numberOfMembers integer, "
            + "PRIMARY KEY(classes_id)"
            + ");";
    private static String createBookingsQuery
            = "CREATE TABLE IF NOT EXISTS Bookings ("
            + "bookings_id text NOT NULL, "
            + "teacher_for_booking text, "
            + "studentsForBooking text, "
            + "period_for_booking text, "
            + "course_for_booking text, "
            + "PRIMARY KEY(bookings_id), "
            + "FOREIGN KEY(course_for_booking) REFERENCES Courses(courses_id), "
            + "FOREIGN KEY(teacher_for_booking) REFERENCES Teachers(teachers_id), "
            + "FOREIGN KEY(period_for_booking) REFERENCES Periods(periods_id), "
            + "FOREIGN KEY(studentsForBooking) REFERENCES ClassesOfStudents(classes_id)"
            + ");";
    private static String createBookedRoomsForCourseQuery
            = "CREATE TABLE IF NOT EXISTS BookedRoomsForCourse ("
            + "id_bookedRoomsCourse text NOT NULL, "
            + "fk_room text, "
            + "courseInRoom text, "
            + "PRIMARY KEY(id_bookedRoomsCourse), "
            + "FOREIGN KEY(fk_room) REFERENCES Rooms(rooms_id), "
            + "FOREIGN KEY(courseInRoom) REFERENCES Courses(courses_id)"
            + ");";
    private static String createBookedPeriodsForTeachersQuery
            = "CREATE TABLE IF NOT EXISTS BookedPeriodsForTeachers ("
            + "bookings_teachers_id text NOT NULL, "
            + "periods_bookings_teacher text, "
            + "teacher_of_booking text, "
            + "PRIMARY KEY(bookings_teachers_id), "
            + "FOREIGN KEY(teacher_of_booking) REFERENCES Teachers(teachers_id), "
            + "FOREIGN KEY(periods_bookings_teacher) REFERENCES Periods(periods_id)"
            + ");";
    private static String createBookedPeriodsForRoomQuery
            = "CREATE TABLE IF NOT EXISTS BookedPeriodsForRoom ("
            + "id_booked_period text NOT NULL, "
            + "period_for_booking text, "
            + "room_for_period text, "
            + "PRIMARY KEY(id_booked_period), "
            + "FOREIGN KEY(period_for_booking) REFERENCES Periods(periods_id), "
            + "FOREIGN KEY(room_for_period) REFERENCES Rooms(rooms_id)"
            + ");";
    private static String createBookedPeriodsForClassesOfStudentsQuery
            = "CREATE TABLE IF NOT EXISTS BookedPeriodsForClassesOfStudents ("
            + "id_booking_students text NOT NULL, "
            + "bookingsPeriod text, "
            + "classOfStudents text, "
            + "PRIMARY KEY(id_booking_students), "
            + "FOREIGN KEY(classOfStudents) REFERENCES ClassesOfStudents(classes_id), "
            + "FOREIGN KEY(bookingsPeriod) REFERENCES Periods(periods_id)"
            + ");";
    private static String createBookedCoursesForRoomQuery
            = "CREATE TABLE IF NOT EXISTS BookedCoursesForRoom ("
            + "id text NOT NULL, "
            + "courses_to_room text, "
            + "bookings_for_room text, "
            + "PRIMARY KEY(id), "
            + "FOREIGN KEY(bookings_for_room) REFERENCES Bookings(bookings_id), "
            + "FOREIGN KEY (courses_to_room) REFERENCES Courses(courses_id)"
            + ");";
}
