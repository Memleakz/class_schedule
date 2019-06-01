--
-- PostgreSQL database dump
--

-- Dumped from database version 11.3
-- Dumped by pg_dump version 11.3

-- Started on 2019-06-01 22:02:27

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 17096)
-- Name: BookedPeriodsForClassesOfStudents; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."BookedPeriodsForClassesOfStudents" (
    "bookingsPeriod" text,
    "classOfStudents" text,
    id_booking_students integer NOT NULL
);


ALTER TABLE public."BookedPeriodsForClassesOfStudents" OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 17102)
-- Name: BookedPeriodsForClassesOfStudents_id_booking_students_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."BookedPeriodsForClassesOfStudents_id_booking_students_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."BookedPeriodsForClassesOfStudents_id_booking_students_seq" OWNER TO postgres;

--
-- TOC entry 3051 (class 0 OID 0)
-- Dependencies: 197
-- Name: BookedPeriodsForClassesOfStudents_id_booking_students_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."BookedPeriodsForClassesOfStudents_id_booking_students_seq" OWNED BY public."BookedPeriodsForClassesOfStudents".id_booking_students;


--
-- TOC entry 198 (class 1259 OID 17104)
-- Name: BookedPeriodsForRoom; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."BookedPeriodsForRoom" (
    id_booked_period integer NOT NULL,
    room_for_period integer,
    period_for_booking integer
);


ALTER TABLE public."BookedPeriodsForRoom" OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 17107)
-- Name: BookedPeriodsForRoom_id_booked_period_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."BookedPeriodsForRoom_id_booked_period_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."BookedPeriodsForRoom_id_booked_period_seq" OWNER TO postgres;

--
-- TOC entry 3052 (class 0 OID 0)
-- Dependencies: 199
-- Name: BookedPeriodsForRoom_id_booked_period_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."BookedPeriodsForRoom_id_booked_period_seq" OWNED BY public."BookedPeriodsForRoom".id_booked_period;


--
-- TOC entry 200 (class 1259 OID 17109)
-- Name: BookedPeriodsForTeachers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."BookedPeriodsForTeachers" (
    periods_bookings_teacher text,
    teacher_of_booking text,
    bookings_teachers_id integer NOT NULL
);


ALTER TABLE public."BookedPeriodsForTeachers" OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 17115)
-- Name: BookedPeriodsForTeachers_bookings_teachers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."BookedPeriodsForTeachers_bookings_teachers_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."BookedPeriodsForTeachers_bookings_teachers_id_seq" OWNER TO postgres;

--
-- TOC entry 3053 (class 0 OID 0)
-- Dependencies: 201
-- Name: BookedPeriodsForTeachers_bookings_teachers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."BookedPeriodsForTeachers_bookings_teachers_id_seq" OWNED BY public."BookedPeriodsForTeachers".bookings_teachers_id;


--
-- TOC entry 202 (class 1259 OID 17117)
-- Name: BookedRoomsForCourse; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."BookedRoomsForCourse" (
    "courseInRoom" text,
    "id_bookedRoomsCourse" integer NOT NULL,
    fk_room integer
);


ALTER TABLE public."BookedRoomsForCourse" OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 17123)
-- Name: BookedRoomsForCourse_id_bookedRoomsCourse_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."BookedRoomsForCourse_id_bookedRoomsCourse_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."BookedRoomsForCourse_id_bookedRoomsCourse_seq" OWNER TO postgres;

--
-- TOC entry 3054 (class 0 OID 0)
-- Dependencies: 203
-- Name: BookedRoomsForCourse_id_bookedRoomsCourse_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."BookedRoomsForCourse_id_bookedRoomsCourse_seq" OWNED BY public."BookedRoomsForCourse"."id_bookedRoomsCourse";


--
-- TOC entry 204 (class 1259 OID 17125)
-- Name: Bookings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Bookings" (
    teacher_for_booking text,
    course_for_booking text,
    bookings_id integer NOT NULL,
    period_for_booking integer,
    "studentsForBooking" integer,
    schedules_id integer
);


ALTER TABLE public."Bookings" OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 17131)
-- Name: Bookings_bookings_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Bookings_bookings_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Bookings_bookings_id_seq" OWNER TO postgres;

--
-- TOC entry 3055 (class 0 OID 0)
-- Dependencies: 205
-- Name: Bookings_bookings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Bookings_bookings_id_seq" OWNED BY public."Bookings".bookings_id;


--
-- TOC entry 206 (class 1259 OID 17133)
-- Name: ClassesOfStudents; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."ClassesOfStudents" (
    field_of_study text,
    "numberOfMembers" integer,
    classes_id text NOT NULL
);


ALTER TABLE public."ClassesOfStudents" OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 17139)
-- Name: Courses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Courses" (
    "courseName" text,
    "numberOfParticipants" integer,
    "numberOfLessons" integer,
    "numberOfHoursTogether" integer,
    "maximalTimesOfWeek" integer,
    "desiredDaysBetweenLectures" integer,
    courses_id text NOT NULL,
    assigned_teacher text
);


ALTER TABLE public."Courses" OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 17145)
-- Name: Days; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Days" (
    "dateOfDay" date NOT NULL,
    "isHollyDay" boolean,
    "bestPeriod" text,
    "mediumPeriod" text,
    "emergencyPeriod" text
);


ALTER TABLE public."Days" OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 17151)
-- Name: DaysBookingPeriodes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."DaysBookingPeriodes" (
    "DaysBookinPeriodId" integer NOT NULL,
    daysbookinperiodename text,
    starthour text,
    endhour text,
    day text
);


ALTER TABLE public."DaysBookingPeriodes" OWNER TO postgres;

--
-- TOC entry 3056 (class 0 OID 0)
-- Dependencies: 209
-- Name: TABLE "DaysBookingPeriodes"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public."DaysBookingPeriodes" IS 'Contains the configure values for optimal booking periodes for different booking periodes';


--
-- TOC entry 210 (class 1259 OID 17157)
-- Name: DaysBookingPeriodes_DaysBookinPeriodId_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."DaysBookingPeriodes_DaysBookinPeriodId_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."DaysBookingPeriodes_DaysBookinPeriodId_seq" OWNER TO postgres;

--
-- TOC entry 3057 (class 0 OID 0)
-- Dependencies: 210
-- Name: DaysBookingPeriodes_DaysBookinPeriodId_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."DaysBookingPeriodes_DaysBookinPeriodId_seq" OWNED BY public."DaysBookingPeriodes"."DaysBookinPeriodId";


--
-- TOC entry 211 (class 1259 OID 17159)
-- Name: NeighborAreasForRoom; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."NeighborAreasForRoom" (
    areas_for_room text,
    id_neighbors integer NOT NULL,
    room_for_areas integer
);


ALTER TABLE public."NeighborAreasForRoom" OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 17165)
-- Name: NeighborAreasForRoom_id_neighbors_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."NeighborAreasForRoom_id_neighbors_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."NeighborAreasForRoom_id_neighbors_seq" OWNER TO postgres;

--
-- TOC entry 3058 (class 0 OID 0)
-- Dependencies: 212
-- Name: NeighborAreasForRoom_id_neighbors_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."NeighborAreasForRoom_id_neighbors_seq" OWNED BY public."NeighborAreasForRoom".id_neighbors;


--
-- TOC entry 213 (class 1259 OID 17167)
-- Name: Periods; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Periods" (
    "StartDate" timestamp without time zone,
    "FinishDate" timestamp without time zone,
    periods_id integer NOT NULL
);


ALTER TABLE public."Periods" OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 17170)
-- Name: Periods_periods_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Periods_periods_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Periods_periods_id_seq" OWNER TO postgres;

--
-- TOC entry 3059 (class 0 OID 0)
-- Dependencies: 214
-- Name: Periods_periods_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Periods_periods_id_seq" OWNED BY public."Periods".periods_id;


--
-- TOC entry 215 (class 1259 OID 17172)
-- Name: PossibleCoursesForTeacher; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."PossibleCoursesForTeacher" (
    teacher_to_possible text,
    course_possible text,
    id_of_couse_to_teacher integer NOT NULL
);


ALTER TABLE public."PossibleCoursesForTeacher" OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 17178)
-- Name: PossibleCoursesForTeacher_id_of_couse_to_teacher_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."PossibleCoursesForTeacher_id_of_couse_to_teacher_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."PossibleCoursesForTeacher_id_of_couse_to_teacher_seq" OWNER TO postgres;

--
-- TOC entry 3060 (class 0 OID 0)
-- Dependencies: 216
-- Name: PossibleCoursesForTeacher_id_of_couse_to_teacher_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."PossibleCoursesForTeacher_id_of_couse_to_teacher_seq" OWNED BY public."PossibleCoursesForTeacher".id_of_couse_to_teacher;


--
-- TOC entry 217 (class 1259 OID 17180)
-- Name: Rooms; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Rooms" (
    rooms_name text,
    area text,
    "numberOfPlaces" integer,
    rooms_id integer NOT NULL
);


ALTER TABLE public."Rooms" OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 17186)
-- Name: RoomsAndBookings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."RoomsAndBookings" (
    id_rooms_for_bookings integer NOT NULL,
    room_for_courses integer,
    "bookingsToRooms" integer
);


ALTER TABLE public."RoomsAndBookings" OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 17189)
-- Name: RoomsAndBookings_id_rooms_for_bookings_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."RoomsAndBookings_id_rooms_for_bookings_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."RoomsAndBookings_id_rooms_for_bookings_seq" OWNER TO postgres;

--
-- TOC entry 3061 (class 0 OID 0)
-- Dependencies: 219
-- Name: RoomsAndBookings_id_rooms_for_bookings_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."RoomsAndBookings_id_rooms_for_bookings_seq" OWNED BY public."RoomsAndBookings".id_rooms_for_bookings;


--
-- TOC entry 220 (class 1259 OID 17191)
-- Name: Rooms_rooms_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Rooms_rooms_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Rooms_rooms_id_seq" OWNER TO postgres;

--
-- TOC entry 3062 (class 0 OID 0)
-- Dependencies: 220
-- Name: Rooms_rooms_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Rooms_rooms_id_seq" OWNED BY public."Rooms".rooms_id;


--
-- TOC entry 221 (class 1259 OID 17193)
-- Name: Schedules; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Schedules" (
    schedules_id integer NOT NULL,
    periods_id integer
);


ALTER TABLE public."Schedules" OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 17196)
-- Name: Schedules_schedules_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Schedules_schedules_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Schedules_schedules_id_seq" OWNER TO postgres;

--
-- TOC entry 3063 (class 0 OID 0)
-- Dependencies: 222
-- Name: Schedules_schedules_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Schedules_schedules_id_seq" OWNED BY public."Schedules".schedules_id;


--
-- TOC entry 223 (class 1259 OID 17198)
-- Name: StudentsForBookings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."StudentsForBookings" (
    id integer NOT NULL,
    "classOfStudents" text,
    "bookingForstudents" integer
);


ALTER TABLE public."StudentsForBookings" OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 17204)
-- Name: StudentsForBookings_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."StudentsForBookings_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."StudentsForBookings_id_seq" OWNER TO postgres;

--
-- TOC entry 3064 (class 0 OID 0)
-- Dependencies: 224
-- Name: StudentsForBookings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."StudentsForBookings_id_seq" OWNED BY public."StudentsForBookings".id;


--
-- TOC entry 225 (class 1259 OID 17206)
-- Name: StudentsForCourses; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."StudentsForCourses" (
    student text,
    course text,
    id_students_to_courses integer NOT NULL
);


ALTER TABLE public."StudentsForCourses" OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 17212)
-- Name: StudentsForCourses_id_students_to_courses_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."StudentsForCourses_id_students_to_courses_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."StudentsForCourses_id_students_to_courses_seq" OWNER TO postgres;

--
-- TOC entry 3065 (class 0 OID 0)
-- Dependencies: 226
-- Name: StudentsForCourses_id_students_to_courses_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."StudentsForCourses_id_students_to_courses_seq" OWNED BY public."StudentsForCourses".id_students_to_courses;


--
-- TOC entry 227 (class 1259 OID 17214)
-- Name: Teachers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Teachers" (
    teachers_id text NOT NULL,
    "teachersName" text
);


ALTER TABLE public."Teachers" OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 17220)
-- Name: Users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Users" (
    "Login" text NOT NULL,
    "Password" text,
    users_teacher text,
    name text,
    "isClassScheduler" boolean
);


ALTER TABLE public."Users" OWNER TO postgres;

--
-- TOC entry 2798 (class 2604 OID 17226)
-- Name: BookedPeriodsForClassesOfStudents id_booking_students; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."BookedPeriodsForClassesOfStudents" ALTER COLUMN id_booking_students SET DEFAULT nextval('public."BookedPeriodsForClassesOfStudents_id_booking_students_seq"'::regclass);


--
-- TOC entry 2799 (class 2604 OID 17227)
-- Name: BookedPeriodsForRoom id_booked_period; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."BookedPeriodsForRoom" ALTER COLUMN id_booked_period SET DEFAULT nextval('public."BookedPeriodsForRoom_id_booked_period_seq"'::regclass);


--
-- TOC entry 2800 (class 2604 OID 17228)
-- Name: BookedPeriodsForTeachers bookings_teachers_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."BookedPeriodsForTeachers" ALTER COLUMN bookings_teachers_id SET DEFAULT nextval('public."BookedPeriodsForTeachers_bookings_teachers_id_seq"'::regclass);


--
-- TOC entry 2801 (class 2604 OID 17229)
-- Name: BookedRoomsForCourse id_bookedRoomsCourse; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."BookedRoomsForCourse" ALTER COLUMN "id_bookedRoomsCourse" SET DEFAULT nextval('public."BookedRoomsForCourse_id_bookedRoomsCourse_seq"'::regclass);


--
-- TOC entry 2802 (class 2604 OID 17230)
-- Name: Bookings bookings_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Bookings" ALTER COLUMN bookings_id SET DEFAULT nextval('public."Bookings_bookings_id_seq"'::regclass);


--
-- TOC entry 2803 (class 2604 OID 17231)
-- Name: DaysBookingPeriodes DaysBookinPeriodId; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."DaysBookingPeriodes" ALTER COLUMN "DaysBookinPeriodId" SET DEFAULT nextval('public."DaysBookingPeriodes_DaysBookinPeriodId_seq"'::regclass);


--
-- TOC entry 2804 (class 2604 OID 17232)
-- Name: NeighborAreasForRoom id_neighbors; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."NeighborAreasForRoom" ALTER COLUMN id_neighbors SET DEFAULT nextval('public."NeighborAreasForRoom_id_neighbors_seq"'::regclass);


--
-- TOC entry 2805 (class 2604 OID 17233)
-- Name: Periods periods_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Periods" ALTER COLUMN periods_id SET DEFAULT nextval('public."Periods_periods_id_seq"'::regclass);


--
-- TOC entry 2806 (class 2604 OID 17234)
-- Name: PossibleCoursesForTeacher id_of_couse_to_teacher; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."PossibleCoursesForTeacher" ALTER COLUMN id_of_couse_to_teacher SET DEFAULT nextval('public."PossibleCoursesForTeacher_id_of_couse_to_teacher_seq"'::regclass);


--
-- TOC entry 2807 (class 2604 OID 17235)
-- Name: Rooms rooms_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Rooms" ALTER COLUMN rooms_id SET DEFAULT nextval('public."Rooms_rooms_id_seq"'::regclass);


--
-- TOC entry 2808 (class 2604 OID 17236)
-- Name: RoomsAndBookings id_rooms_for_bookings; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."RoomsAndBookings" ALTER COLUMN id_rooms_for_bookings SET DEFAULT nextval('public."RoomsAndBookings_id_rooms_for_bookings_seq"'::regclass);


--
-- TOC entry 2809 (class 2604 OID 17237)
-- Name: Schedules schedules_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Schedules" ALTER COLUMN schedules_id SET DEFAULT nextval('public."Schedules_schedules_id_seq"'::regclass);


--
-- TOC entry 2810 (class 2604 OID 17238)
-- Name: StudentsForBookings id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."StudentsForBookings" ALTER COLUMN id SET DEFAULT nextval('public."StudentsForBookings_id_seq"'::regclass);


--
-- TOC entry 2811 (class 2604 OID 17239)
-- Name: StudentsForCourses id_students_to_courses; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."StudentsForCourses" ALTER COLUMN id_students_to_courses SET DEFAULT nextval('public."StudentsForCourses_id_students_to_courses_seq"'::regclass);


--
-- TOC entry 3013 (class 0 OID 17096)
-- Dependencies: 196
-- Data for Name: BookedPeriodsForClassesOfStudents; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."BookedPeriodsForClassesOfStudents" ("bookingsPeriod", "classOfStudents", id_booking_students) FROM stdin;
\.


--
-- TOC entry 3015 (class 0 OID 17104)
-- Dependencies: 198
-- Data for Name: BookedPeriodsForRoom; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."BookedPeriodsForRoom" (id_booked_period, room_for_period, period_for_booking) FROM stdin;
482	1	5888
483	1	5900
484	1	5912
485	1	5924
486	1	5936
487	1	5948
488	1	5960
489	1	5972
490	1	5996
491	1	6020
492	1	6044
493	1	6068
494	1	6092
495	1	6116
496	1	6140
497	1	6164
498	1	6188
499	1	6212
500	1	6236
501	1	6260
502	1	6284
\.


--
-- TOC entry 3017 (class 0 OID 17109)
-- Dependencies: 200
-- Data for Name: BookedPeriodsForTeachers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."BookedPeriodsForTeachers" (periods_bookings_teacher, teacher_of_booking, bookings_teachers_id) FROM stdin;
6140	teacher01	6062
6141	teacher01	6063
6142	teacher01	6064
6143	teacher01	6065
6144	teacher01	6066
6145	teacher01	6067
6146	teacher01	6068
6147	teacher01	6069
6148	teacher01	6070
6149	teacher01	6071
6150	teacher01	6072
6151	teacher01	6073
6152	teacher01	6074
6153	teacher01	6075
6154	teacher01	6076
6155	teacher01	6077
6156	teacher01	6078
6157	teacher01	6079
6158	teacher01	6080
6159	teacher01	6081
6160	teacher01	6082
6161	teacher01	6083
6162	teacher01	6084
6163	teacher01	6085
6164	teacher01	6086
6165	teacher01	6087
6166	teacher01	6088
6167	teacher01	6089
6168	teacher01	6090
6169	teacher01	6091
6170	teacher01	6092
6171	teacher01	6093
6172	teacher01	6094
6173	teacher01	6095
6174	teacher01	6096
6175	teacher01	6097
6176	teacher01	6098
6177	teacher01	6099
6178	teacher01	6100
6179	teacher01	6101
6180	teacher01	6102
6181	teacher01	6103
6182	teacher01	6104
6183	teacher01	6105
6184	teacher01	6106
6185	teacher01	6107
6186	teacher01	6108
6187	teacher01	6109
6188	teacher03	6110
6189	teacher03	6111
6190	teacher03	6112
6191	teacher03	6113
6192	teacher03	6114
6193	teacher03	6115
6194	teacher03	6116
6195	teacher03	6117
6196	teacher03	6118
6197	teacher03	6119
6198	teacher03	6120
6199	teacher03	6121
6200	teacher03	6122
6201	teacher03	6123
6202	teacher03	6124
6203	teacher03	6125
6204	teacher03	6126
6205	teacher03	6127
6206	teacher03	6128
6207	teacher03	6129
6208	teacher03	6130
6209	teacher03	6131
6210	teacher03	6132
6211	teacher03	6133
6212	teacher02	6134
6213	teacher02	6135
6214	teacher02	6136
6215	teacher02	6137
6216	teacher02	6138
6217	teacher02	6139
6218	teacher02	6140
6219	teacher02	6141
6220	teacher02	6142
6221	teacher02	6143
6222	teacher02	6144
6223	teacher02	6145
6224	teacher02	6146
6225	teacher02	6147
6226	teacher02	6148
6227	teacher02	6149
6228	teacher02	6150
6229	teacher02	6151
6230	teacher02	6152
6231	teacher02	6153
6232	teacher02	6154
6233	teacher02	6155
6234	teacher02	6156
6235	teacher02	6157
6236	teacher05	6158
6237	teacher05	6159
6238	teacher05	6160
6239	teacher05	6161
6240	teacher05	6162
6241	teacher05	6163
6242	teacher05	6164
6243	teacher05	6165
6244	teacher05	6166
6245	teacher05	6167
6246	teacher05	6168
6247	teacher05	6169
6248	teacher05	6170
6249	teacher05	6171
6250	teacher05	6172
6251	teacher05	6173
6252	teacher05	6174
6253	teacher05	6175
6254	teacher05	6176
6255	teacher05	6177
6256	teacher05	6178
6257	teacher05	6179
6258	teacher05	6180
6259	teacher05	6181
6260	teacher07	6182
6261	teacher07	6183
6262	teacher07	6184
6263	teacher07	6185
6264	teacher07	6186
6265	teacher07	6187
6266	teacher07	6188
6267	teacher07	6189
6268	teacher07	6190
6269	teacher07	6191
6270	teacher07	6192
6271	teacher07	6193
6272	teacher07	6194
6273	teacher07	6195
6274	teacher07	6196
6275	teacher07	6197
6276	teacher07	6198
6277	teacher07	6199
6278	teacher07	6200
6279	teacher07	6201
6280	teacher07	6202
6281	teacher07	6203
6282	teacher07	6204
6283	teacher07	6205
6284	teacher08	6206
6285	teacher08	6207
6286	teacher08	6208
6287	teacher08	6209
6288	teacher08	6210
6289	teacher08	6211
6290	teacher08	6212
6291	teacher08	6213
6292	teacher08	6214
6293	teacher08	6215
6294	teacher08	6216
6295	teacher08	6217
6296	teacher08	6218
6297	teacher08	6219
6298	teacher08	6220
6299	teacher08	6221
6300	teacher08	6222
6301	teacher08	6223
6302	teacher08	6224
6303	teacher08	6225
6304	teacher08	6226
6305	teacher08	6227
6306	teacher08	6228
6307	teacher08	6229
5888	jan001	5810
5889	jan001	5811
5890	jan001	5812
5891	jan001	5813
5892	jan001	5814
5893	jan001	5815
5894	jan001	5816
5895	jan001	5817
5896	jan001	5818
5897	jan001	5819
5898	jan001	5820
5899	jan001	5821
5900	jan001	5822
5901	jan001	5823
5902	jan001	5824
5903	jan001	5825
5904	jan001	5826
5905	jan001	5827
5906	jan001	5828
5907	jan001	5829
5908	jan001	5830
5909	jan001	5831
5910	jan001	5832
5911	jan001	5833
5912	teacher04	5834
5913	teacher04	5835
5914	teacher04	5836
5915	teacher04	5837
5916	teacher04	5838
5917	teacher04	5839
5918	teacher04	5840
5919	teacher04	5841
5920	teacher04	5842
5921	teacher04	5843
5922	teacher04	5844
5923	teacher04	5845
5924	teacher06	5846
5925	teacher06	5847
5926	teacher06	5848
5927	teacher06	5849
5928	teacher06	5850
5929	teacher06	5851
5930	teacher06	5852
5931	teacher06	5853
5932	teacher06	5854
5933	teacher06	5855
5934	teacher06	5856
5935	teacher06	5857
5936	teacher08	5858
5937	teacher08	5859
5938	teacher08	5860
5939	teacher08	5861
5940	teacher08	5862
5941	teacher08	5863
5942	teacher08	5864
5943	teacher08	5865
5944	teacher08	5866
5945	teacher08	5867
5946	teacher08	5868
5947	teacher08	5869
5948	teacher07	5870
5949	teacher07	5871
5950	teacher07	5872
5951	teacher07	5873
5952	teacher07	5874
5953	teacher07	5875
5954	teacher07	5876
5955	teacher07	5877
5956	teacher07	5878
5957	teacher07	5879
5958	teacher07	5880
5959	teacher07	5881
5960	teacher09	5882
5961	teacher09	5883
5962	teacher09	5884
5963	teacher09	5885
5964	teacher09	5886
5965	teacher09	5887
5966	teacher09	5888
5967	teacher09	5889
5968	teacher09	5890
5969	teacher09	5891
5970	teacher09	5892
5971	teacher09	5893
5972	teacher01	5894
5973	teacher01	5895
5974	teacher01	5896
5975	teacher01	5897
5976	teacher01	5898
5977	teacher01	5899
5978	teacher01	5900
5979	teacher01	5901
5980	teacher01	5902
5981	teacher01	5903
5982	teacher01	5904
5983	teacher01	5905
5984	teacher01	5906
5985	teacher01	5907
5986	teacher01	5908
5987	teacher01	5909
5988	teacher01	5910
5989	teacher01	5911
5990	teacher01	5912
5991	teacher01	5913
5992	teacher01	5914
5993	teacher01	5915
5994	teacher01	5916
5995	teacher01	5917
5996	teacher01	5918
5997	teacher01	5919
5998	teacher01	5920
5999	teacher01	5921
6000	teacher01	5922
6001	teacher01	5923
6002	teacher01	5924
6003	teacher01	5925
6004	teacher01	5926
6005	teacher01	5927
6006	teacher01	5928
6007	teacher01	5929
6008	teacher01	5930
6009	teacher01	5931
6010	teacher01	5932
6011	teacher01	5933
6012	teacher01	5934
6013	teacher01	5935
6014	teacher01	5936
6015	teacher01	5937
6016	teacher01	5938
6017	teacher01	5939
6018	teacher01	5940
6019	teacher01	5941
6020	teacher02	5942
6021	teacher02	5943
6022	teacher02	5944
6023	teacher02	5945
6024	teacher02	5946
6025	teacher02	5947
6026	teacher02	5948
6027	teacher02	5949
6028	teacher02	5950
6029	teacher02	5951
6030	teacher02	5952
6031	teacher02	5953
6032	teacher02	5954
6033	teacher02	5955
6034	teacher02	5956
6035	teacher02	5957
6036	teacher02	5958
6037	teacher02	5959
6038	teacher02	5960
6039	teacher02	5961
6040	teacher02	5962
6041	teacher02	5963
6042	teacher02	5964
6043	teacher02	5965
6044	teacher04	5966
6045	teacher04	5967
6046	teacher04	5968
6047	teacher04	5969
6048	teacher04	5970
6049	teacher04	5971
6050	teacher04	5972
6051	teacher04	5973
6052	teacher04	5974
6053	teacher04	5975
6054	teacher04	5976
6055	teacher04	5977
6056	teacher04	5978
6057	teacher04	5979
6058	teacher04	5980
6059	teacher04	5981
6060	teacher04	5982
6061	teacher04	5983
6062	teacher04	5984
6063	teacher04	5985
6064	teacher04	5986
6065	teacher04	5987
6066	teacher04	5988
6067	teacher04	5989
6068	teacher07	5990
6069	teacher07	5991
6070	teacher07	5992
6071	teacher07	5993
6072	teacher07	5994
6073	teacher07	5995
6074	teacher07	5996
6075	teacher07	5997
6076	teacher07	5998
6077	teacher07	5999
6078	teacher07	6000
6079	teacher07	6001
6080	teacher07	6002
6081	teacher07	6003
6082	teacher07	6004
6083	teacher07	6005
6084	teacher07	6006
6085	teacher07	6007
6086	teacher07	6008
6087	teacher07	6009
6088	teacher07	6010
6089	teacher07	6011
6090	teacher07	6012
6091	teacher07	6013
6092	teacher09	6014
6093	teacher09	6015
6094	teacher09	6016
6095	teacher09	6017
6096	teacher09	6018
6097	teacher09	6019
6098	teacher09	6020
6099	teacher09	6021
6100	teacher09	6022
6101	teacher09	6023
6102	teacher09	6024
6103	teacher09	6025
6104	teacher09	6026
6105	teacher09	6027
6106	teacher09	6028
6107	teacher09	6029
6108	teacher09	6030
6109	teacher09	6031
6110	teacher09	6032
6111	teacher09	6033
6112	teacher09	6034
6113	teacher09	6035
6114	teacher09	6036
6115	teacher09	6037
6116	teacher05	6038
6117	teacher05	6039
6118	teacher05	6040
6119	teacher05	6041
6120	teacher05	6042
6121	teacher05	6043
6122	teacher05	6044
6123	teacher05	6045
6124	teacher05	6046
6125	teacher05	6047
6126	teacher05	6048
6127	teacher05	6049
6128	teacher05	6050
6129	teacher05	6051
6130	teacher05	6052
6131	teacher05	6053
6132	teacher05	6054
6133	teacher05	6055
6134	teacher05	6056
6135	teacher05	6057
6136	teacher05	6058
6137	teacher05	6059
6138	teacher05	6060
6139	teacher05	6061
\.


--
-- TOC entry 3019 (class 0 OID 17117)
-- Dependencies: 202
-- Data for Name: BookedRoomsForCourse; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."BookedRoomsForCourse" ("courseInRoom", "id_bookedRoomsCourse", fk_room) FROM stdin;
LAB	482	1
PM	483	1
cid-248	484	1
cid-83	485	1
cid-100	486	1
cid-127	487	1
cid-54	488	1
cid-136	489	1
cid-140	490	1
cid-58	491	1
cid-60	492	1
cid-208	493	1
cid-34	494	1
cid-97	495	1
cid-144	496	1
cid-145	497	1
cid-146	498	1
cid-229	499	1
cid-37	500	1
cid-71	501	1
cid-214	502	1
\.


--
-- TOC entry 3021 (class 0 OID 17125)
-- Dependencies: 204
-- Data for Name: Bookings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Bookings" (teacher_for_booking, course_for_booking, bookings_id, period_for_booking, "studentsForBooking", schedules_id) FROM stdin;
teacher01	cid-144	6062	6140	\N	\N
teacher01	cid-144	6063	6141	\N	\N
teacher01	cid-144	6064	6142	\N	\N
teacher01	cid-144	6065	6143	\N	\N
teacher01	cid-144	6066	6144	\N	\N
teacher01	cid-144	6067	6145	\N	\N
teacher01	cid-144	6068	6146	\N	\N
teacher01	cid-144	6069	6147	\N	\N
teacher01	cid-144	6070	6148	\N	\N
teacher01	cid-144	6071	6149	\N	\N
teacher01	cid-144	6072	6150	\N	\N
teacher01	cid-144	6073	6151	\N	\N
teacher01	cid-144	6074	6152	\N	\N
teacher01	cid-144	6075	6153	\N	\N
teacher01	cid-144	6076	6154	\N	\N
teacher01	cid-144	6077	6155	\N	\N
teacher01	cid-144	6078	6156	\N	\N
teacher01	cid-144	6079	6157	\N	\N
teacher01	cid-144	6080	6158	\N	\N
teacher01	cid-144	6081	6159	\N	\N
teacher01	cid-144	6082	6160	\N	\N
teacher01	cid-144	6083	6161	\N	\N
teacher01	cid-144	6084	6162	\N	\N
teacher01	cid-144	6085	6163	\N	\N
teacher01	cid-145	6086	6164	\N	\N
teacher01	cid-145	6087	6165	\N	\N
teacher01	cid-145	6088	6166	\N	\N
teacher01	cid-145	6089	6167	\N	\N
teacher01	cid-145	6090	6168	\N	\N
teacher01	cid-145	6091	6169	\N	\N
teacher01	cid-145	6092	6170	\N	\N
teacher01	cid-145	6093	6171	\N	\N
teacher01	cid-145	6094	6172	\N	\N
teacher01	cid-145	6095	6173	\N	\N
teacher01	cid-145	6096	6174	\N	\N
teacher01	cid-145	6097	6175	\N	\N
teacher01	cid-145	6098	6176	\N	\N
teacher01	cid-145	6099	6177	\N	\N
teacher01	cid-145	6100	6178	\N	\N
teacher01	cid-145	6101	6179	\N	\N
teacher01	cid-145	6102	6180	\N	\N
teacher01	cid-145	6103	6181	\N	\N
teacher01	cid-145	6104	6182	\N	\N
teacher01	cid-145	6105	6183	\N	\N
teacher01	cid-145	6106	6184	\N	\N
teacher01	cid-145	6107	6185	\N	\N
teacher01	cid-145	6108	6186	\N	\N
teacher01	cid-145	6109	6187	\N	\N
teacher03	cid-146	6110	6188	\N	\N
teacher03	cid-146	6111	6189	\N	\N
teacher03	cid-146	6112	6190	\N	\N
teacher03	cid-146	6113	6191	\N	\N
teacher03	cid-146	6114	6192	\N	\N
teacher03	cid-146	6115	6193	\N	\N
teacher03	cid-146	6116	6194	\N	\N
teacher03	cid-146	6117	6195	\N	\N
teacher03	cid-146	6118	6196	\N	\N
teacher03	cid-146	6119	6197	\N	\N
teacher03	cid-146	6120	6198	\N	\N
teacher03	cid-146	6121	6199	\N	\N
teacher03	cid-146	6122	6200	\N	\N
teacher03	cid-146	6123	6201	\N	\N
teacher03	cid-146	6124	6202	\N	\N
teacher03	cid-146	6125	6203	\N	\N
teacher03	cid-146	6126	6204	\N	\N
teacher03	cid-146	6127	6205	\N	\N
teacher03	cid-146	6128	6206	\N	\N
teacher03	cid-146	6129	6207	\N	\N
teacher03	cid-146	6130	6208	\N	\N
teacher03	cid-146	6131	6209	\N	\N
teacher03	cid-146	6132	6210	\N	\N
teacher03	cid-146	6133	6211	\N	\N
teacher02	cid-229	6134	6212	\N	\N
teacher02	cid-229	6135	6213	\N	\N
teacher02	cid-229	6136	6214	\N	\N
teacher02	cid-229	6137	6215	\N	\N
teacher02	cid-229	6138	6216	\N	\N
teacher02	cid-229	6139	6217	\N	\N
teacher02	cid-229	6140	6218	\N	\N
teacher02	cid-229	6141	6219	\N	\N
teacher02	cid-229	6142	6220	\N	\N
teacher02	cid-229	6143	6221	\N	\N
teacher02	cid-229	6144	6222	\N	\N
teacher02	cid-229	6145	6223	\N	\N
teacher02	cid-229	6146	6224	\N	\N
teacher02	cid-229	6147	6225	\N	\N
teacher02	cid-229	6148	6226	\N	\N
teacher02	cid-229	6149	6227	\N	\N
teacher02	cid-229	6150	6228	\N	\N
teacher02	cid-229	6151	6229	\N	\N
teacher02	cid-229	6152	6230	\N	\N
teacher02	cid-229	6153	6231	\N	\N
teacher02	cid-229	6154	6232	\N	\N
teacher02	cid-229	6155	6233	\N	\N
teacher02	cid-229	6156	6234	\N	\N
teacher02	cid-229	6157	6235	\N	\N
teacher05	cid-37	6158	6236	\N	\N
teacher05	cid-37	6159	6237	\N	\N
teacher05	cid-37	6160	6238	\N	\N
teacher05	cid-37	6161	6239	\N	\N
teacher05	cid-37	6162	6240	\N	\N
teacher05	cid-37	6163	6241	\N	\N
teacher05	cid-37	6164	6242	\N	\N
teacher05	cid-37	6165	6243	\N	\N
teacher05	cid-37	6166	6244	\N	\N
teacher05	cid-37	6167	6245	\N	\N
teacher05	cid-37	6168	6246	\N	\N
teacher05	cid-37	6169	6247	\N	\N
teacher05	cid-37	6170	6248	\N	\N
teacher05	cid-37	6171	6249	\N	\N
teacher05	cid-37	6172	6250	\N	\N
teacher05	cid-37	6173	6251	\N	\N
teacher05	cid-37	6174	6252	\N	\N
teacher05	cid-37	6175	6253	\N	\N
teacher05	cid-37	6176	6254	\N	\N
teacher05	cid-37	6177	6255	\N	\N
teacher05	cid-37	6178	6256	\N	\N
teacher05	cid-37	6179	6257	\N	\N
teacher05	cid-37	6180	6258	\N	\N
teacher05	cid-37	6181	6259	\N	\N
teacher07	cid-71	6182	6260	\N	\N
teacher07	cid-71	6183	6261	\N	\N
teacher07	cid-71	6184	6262	\N	\N
teacher07	cid-71	6185	6263	\N	\N
teacher07	cid-71	6186	6264	\N	\N
teacher07	cid-71	6187	6265	\N	\N
teacher07	cid-71	6188	6266	\N	\N
teacher07	cid-71	6189	6267	\N	\N
teacher07	cid-71	6190	6268	\N	\N
teacher07	cid-71	6191	6269	\N	\N
teacher07	cid-71	6192	6270	\N	\N
teacher07	cid-71	6193	6271	\N	\N
teacher07	cid-71	6194	6272	\N	\N
teacher07	cid-71	6195	6273	\N	\N
teacher07	cid-71	6196	6274	\N	\N
teacher07	cid-71	6197	6275	\N	\N
teacher07	cid-71	6198	6276	\N	\N
teacher07	cid-71	6199	6277	\N	\N
teacher07	cid-71	6200	6278	\N	\N
teacher07	cid-71	6201	6279	\N	\N
teacher07	cid-71	6202	6280	\N	\N
teacher07	cid-71	6203	6281	\N	\N
teacher07	cid-71	6204	6282	\N	\N
teacher07	cid-71	6205	6283	\N	\N
teacher08	cid-214	6206	6284	\N	\N
teacher08	cid-214	6207	6285	\N	\N
teacher08	cid-214	6208	6286	\N	\N
teacher08	cid-214	6209	6287	\N	\N
teacher08	cid-214	6210	6288	\N	\N
teacher08	cid-214	6211	6289	\N	\N
teacher08	cid-214	6212	6290	\N	\N
teacher08	cid-214	6213	6291	\N	\N
teacher08	cid-214	6214	6292	\N	\N
teacher08	cid-214	6215	6293	\N	\N
teacher08	cid-214	6216	6294	\N	\N
teacher08	cid-214	6217	6295	\N	\N
teacher08	cid-214	6218	6296	\N	\N
teacher08	cid-214	6219	6297	\N	\N
teacher08	cid-214	6220	6298	\N	\N
teacher08	cid-214	6221	6299	\N	\N
teacher08	cid-214	6222	6300	\N	\N
teacher08	cid-214	6223	6301	\N	\N
teacher08	cid-214	6224	6302	\N	\N
teacher08	cid-214	6225	6303	\N	\N
teacher08	cid-214	6226	6304	\N	\N
teacher08	cid-214	6227	6305	\N	\N
teacher08	cid-214	6228	6306	\N	\N
teacher08	cid-214	6229	6307	\N	\N
jan001	LAB	5810	5888	\N	\N
jan001	LAB	5811	5889	\N	\N
jan001	LAB	5812	5890	\N	\N
jan001	LAB	5813	5891	\N	\N
jan001	LAB	5814	5892	\N	\N
jan001	LAB	5815	5893	\N	\N
jan001	LAB	5816	5894	\N	\N
jan001	LAB	5817	5895	\N	\N
jan001	LAB	5818	5896	\N	\N
jan001	LAB	5819	5897	\N	\N
jan001	LAB	5820	5898	\N	\N
jan001	LAB	5821	5899	\N	\N
jan001	PM	5822	5900	\N	\N
jan001	PM	5823	5901	\N	\N
jan001	PM	5824	5902	\N	\N
jan001	PM	5825	5903	\N	\N
jan001	PM	5826	5904	\N	\N
jan001	PM	5827	5905	\N	\N
jan001	PM	5828	5906	\N	\N
jan001	PM	5829	5907	\N	\N
jan001	PM	5830	5908	\N	\N
jan001	PM	5831	5909	\N	\N
jan001	PM	5832	5910	\N	\N
jan001	PM	5833	5911	\N	\N
teacher04	cid-248	5834	5912	\N	\N
teacher04	cid-248	5835	5913	\N	\N
teacher04	cid-248	5836	5914	\N	\N
teacher04	cid-248	5837	5915	\N	\N
teacher04	cid-248	5838	5916	\N	\N
teacher04	cid-248	5839	5917	\N	\N
teacher04	cid-248	5840	5918	\N	\N
teacher04	cid-248	5841	5919	\N	\N
teacher04	cid-248	5842	5920	\N	\N
teacher04	cid-248	5843	5921	\N	\N
teacher04	cid-248	5844	5922	\N	\N
teacher04	cid-248	5845	5923	\N	\N
teacher06	cid-83	5846	5924	\N	\N
teacher06	cid-83	5847	5925	\N	\N
teacher06	cid-83	5848	5926	\N	\N
teacher06	cid-83	5849	5927	\N	\N
teacher06	cid-83	5850	5928	\N	\N
teacher06	cid-83	5851	5929	\N	\N
teacher06	cid-83	5852	5930	\N	\N
teacher06	cid-83	5853	5931	\N	\N
teacher06	cid-83	5854	5932	\N	\N
teacher06	cid-83	5855	5933	\N	\N
teacher06	cid-83	5856	5934	\N	\N
teacher06	cid-83	5857	5935	\N	\N
teacher08	cid-100	5858	5936	\N	\N
teacher08	cid-100	5859	5937	\N	\N
teacher08	cid-100	5860	5938	\N	\N
teacher08	cid-100	5861	5939	\N	\N
teacher08	cid-100	5862	5940	\N	\N
teacher08	cid-100	5863	5941	\N	\N
teacher08	cid-100	5864	5942	\N	\N
teacher08	cid-100	5865	5943	\N	\N
teacher08	cid-100	5866	5944	\N	\N
teacher08	cid-100	5867	5945	\N	\N
teacher08	cid-100	5868	5946	\N	\N
teacher08	cid-100	5869	5947	\N	\N
teacher07	cid-127	5870	5948	\N	\N
teacher07	cid-127	5871	5949	\N	\N
teacher07	cid-127	5872	5950	\N	\N
teacher07	cid-127	5873	5951	\N	\N
teacher07	cid-127	5874	5952	\N	\N
teacher07	cid-127	5875	5953	\N	\N
teacher07	cid-127	5876	5954	\N	\N
teacher07	cid-127	5877	5955	\N	\N
teacher07	cid-127	5878	5956	\N	\N
teacher07	cid-127	5879	5957	\N	\N
teacher07	cid-127	5880	5958	\N	\N
teacher07	cid-127	5881	5959	\N	\N
teacher09	cid-54	5882	5960	\N	\N
teacher09	cid-54	5883	5961	\N	\N
teacher09	cid-54	5884	5962	\N	\N
teacher09	cid-54	5885	5963	\N	\N
teacher09	cid-54	5886	5964	\N	\N
teacher09	cid-54	5887	5965	\N	\N
teacher09	cid-54	5888	5966	\N	\N
teacher09	cid-54	5889	5967	\N	\N
teacher09	cid-54	5890	5968	\N	\N
teacher09	cid-54	5891	5969	\N	\N
teacher09	cid-54	5892	5970	\N	\N
teacher09	cid-54	5893	5971	\N	\N
teacher01	cid-136	5894	5972	\N	\N
teacher01	cid-136	5895	5973	\N	\N
teacher01	cid-136	5896	5974	\N	\N
teacher01	cid-136	5897	5975	\N	\N
teacher01	cid-136	5898	5976	\N	\N
teacher01	cid-136	5899	5977	\N	\N
teacher01	cid-136	5900	5978	\N	\N
teacher01	cid-136	5901	5979	\N	\N
teacher01	cid-136	5902	5980	\N	\N
teacher01	cid-136	5903	5981	\N	\N
teacher01	cid-136	5904	5982	\N	\N
teacher01	cid-136	5905	5983	\N	\N
teacher01	cid-136	5906	5984	\N	\N
teacher01	cid-136	5907	5985	\N	\N
teacher01	cid-136	5908	5986	\N	\N
teacher01	cid-136	5909	5987	\N	\N
teacher01	cid-136	5910	5988	\N	\N
teacher01	cid-136	5911	5989	\N	\N
teacher01	cid-136	5912	5990	\N	\N
teacher01	cid-136	5913	5991	\N	\N
teacher01	cid-136	5914	5992	\N	\N
teacher01	cid-136	5915	5993	\N	\N
teacher01	cid-136	5916	5994	\N	\N
teacher01	cid-136	5917	5995	\N	\N
teacher01	cid-140	5918	5996	\N	\N
teacher01	cid-140	5919	5997	\N	\N
teacher01	cid-140	5920	5998	\N	\N
teacher01	cid-140	5921	5999	\N	\N
teacher01	cid-140	5922	6000	\N	\N
teacher01	cid-140	5923	6001	\N	\N
teacher01	cid-140	5924	6002	\N	\N
teacher01	cid-140	5925	6003	\N	\N
teacher01	cid-140	5926	6004	\N	\N
teacher01	cid-140	5927	6005	\N	\N
teacher01	cid-140	5928	6006	\N	\N
teacher01	cid-140	5929	6007	\N	\N
teacher01	cid-140	5930	6008	\N	\N
teacher01	cid-140	5931	6009	\N	\N
teacher01	cid-140	5932	6010	\N	\N
teacher01	cid-140	5933	6011	\N	\N
teacher01	cid-140	5934	6012	\N	\N
teacher01	cid-140	5935	6013	\N	\N
teacher01	cid-140	5936	6014	\N	\N
teacher01	cid-140	5937	6015	\N	\N
teacher01	cid-140	5938	6016	\N	\N
teacher01	cid-140	5939	6017	\N	\N
teacher01	cid-140	5940	6018	\N	\N
teacher01	cid-140	5941	6019	\N	\N
teacher02	cid-58	5942	6020	\N	\N
teacher02	cid-58	5943	6021	\N	\N
teacher02	cid-58	5944	6022	\N	\N
teacher02	cid-58	5945	6023	\N	\N
teacher02	cid-58	5946	6024	\N	\N
teacher02	cid-58	5947	6025	\N	\N
teacher02	cid-58	5948	6026	\N	\N
teacher02	cid-58	5949	6027	\N	\N
teacher02	cid-58	5950	6028	\N	\N
teacher02	cid-58	5951	6029	\N	\N
teacher02	cid-58	5952	6030	\N	\N
teacher02	cid-58	5953	6031	\N	\N
teacher02	cid-58	5954	6032	\N	\N
teacher02	cid-58	5955	6033	\N	\N
teacher02	cid-58	5956	6034	\N	\N
teacher02	cid-58	5957	6035	\N	\N
teacher02	cid-58	5958	6036	\N	\N
teacher02	cid-58	5959	6037	\N	\N
teacher02	cid-58	5960	6038	\N	\N
teacher02	cid-58	5961	6039	\N	\N
teacher02	cid-58	5962	6040	\N	\N
teacher02	cid-58	5963	6041	\N	\N
teacher02	cid-58	5964	6042	\N	\N
teacher02	cid-58	5965	6043	\N	\N
teacher04	cid-60	5966	6044	\N	\N
teacher04	cid-60	5967	6045	\N	\N
teacher04	cid-60	5968	6046	\N	\N
teacher04	cid-60	5969	6047	\N	\N
teacher04	cid-60	5970	6048	\N	\N
teacher04	cid-60	5971	6049	\N	\N
teacher04	cid-60	5972	6050	\N	\N
teacher04	cid-60	5973	6051	\N	\N
teacher04	cid-60	5974	6052	\N	\N
teacher04	cid-60	5975	6053	\N	\N
teacher04	cid-60	5976	6054	\N	\N
teacher04	cid-60	5977	6055	\N	\N
teacher04	cid-60	5978	6056	\N	\N
teacher04	cid-60	5979	6057	\N	\N
teacher04	cid-60	5980	6058	\N	\N
teacher04	cid-60	5981	6059	\N	\N
teacher04	cid-60	5982	6060	\N	\N
teacher04	cid-60	5983	6061	\N	\N
teacher04	cid-60	5984	6062	\N	\N
teacher04	cid-60	5985	6063	\N	\N
teacher04	cid-60	5986	6064	\N	\N
teacher04	cid-60	5987	6065	\N	\N
teacher04	cid-60	5988	6066	\N	\N
teacher04	cid-60	5989	6067	\N	\N
teacher07	cid-208	5990	6068	\N	\N
teacher07	cid-208	5991	6069	\N	\N
teacher07	cid-208	5992	6070	\N	\N
teacher07	cid-208	5993	6071	\N	\N
teacher07	cid-208	5994	6072	\N	\N
teacher07	cid-208	5995	6073	\N	\N
teacher07	cid-208	5996	6074	\N	\N
teacher07	cid-208	5997	6075	\N	\N
teacher07	cid-208	5998	6076	\N	\N
teacher07	cid-208	5999	6077	\N	\N
teacher07	cid-208	6000	6078	\N	\N
teacher07	cid-208	6001	6079	\N	\N
teacher07	cid-208	6002	6080	\N	\N
teacher07	cid-208	6003	6081	\N	\N
teacher07	cid-208	6004	6082	\N	\N
teacher07	cid-208	6005	6083	\N	\N
teacher07	cid-208	6006	6084	\N	\N
teacher07	cid-208	6007	6085	\N	\N
teacher07	cid-208	6008	6086	\N	\N
teacher07	cid-208	6009	6087	\N	\N
teacher07	cid-208	6010	6088	\N	\N
teacher07	cid-208	6011	6089	\N	\N
teacher07	cid-208	6012	6090	\N	\N
teacher07	cid-208	6013	6091	\N	\N
teacher09	cid-34	6014	6092	\N	\N
teacher09	cid-34	6015	6093	\N	\N
teacher09	cid-34	6016	6094	\N	\N
teacher09	cid-34	6017	6095	\N	\N
teacher09	cid-34	6018	6096	\N	\N
teacher09	cid-34	6019	6097	\N	\N
teacher09	cid-34	6020	6098	\N	\N
teacher09	cid-34	6021	6099	\N	\N
teacher09	cid-34	6022	6100	\N	\N
teacher09	cid-34	6023	6101	\N	\N
teacher09	cid-34	6024	6102	\N	\N
teacher09	cid-34	6025	6103	\N	\N
teacher09	cid-34	6026	6104	\N	\N
teacher09	cid-34	6027	6105	\N	\N
teacher09	cid-34	6028	6106	\N	\N
teacher09	cid-34	6029	6107	\N	\N
teacher09	cid-34	6030	6108	\N	\N
teacher09	cid-34	6031	6109	\N	\N
teacher09	cid-34	6032	6110	\N	\N
teacher09	cid-34	6033	6111	\N	\N
teacher09	cid-34	6034	6112	\N	\N
teacher09	cid-34	6035	6113	\N	\N
teacher09	cid-34	6036	6114	\N	\N
teacher09	cid-34	6037	6115	\N	\N
teacher05	cid-97	6038	6116	\N	\N
teacher05	cid-97	6039	6117	\N	\N
teacher05	cid-97	6040	6118	\N	\N
teacher05	cid-97	6041	6119	\N	\N
teacher05	cid-97	6042	6120	\N	\N
teacher05	cid-97	6043	6121	\N	\N
teacher05	cid-97	6044	6122	\N	\N
teacher05	cid-97	6045	6123	\N	\N
teacher05	cid-97	6046	6124	\N	\N
teacher05	cid-97	6047	6125	\N	\N
teacher05	cid-97	6048	6126	\N	\N
teacher05	cid-97	6049	6127	\N	\N
teacher05	cid-97	6050	6128	\N	\N
teacher05	cid-97	6051	6129	\N	\N
teacher05	cid-97	6052	6130	\N	\N
teacher05	cid-97	6053	6131	\N	\N
teacher05	cid-97	6054	6132	\N	\N
teacher05	cid-97	6055	6133	\N	\N
teacher05	cid-97	6056	6134	\N	\N
teacher05	cid-97	6057	6135	\N	\N
teacher05	cid-97	6058	6136	\N	\N
teacher05	cid-97	6059	6137	\N	\N
teacher05	cid-97	6060	6138	\N	\N
teacher05	cid-97	6061	6139	\N	\N
\.


--
-- TOC entry 3023 (class 0 OID 17133)
-- Dependencies: 206
-- Data for Name: ClassesOfStudents; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."ClassesOfStudents" (field_of_study, "numberOfMembers", classes_id) FROM stdin;
Software Enginering	100	SE
Software Teknologi	30	IT
KemiTeknologi	40	Kemi
fieldofstudy-169	89	cid-136
fieldofstudy-240	218	cid-144
fieldofstudy-240	26	cid-140
fieldofstudy-87	89	cid-145
fieldofstudy-166	209	cid-58
fieldofstudy-223	138	cid-146
fieldofstudy-50	114	cid-229
fieldofstudy-163	240	cid-60
fieldofstudy-52	107	cid-37
fieldofstudy-163	222	cid-248
fieldofstudy-76	141	cid-83
fieldofstudy-64	118	cid-208
fieldofstudy-170	61	cid-100
fieldofstudy-69	217	cid-71
fieldofstudy-246	158	cid-214
fieldofstudy-36	97	cid-34
fieldofstudy-207	44	cid-111
fieldofstudy-65	188	cid-97
fieldofstudy-80	167	cid-127
fieldofstudy-237	88	cid-54
\.


--
-- TOC entry 3024 (class 0 OID 17139)
-- Dependencies: 207
-- Data for Name: Courses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Courses" ("courseName", "numberOfParticipants", "numberOfLessons", "numberOfHoursTogether", "maximalTimesOfWeek", "desiredDaysBetweenLectures", courses_id, assigned_teacher) FROM stdin;
Laboratorie	40	48	4	1	4	LAB	jan001
Project management	130	48	4	1	7	PM	jan001
course-245	221	48	4	2	2	cid-248	teacher04
course-192	217	48	4	2	2	cid-83	teacher06
course-65	190	48	4	1	2	cid-100	teacher08
course-57	106	48	4	2	1	cid-127	teacher07
course-126	32	48	4	1	2	cid-54	teacher09
course-144	101	48	2	1	1	cid-136	teacher01
course-103	127	48	2	2	1	cid-140	teacher01
course-59	145	48	2	1	2	cid-58	teacher02
course-146	101	48	2	1	2	cid-60	teacher04
course-132	185	48	2	1	2	cid-208	teacher07
course-240	130	48	2	1	2	cid-34	teacher09
course-189	56	48	2	2	2	cid-97	teacher05
Software Maintance	130	48	2	1	7	SM	jan001
course-213	131	48	2	1	2	cid-144	teacher01
course-128	121	48	2	1	1	cid-145	teacher01
course-206	230	48	2	2	2	cid-146	teacher03
course-169	79	48	2	1	2	cid-229	teacher02
course-80	85	48	2	2	2	cid-37	teacher05
course-115	165	48	2	1	1	cid-71	teacher07
course-47	187	48	2	1	2	cid-214	teacher08
course-220	187	48	2	2	1	cid-111	teacher02
\.


--
-- TOC entry 3025 (class 0 OID 17145)
-- Dependencies: 208
-- Data for Name: Days; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Days" ("dateOfDay", "isHollyDay", "bestPeriod", "mediumPeriod", "emergencyPeriod") FROM stdin;
\.


--
-- TOC entry 3026 (class 0 OID 17151)
-- Dependencies: 209
-- Data for Name: DaysBookingPeriodes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."DaysBookingPeriodes" ("DaysBookinPeriodId", daysbookinperiodename, starthour, endhour, day) FROM stdin;
2	best	08:00:00	16:00:00	tuesday
4	best	08:00:00	16:00:00	thursday
3	best	08:00:00	16:00:00	wednesday
5	best	08:00:00	16:00:00	friday
9	medium	16:00:00	18:00:00	tuesday
10	medium	16:00:00	18:00:00	wednesday
11	medium	16:00:00	18:00:00	thursday
12	medium	16:00:00	18:00:00	friday
14	medium	\N	\N	sunday
6	best	\N	\N	saturday
7	best	\N	\N	sunday
13	medium	\N	\N	saturday
16	emergency	18:00:00	20:00:00	tuesday
17	emergency	18:00:00	20:00:00	wednesday
18	emergency	18:00:00	20:00:00	thursday
19	emergency	18:00:00	20:00:00	friday
20	emergency	08:00:00	16:00:00	saturday
21	emergency	\N	\N	sunday
8	medium	06:00:00	07:00:00	monday
1	best	08:00:00	16:00:00	monday
15	emergency	18:00:00	20:00:00	monday
\.


--
-- TOC entry 3028 (class 0 OID 17159)
-- Dependencies: 211
-- Data for Name: NeighborAreasForRoom; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."NeighborAreasForRoom" (areas_for_room, id_neighbors, room_for_areas) FROM stdin;
HovedIndgang	1	1
HovedIndgang	2	2
HovedIndgang	3	3
HovedIndgang	4	4
\.


--
-- TOC entry 3030 (class 0 OID 17167)
-- Dependencies: 213
-- Data for Name: Periods; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Periods" ("StartDate", "FinishDate", periods_id) FROM stdin;
2019-08-02 14:00:01	2019-08-02 16:00:01	6140
2019-08-07 14:00:01	2019-08-07 16:00:01	6141
2019-08-14 14:00:01	2019-08-14 16:00:01	6142
2019-08-21 14:00:01	2019-08-21 16:00:01	6143
2019-08-28 14:00:01	2019-08-28 16:00:01	6144
2019-09-04 14:00:01	2019-09-04 16:00:01	6145
2019-09-10 14:00:01	2019-09-10 16:00:01	6146
2019-09-13 14:00:01	2019-09-13 16:00:01	6147
2019-09-17 10:00:01	2019-09-17 12:00:01	6148
2019-09-20 10:00:01	2019-09-20 12:00:01	6149
2019-09-24 10:00:01	2019-09-24 12:00:01	6150
2019-09-27 10:00:01	2019-09-27 12:00:01	6151
2019-10-01 10:00:01	2019-10-01 12:00:01	6152
2019-10-04 10:00:01	2019-10-04 12:00:01	6153
2019-10-08 10:00:01	2019-10-08 12:00:01	6154
2019-10-11 10:00:01	2019-10-11 12:00:01	6155
2019-10-15 10:00:01	2019-10-15 12:00:01	6156
2019-10-18 10:00:01	2019-10-18 12:00:01	6157
2019-10-21 14:00:01	2019-10-21 16:00:01	6158
2019-10-24 14:00:01	2019-10-24 16:00:01	6159
2019-10-28 14:00:01	2019-10-28 16:00:01	6160
2019-10-31 14:00:01	2019-10-31 16:00:01	6161
2019-11-04 10:00:01	2019-11-04 12:00:01	6162
2019-11-07 10:00:01	2019-11-07 12:00:01	6163
2019-08-15 08:00:01	2019-08-15 10:00:01	6164
2019-08-22 08:00:01	2019-08-22 10:00:01	6165
2019-08-29 08:00:01	2019-08-29 10:00:01	6166
2019-09-05 12:00:01	2019-09-05 14:00:01	6167
2019-09-11 08:00:01	2019-09-11 10:00:01	6168
2019-09-17 12:00:01	2019-09-17 14:00:01	6169
2019-09-20 12:00:01	2019-09-20 14:00:01	6170
2019-09-24 12:00:01	2019-09-24 14:00:01	6171
2019-09-27 12:00:01	2019-09-27 14:00:01	6172
2019-10-01 12:00:01	2019-10-01 14:00:01	6173
2019-10-04 12:00:01	2019-10-04 14:00:01	6174
2019-10-08 12:00:01	2019-10-08 14:00:01	6175
2019-10-11 12:00:01	2019-10-11 14:00:01	6176
2019-10-15 12:00:01	2019-10-15 14:00:01	6177
2019-10-18 12:00:01	2019-10-18 14:00:01	6178
2019-10-22 08:00:01	2019-10-22 10:00:01	6179
2019-10-25 08:00:01	2019-10-25 10:00:01	6180
2019-10-29 08:00:01	2019-10-29 10:00:01	6181
2019-11-01 08:00:01	2019-11-01 10:00:01	6182
2019-11-04 12:00:01	2019-11-04 14:00:01	6183
2019-11-06 08:00:01	2019-11-06 10:00:01	6184
2019-11-08 08:00:01	2019-11-08 10:00:01	6185
2019-11-11 08:00:01	2019-11-11 10:00:01	6186
2019-11-13 08:00:01	2019-11-13 10:00:01	6187
2019-08-15 10:00:01	2019-08-15 12:00:01	6188
2019-08-22 10:00:01	2019-08-22 12:00:01	6189
2019-08-29 10:00:01	2019-08-29 12:00:01	6190
2019-09-05 14:00:01	2019-09-05 16:00:01	6191
2019-09-11 10:00:01	2019-09-11 12:00:01	6192
2019-09-17 14:00:01	2019-09-17 16:00:01	6193
2019-09-20 14:00:01	2019-09-20 16:00:01	6194
2019-09-24 14:00:01	2019-09-24 16:00:01	6195
2019-09-27 14:00:01	2019-09-27 16:00:01	6196
2019-10-01 14:00:01	2019-10-01 16:00:01	6197
2019-10-04 14:00:01	2019-10-04 16:00:01	6198
2019-10-08 14:00:01	2019-10-08 16:00:01	6199
2019-10-11 14:00:01	2019-10-11 16:00:01	6200
2019-10-15 14:00:01	2019-10-15 16:00:01	6201
2019-10-18 14:00:01	2019-10-18 16:00:01	6202
2019-10-22 10:00:01	2019-10-22 12:00:01	6203
2019-10-25 10:00:01	2019-10-25 12:00:01	6204
2019-10-29 10:00:01	2019-10-29 12:00:01	6205
2019-11-01 10:00:01	2019-11-01 12:00:01	6206
2019-11-04 14:00:01	2019-11-04 16:00:01	6207
2019-11-07 12:00:01	2019-11-07 14:00:01	6208
2019-11-11 10:00:01	2019-11-11 12:00:01	6209
2019-11-14 08:00:01	2019-11-14 10:00:01	6210
2019-11-18 08:00:01	2019-11-18 10:00:01	6211
2019-08-15 12:00:01	2019-08-15 14:00:01	6212
2019-08-22 12:00:01	2019-08-22 14:00:01	6213
2019-08-29 12:00:01	2019-08-29 14:00:01	6214
2019-09-11 12:00:01	2019-09-11 14:00:01	6215
2019-09-18 08:00:01	2019-09-18 10:00:01	6216
2019-09-25 08:00:01	2019-09-25 10:00:01	6217
2019-10-02 08:00:01	2019-10-02 10:00:01	6218
2019-10-09 08:00:01	2019-10-09 10:00:01	6219
2019-10-16 08:00:01	2019-10-16 10:00:01	6220
2019-10-22 12:00:01	2019-10-22 14:00:01	6221
2019-10-25 12:00:01	2019-10-25 14:00:01	6222
2019-10-29 12:00:01	2019-10-29 14:00:01	6223
2019-11-01 12:00:01	2019-11-01 14:00:01	6224
2019-11-05 08:00:01	2019-11-05 10:00:01	6225
2019-11-08 10:00:01	2019-11-08 12:00:01	6226
2019-11-11 12:00:01	2019-11-11 14:00:01	6227
2019-11-14 10:00:01	2019-11-14 12:00:01	6228
2019-11-18 10:00:01	2019-11-18 12:00:01	6229
2019-11-21 08:00:01	2019-11-21 10:00:01	6230
2019-11-25 08:00:01	2019-11-25 10:00:01	6231
2019-11-28 08:00:01	2019-11-28 10:00:01	6232
2019-12-02 08:00:01	2019-12-02 10:00:01	6233
2019-12-05 08:00:01	2019-12-05 10:00:01	6234
2019-12-09 08:00:01	2019-12-09 10:00:01	6235
2019-08-15 14:00:01	2019-08-15 16:00:01	6236
2019-08-22 14:00:01	2019-08-22 16:00:01	6237
2019-08-29 14:00:01	2019-08-29 16:00:01	6238
2019-09-11 14:00:01	2019-09-11 16:00:01	6239
2019-09-18 10:00:01	2019-09-18 12:00:01	6240
2019-09-25 10:00:01	2019-09-25 12:00:01	6241
2019-10-02 10:00:01	2019-10-02 12:00:01	6242
2019-10-09 10:00:01	2019-10-09 12:00:01	6243
2019-10-16 10:00:01	2019-10-16 12:00:01	6244
2019-10-22 14:00:01	2019-10-22 16:00:01	6245
2019-10-25 14:00:01	2019-10-25 16:00:01	6246
2019-10-29 14:00:01	2019-10-29 16:00:01	6247
2019-11-01 14:00:01	2019-11-01 16:00:01	6248
2019-11-05 10:00:01	2019-11-05 12:00:01	6249
2019-11-08 12:00:01	2019-11-08 14:00:01	6250
2019-11-11 14:00:01	2019-11-11 16:00:01	6251
2019-11-14 12:00:01	2019-11-14 14:00:01	6252
2019-11-18 12:00:01	2019-11-18 14:00:01	6253
2019-11-21 10:00:01	2019-11-21 12:00:01	6254
2019-11-25 10:00:01	2019-11-25 12:00:01	6255
2019-11-28 10:00:01	2019-11-28 12:00:01	6256
2019-12-02 10:00:01	2019-12-02 12:00:01	6257
2019-12-05 10:00:01	2019-12-05 12:00:01	6258
2019-12-09 10:00:01	2019-12-09 12:00:01	6259
2019-09-12 12:00:01	2019-09-12 14:00:01	6260
2019-09-18 12:00:01	2019-09-18 14:00:01	6261
2019-09-25 12:00:01	2019-09-25 14:00:01	6262
2019-10-02 12:00:01	2019-10-02 14:00:01	6263
2019-10-09 12:00:01	2019-10-09 14:00:01	6264
2019-10-16 12:00:01	2019-10-16 14:00:01	6265
2019-10-23 08:00:01	2019-10-23 10:00:01	6266
2019-10-30 08:00:01	2019-10-30 10:00:01	6267
2019-11-05 12:00:01	2019-11-05 14:00:01	6268
2019-11-07 14:00:01	2019-11-07 16:00:01	6269
2019-11-12 08:00:01	2019-11-12 10:00:01	6270
2019-11-14 14:00:01	2019-11-14 16:00:01	6271
2019-11-18 14:00:01	2019-11-18 16:00:01	6272
2019-11-20 08:00:01	2019-11-20 10:00:01	6273
2019-11-22 08:00:01	2019-11-22 10:00:01	6274
2019-11-25 12:00:01	2019-11-25 14:00:01	6275
2019-11-27 08:00:01	2019-11-27 10:00:01	6276
2019-11-29 08:00:01	2019-11-29 10:00:01	6277
2019-12-02 12:00:01	2019-12-02 14:00:01	6278
2019-12-04 08:00:01	2019-12-04 10:00:01	6279
2019-12-06 08:00:01	2019-12-06 10:00:01	6280
2019-12-09 12:00:01	2019-12-09 14:00:01	6281
2019-12-11 08:00:01	2019-12-11 10:00:01	6282
2019-12-13 08:00:01	2019-12-13 10:00:01	6283
2019-09-12 14:00:01	2019-09-12 16:00:01	6284
2019-09-18 14:00:01	2019-09-18 16:00:01	6285
2019-09-25 14:00:01	2019-09-25 16:00:01	6286
2019-10-02 14:00:01	2019-10-02 16:00:01	6287
2019-10-09 14:00:01	2019-10-09 16:00:01	6288
2019-10-16 14:00:01	2019-10-16 16:00:01	6289
2019-10-23 10:00:01	2019-10-23 12:00:01	6290
2019-10-30 10:00:01	2019-10-30 12:00:01	6291
2019-11-05 14:00:01	2019-11-05 16:00:01	6292
2019-11-08 14:00:01	2019-11-08 16:00:01	6293
2019-11-12 10:00:01	2019-11-12 12:00:01	6294
2019-11-15 08:00:01	2019-11-15 10:00:01	6295
2019-11-19 08:00:01	2019-11-19 10:00:01	6296
2019-11-22 10:00:01	2019-11-22 12:00:01	6297
2019-11-25 14:00:01	2019-11-25 16:00:01	6298
2019-11-28 12:00:01	2019-11-28 14:00:01	6299
2019-12-02 14:00:01	2019-12-02 16:00:01	6300
2019-12-05 12:00:01	2019-12-05 14:00:01	6301
2019-12-09 14:00:01	2019-12-09 16:00:01	6302
2019-12-12 08:00:01	2019-12-12 10:00:01	6303
2019-12-16 08:00:01	2019-12-16 10:00:01	6304
2019-12-19 08:00:01	2019-12-19 10:00:01	6305
2019-12-23 08:00:01	2019-12-23 10:00:01	6306
2019-12-26 08:00:01	2019-12-26 10:00:01	6307
2019-06-01 00:00:01	2019-12-31 23:59:59	5887
2019-06-03 08:00:01	2019-06-03 12:00:01	5888
2019-06-10 08:00:01	2019-06-10 12:00:01	5889
2019-06-17 08:00:01	2019-06-17 12:00:01	5890
2019-06-24 08:00:01	2019-06-24 12:00:01	5891
2019-07-01 08:00:01	2019-07-01 12:00:01	5892
2019-07-08 08:00:01	2019-07-08 12:00:01	5893
2019-07-15 08:00:01	2019-07-15 12:00:01	5894
2019-07-22 08:00:01	2019-07-22 12:00:01	5895
2019-07-29 08:00:01	2019-07-29 12:00:01	5896
2019-08-05 08:00:01	2019-08-05 12:00:01	5897
2019-08-12 08:00:01	2019-08-12 12:00:01	5898
2019-08-19 08:00:01	2019-08-19 12:00:01	5899
2019-06-03 12:00:01	2019-06-03 16:00:01	5900
2019-06-11 08:00:01	2019-06-11 12:00:01	5901
2019-06-19 08:00:01	2019-06-19 12:00:01	5902
2019-06-27 08:00:01	2019-06-27 12:00:01	5903
2019-07-05 08:00:01	2019-07-05 12:00:01	5904
2019-07-15 12:00:01	2019-07-15 16:00:01	5905
2019-07-23 08:00:01	2019-07-23 12:00:01	5906
2019-07-31 08:00:01	2019-07-31 12:00:01	5907
2019-08-08 08:00:01	2019-08-08 12:00:01	5908
2019-08-16 08:00:01	2019-08-16 12:00:01	5909
2019-08-26 08:00:01	2019-08-26 12:00:01	5910
2019-09-03 08:00:01	2019-09-03 12:00:01	5911
2019-06-04 08:00:01	2019-06-04 12:00:01	5912
2019-06-07 08:00:01	2019-06-07 12:00:01	5913
2019-06-10 12:00:01	2019-06-10 16:00:01	5914
2019-06-13 08:00:01	2019-06-13 12:00:01	5915
2019-06-17 12:00:01	2019-06-17 16:00:01	5916
2019-06-20 08:00:01	2019-06-20 12:00:01	5917
2019-06-24 12:00:01	2019-06-24 16:00:01	5918
2019-06-27 12:00:01	2019-06-27 16:00:01	5919
2019-07-01 12:00:01	2019-07-01 16:00:01	5920
2019-07-04 08:00:01	2019-07-04 12:00:01	5921
2019-07-08 12:00:01	2019-07-08 16:00:01	5922
2019-07-11 08:00:01	2019-07-11 12:00:01	5923
2019-06-04 12:00:01	2019-06-04 16:00:01	5924
2019-06-07 12:00:01	2019-06-07 16:00:01	5925
2019-06-11 12:00:01	2019-06-11 16:00:01	5926
2019-06-14 08:00:01	2019-06-14 12:00:01	5927
2019-06-18 08:00:01	2019-06-18 12:00:01	5928
2019-06-21 08:00:01	2019-06-21 12:00:01	5929
2019-06-25 08:00:01	2019-06-25 12:00:01	5930
2019-06-28 08:00:01	2019-06-28 12:00:01	5931
2019-07-02 08:00:01	2019-07-02 12:00:01	5932
2019-07-05 12:00:01	2019-07-05 16:00:01	5933
2019-07-09 08:00:01	2019-07-09 12:00:01	5934
2019-07-12 08:00:01	2019-07-12 12:00:01	5935
2019-06-05 08:00:01	2019-06-05 12:00:01	5936
2019-06-12 08:00:01	2019-06-12 12:00:01	5937
2019-06-18 12:00:01	2019-06-18 16:00:01	5938
2019-06-21 12:00:01	2019-06-21 16:00:01	5939
2019-06-25 12:00:01	2019-06-25 16:00:01	5940
2019-06-28 12:00:01	2019-06-28 16:00:01	5941
2019-07-02 12:00:01	2019-07-02 16:00:01	5942
2019-07-09 12:00:01	2019-07-09 16:00:01	5943
2019-07-12 12:00:01	2019-07-12 16:00:01	5944
2019-07-16 08:00:01	2019-07-16 12:00:01	5945
2019-07-19 08:00:01	2019-07-19 12:00:01	5946
2019-07-22 12:00:01	2019-07-22 16:00:01	5947
2019-06-05 12:00:01	2019-06-05 16:00:01	5948
2019-06-12 12:00:01	2019-06-12 16:00:01	5949
2019-06-14 12:00:01	2019-06-14 16:00:01	5950
2019-06-19 12:00:01	2019-06-19 16:00:01	5951
2019-06-26 08:00:01	2019-06-26 12:00:01	5952
2019-07-03 08:00:01	2019-07-03 12:00:01	5953
2019-07-10 08:00:01	2019-07-10 12:00:01	5954
2019-07-16 12:00:01	2019-07-16 16:00:01	5955
2019-07-18 08:00:01	2019-07-18 12:00:01	5956
2019-07-23 12:00:01	2019-07-23 16:00:01	5957
2019-07-25 08:00:01	2019-07-25 12:00:01	5958
2019-07-29 12:00:01	2019-07-29 16:00:01	5959
2019-06-06 08:00:01	2019-06-06 12:00:01	5960
2019-06-13 12:00:01	2019-06-13 16:00:01	5961
2019-06-20 12:00:01	2019-06-20 16:00:01	5962
2019-06-26 12:00:01	2019-06-26 16:00:01	5963
2019-07-03 12:00:01	2019-07-03 16:00:01	5964
2019-07-10 12:00:01	2019-07-10 16:00:01	5965
2019-07-17 08:00:01	2019-07-17 12:00:01	5966
2019-07-24 08:00:01	2019-07-24 12:00:01	5967
2019-07-30 08:00:01	2019-07-30 12:00:01	5968
2019-08-02 08:00:01	2019-08-02 12:00:01	5969
2019-08-05 12:00:01	2019-08-05 16:00:01	5970
2019-08-08 12:00:01	2019-08-08 16:00:01	5971
2019-06-06 12:00:01	2019-06-06 14:00:01	5972
2019-07-04 12:00:01	2019-07-04 14:00:01	5973
2019-07-11 12:00:01	2019-07-11 14:00:01	5974
2019-07-17 12:00:01	2019-07-17 14:00:01	5975
2019-07-19 12:00:01	2019-07-19 14:00:01	5976
2019-07-24 12:00:01	2019-07-24 14:00:01	5977
2019-07-26 08:00:01	2019-07-26 10:00:01	5978
2019-07-30 12:00:01	2019-07-30 14:00:01	5979
2019-08-01 08:00:01	2019-08-01 10:00:01	5980
2019-08-06 08:00:01	2019-08-06 10:00:01	5981
2019-08-09 08:00:01	2019-08-09 10:00:01	5982
2019-08-12 12:00:01	2019-08-12 14:00:01	5983
2019-08-14 08:00:01	2019-08-14 10:00:01	5984
2019-08-16 12:00:01	2019-08-16 14:00:01	5985
2019-08-19 12:00:01	2019-08-19 14:00:01	5986
2019-08-21 08:00:01	2019-08-21 10:00:01	5987
2019-08-23 08:00:01	2019-08-23 10:00:01	5988
2019-08-26 12:00:01	2019-08-26 14:00:01	5989
2019-08-28 08:00:01	2019-08-28 10:00:01	5990
2019-08-30 08:00:01	2019-08-30 10:00:01	5991
2019-09-02 08:00:01	2019-09-02 10:00:01	5992
2019-09-04 08:00:01	2019-09-04 10:00:01	5993
2019-09-06 08:00:01	2019-09-06 10:00:01	5994
2019-09-09 08:00:01	2019-09-09 10:00:01	5995
2019-06-06 14:00:01	2019-06-06 16:00:01	5996
2019-07-04 14:00:01	2019-07-04 16:00:01	5997
2019-07-11 14:00:01	2019-07-11 16:00:01	5998
2019-07-17 14:00:01	2019-07-17 16:00:01	5999
2019-07-19 14:00:01	2019-07-19 16:00:01	6000
2019-07-24 14:00:01	2019-07-24 16:00:01	6001
2019-07-26 10:00:01	2019-07-26 12:00:01	6002
2019-07-30 14:00:01	2019-07-30 16:00:01	6003
2019-08-01 10:00:01	2019-08-01 12:00:01	6004
2019-08-06 10:00:01	2019-08-06 12:00:01	6005
2019-08-09 10:00:01	2019-08-09 12:00:01	6006
2019-08-12 14:00:01	2019-08-12 16:00:01	6007
2019-08-14 10:00:01	2019-08-14 12:00:01	6008
2019-08-16 14:00:01	2019-08-16 16:00:01	6009
2019-08-19 14:00:01	2019-08-19 16:00:01	6010
2019-08-21 10:00:01	2019-08-21 12:00:01	6011
2019-08-23 10:00:01	2019-08-23 12:00:01	6012
2019-08-26 14:00:01	2019-08-26 16:00:01	6013
2019-08-28 10:00:01	2019-08-28 12:00:01	6014
2019-08-30 10:00:01	2019-08-30 12:00:01	6015
2019-09-02 10:00:01	2019-09-02 12:00:01	6016
2019-09-04 10:00:01	2019-09-04 12:00:01	6017
2019-09-06 10:00:01	2019-09-06 12:00:01	6018
2019-09-09 10:00:01	2019-09-09 12:00:01	6019
2019-07-18 12:00:01	2019-07-18 14:00:01	6020
2019-07-25 12:00:01	2019-07-25 14:00:01	6021
2019-07-31 12:00:01	2019-07-31 14:00:01	6022
2019-08-06 12:00:01	2019-08-06 14:00:01	6023
2019-08-09 12:00:01	2019-08-09 14:00:01	6024
2019-08-13 08:00:01	2019-08-13 10:00:01	6025
2019-08-20 08:00:01	2019-08-20 10:00:01	6026
2019-08-23 12:00:01	2019-08-23 14:00:01	6027
2019-08-27 08:00:01	2019-08-27 10:00:01	6028
2019-08-30 12:00:01	2019-08-30 14:00:01	6029
2019-09-02 12:00:01	2019-09-02 14:00:01	6030
2019-09-05 08:00:01	2019-09-05 10:00:01	6031
2019-09-09 12:00:01	2019-09-09 14:00:01	6032
2019-09-12 08:00:01	2019-09-12 10:00:01	6033
2019-09-16 08:00:01	2019-09-16 10:00:01	6034
2019-09-19 08:00:01	2019-09-19 10:00:01	6035
2019-09-23 08:00:01	2019-09-23 10:00:01	6036
2019-09-26 08:00:01	2019-09-26 10:00:01	6037
2019-09-30 08:00:01	2019-09-30 10:00:01	6038
2019-10-03 08:00:01	2019-10-03 10:00:01	6039
2019-10-07 08:00:01	2019-10-07 10:00:01	6040
2019-10-10 08:00:01	2019-10-10 10:00:01	6041
2019-10-14 08:00:01	2019-10-14 10:00:01	6042
2019-10-17 08:00:01	2019-10-17 10:00:01	6043
2019-07-18 14:00:01	2019-07-18 16:00:01	6044
2019-07-25 14:00:01	2019-07-25 16:00:01	6045
2019-07-31 14:00:01	2019-07-31 16:00:01	6046
2019-08-06 14:00:01	2019-08-06 16:00:01	6047
2019-08-09 14:00:01	2019-08-09 16:00:01	6048
2019-08-13 10:00:01	2019-08-13 12:00:01	6049
2019-08-20 10:00:01	2019-08-20 12:00:01	6050
2019-08-23 14:00:01	2019-08-23 16:00:01	6051
2019-08-27 10:00:01	2019-08-27 12:00:01	6052
2019-08-30 14:00:01	2019-08-30 16:00:01	6053
2019-09-02 14:00:01	2019-09-02 16:00:01	6054
2019-09-05 10:00:01	2019-09-05 12:00:01	6055
2019-09-09 14:00:01	2019-09-09 16:00:01	6056
2019-09-12 10:00:01	2019-09-12 12:00:01	6057
2019-09-16 10:00:01	2019-09-16 12:00:01	6058
2019-09-19 10:00:01	2019-09-19 12:00:01	6059
2019-09-23 10:00:01	2019-09-23 12:00:01	6060
2019-09-26 10:00:01	2019-09-26 12:00:01	6061
2019-09-30 10:00:01	2019-09-30 12:00:01	6062
2019-10-03 10:00:01	2019-10-03 12:00:01	6063
2019-10-07 10:00:01	2019-10-07 12:00:01	6064
2019-10-10 10:00:01	2019-10-10 12:00:01	6065
2019-10-14 10:00:01	2019-10-14 12:00:01	6066
2019-10-17 10:00:01	2019-10-17 12:00:01	6067
2019-07-26 12:00:01	2019-07-26 14:00:01	6068
2019-08-01 12:00:01	2019-08-01 14:00:01	6069
2019-08-07 08:00:01	2019-08-07 10:00:01	6070
2019-08-13 12:00:01	2019-08-13 14:00:01	6071
2019-08-20 12:00:01	2019-08-20 14:00:01	6072
2019-08-27 12:00:01	2019-08-27 14:00:01	6073
2019-09-03 12:00:01	2019-09-03 14:00:01	6074
2019-09-06 12:00:01	2019-09-06 14:00:01	6075
2019-09-10 08:00:01	2019-09-10 10:00:01	6076
2019-09-13 08:00:01	2019-09-13 10:00:01	6077
2019-09-16 12:00:01	2019-09-16 14:00:01	6078
2019-09-19 12:00:01	2019-09-19 14:00:01	6079
2019-09-23 12:00:01	2019-09-23 14:00:01	6080
2019-09-26 12:00:01	2019-09-26 14:00:01	6081
2019-09-30 12:00:01	2019-09-30 14:00:01	6082
2019-10-03 12:00:01	2019-10-03 14:00:01	6083
2019-10-07 12:00:01	2019-10-07 14:00:01	6084
2019-10-10 12:00:01	2019-10-10 14:00:01	6085
2019-10-14 12:00:01	2019-10-14 14:00:01	6086
2019-10-17 12:00:01	2019-10-17 14:00:01	6087
2019-10-21 08:00:01	2019-10-21 10:00:01	6088
2019-10-24 08:00:01	2019-10-24 10:00:01	6089
2019-10-28 08:00:01	2019-10-28 10:00:01	6090
2019-10-31 08:00:01	2019-10-31 10:00:01	6091
2019-07-26 14:00:01	2019-07-26 16:00:01	6092
2019-08-01 14:00:01	2019-08-01 16:00:01	6093
2019-08-07 10:00:01	2019-08-07 12:00:01	6094
2019-08-13 14:00:01	2019-08-13 16:00:01	6095
2019-08-20 14:00:01	2019-08-20 16:00:01	6096
2019-08-27 14:00:01	2019-08-27 16:00:01	6097
2019-09-03 14:00:01	2019-09-03 16:00:01	6098
2019-09-06 14:00:01	2019-09-06 16:00:01	6099
2019-09-10 10:00:01	2019-09-10 12:00:01	6100
2019-09-13 10:00:01	2019-09-13 12:00:01	6101
2019-09-16 14:00:01	2019-09-16 16:00:01	6102
2019-09-19 14:00:01	2019-09-19 16:00:01	6103
2019-09-23 14:00:01	2019-09-23 16:00:01	6104
2019-09-26 14:00:01	2019-09-26 16:00:01	6105
2019-09-30 14:00:01	2019-09-30 16:00:01	6106
2019-10-03 14:00:01	2019-10-03 16:00:01	6107
2019-10-07 14:00:01	2019-10-07 16:00:01	6108
2019-10-10 14:00:01	2019-10-10 16:00:01	6109
2019-10-14 14:00:01	2019-10-14 16:00:01	6110
2019-10-17 14:00:01	2019-10-17 16:00:01	6111
2019-10-21 10:00:01	2019-10-21 12:00:01	6112
2019-10-24 10:00:01	2019-10-24 12:00:01	6113
2019-10-28 10:00:01	2019-10-28 12:00:01	6114
2019-10-31 10:00:01	2019-10-31 12:00:01	6115
2019-08-02 12:00:01	2019-08-02 14:00:01	6116
2019-08-07 12:00:01	2019-08-07 14:00:01	6117
2019-08-14 12:00:01	2019-08-14 14:00:01	6118
2019-08-21 12:00:01	2019-08-21 14:00:01	6119
2019-08-28 12:00:01	2019-08-28 14:00:01	6120
2019-09-04 12:00:01	2019-09-04 14:00:01	6121
2019-09-10 12:00:01	2019-09-10 14:00:01	6122
2019-09-13 12:00:01	2019-09-13 14:00:01	6123
2019-09-17 08:00:01	2019-09-17 10:00:01	6124
2019-09-20 08:00:01	2019-09-20 10:00:01	6125
2019-09-24 08:00:01	2019-09-24 10:00:01	6126
2019-09-27 08:00:01	2019-09-27 10:00:01	6127
2019-10-01 08:00:01	2019-10-01 10:00:01	6128
2019-10-04 08:00:01	2019-10-04 10:00:01	6129
2019-10-08 08:00:01	2019-10-08 10:00:01	6130
2019-10-11 08:00:01	2019-10-11 10:00:01	6131
2019-10-15 08:00:01	2019-10-15 10:00:01	6132
2019-10-18 08:00:01	2019-10-18 10:00:01	6133
2019-10-21 12:00:01	2019-10-21 14:00:01	6134
2019-10-24 12:00:01	2019-10-24 14:00:01	6135
2019-10-28 12:00:01	2019-10-28 14:00:01	6136
2019-10-31 12:00:01	2019-10-31 14:00:01	6137
2019-11-04 08:00:01	2019-11-04 10:00:01	6138
2019-11-07 08:00:01	2019-11-07 10:00:01	6139
\.


--
-- TOC entry 3032 (class 0 OID 17172)
-- Dependencies: 215
-- Data for Name: PossibleCoursesForTeacher; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."PossibleCoursesForTeacher" (teacher_to_possible, course_possible, id_of_couse_to_teacher) FROM stdin;
jan001	SM	1
jan001	LAB	2
\.


--
-- TOC entry 3034 (class 0 OID 17180)
-- Dependencies: 217
-- Data for Name: Rooms; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Rooms" (rooms_name, area, "numberOfPlaces", rooms_id) FROM stdin;
u180	TEK	100	1
u181	TEK	100	2
u74	HovedIndgang	40	3
u1	IndgangC	200	4
room160	uni	141	5
room31	uni	159	6
room224	uni	177	7
room109	uni	179	8
room192	uni	102	9
room242	uni	59	10
room118	uni	90	11
room131	uni	93	12
room73	uni	90	13
room89	uni	204	14
room132	uni	172	15
room209	uni	101	16
room198	uni	172	17
room120	uni	63	18
room186	uni	65	19
room99	uni	52	20
\.


--
-- TOC entry 3035 (class 0 OID 17186)
-- Dependencies: 218
-- Data for Name: RoomsAndBookings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."RoomsAndBookings" (id_rooms_for_bookings, room_for_courses, "bookingsToRooms") FROM stdin;
482	1	0
483	1	0
484	1	0
485	1	0
486	1	0
487	1	0
488	1	0
489	1	0
490	1	0
491	1	0
492	1	0
493	1	0
494	1	0
495	1	0
496	1	0
497	1	0
498	1	0
499	1	0
500	1	0
501	1	0
502	1	0
\.


--
-- TOC entry 3038 (class 0 OID 17193)
-- Dependencies: 221
-- Data for Name: Schedules; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Schedules" (schedules_id, periods_id) FROM stdin;
65	5887
\.


--
-- TOC entry 3040 (class 0 OID 17198)
-- Dependencies: 223
-- Data for Name: StudentsForBookings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."StudentsForBookings" (id, "classOfStudents", "bookingForstudents") FROM stdin;
\.


--
-- TOC entry 3042 (class 0 OID 17206)
-- Dependencies: 225
-- Data for Name: StudentsForCourses; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."StudentsForCourses" (student, course, id_students_to_courses) FROM stdin;
\.


--
-- TOC entry 3044 (class 0 OID 17214)
-- Dependencies: 227
-- Data for Name: Teachers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Teachers" (teachers_id, "teachersName") FROM stdin;
jan001	Jan Corfixen Sørensen
ac	a
tobi111	Tobias Petersen
teacher06	teacher05
teacher04	teacher02
teacher03	teacher09
teacher07	teacher08
teacher01	teacher06
teacher09	teacher06
teacher02	teacher01
teacher08	teacher07
teacher05	teacher010
teacher010	teacher06
\.


--
-- TOC entry 3045 (class 0 OID 17220)
-- Dependencies: 228
-- Data for Name: Users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Users" ("Login", "Password", users_teacher, name, "isClassScheduler") FROM stdin;
jan	corfixen	jan001	Jan Corfixen Sørensen	\N
laura	tobias	\N	Laura Frisendahl Petersen	t
\.


--
-- TOC entry 3066 (class 0 OID 0)
-- Dependencies: 197
-- Name: BookedPeriodsForClassesOfStudents_id_booking_students_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."BookedPeriodsForClassesOfStudents_id_booking_students_seq"', 1191, true);


--
-- TOC entry 3067 (class 0 OID 0)
-- Dependencies: 199
-- Name: BookedPeriodsForRoom_id_booked_period_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."BookedPeriodsForRoom_id_booked_period_seq"', 502, true);


--
-- TOC entry 3068 (class 0 OID 0)
-- Dependencies: 201
-- Name: BookedPeriodsForTeachers_bookings_teachers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."BookedPeriodsForTeachers_bookings_teachers_id_seq"', 6229, true);


--
-- TOC entry 3069 (class 0 OID 0)
-- Dependencies: 203
-- Name: BookedRoomsForCourse_id_bookedRoomsCourse_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."BookedRoomsForCourse_id_bookedRoomsCourse_seq"', 502, true);


--
-- TOC entry 3070 (class 0 OID 0)
-- Dependencies: 205
-- Name: Bookings_bookings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Bookings_bookings_id_seq"', 6229, true);


--
-- TOC entry 3071 (class 0 OID 0)
-- Dependencies: 210
-- Name: DaysBookingPeriodes_DaysBookinPeriodId_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."DaysBookingPeriodes_DaysBookinPeriodId_seq"', 24, true);


--
-- TOC entry 3072 (class 0 OID 0)
-- Dependencies: 212
-- Name: NeighborAreasForRoom_id_neighbors_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."NeighborAreasForRoom_id_neighbors_seq"', 1, true);


--
-- TOC entry 3073 (class 0 OID 0)
-- Dependencies: 214
-- Name: Periods_periods_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Periods_periods_id_seq"', 6307, true);


--
-- TOC entry 3074 (class 0 OID 0)
-- Dependencies: 216
-- Name: PossibleCoursesForTeacher_id_of_couse_to_teacher_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."PossibleCoursesForTeacher_id_of_couse_to_teacher_seq"', 1, false);


--
-- TOC entry 3075 (class 0 OID 0)
-- Dependencies: 219
-- Name: RoomsAndBookings_id_rooms_for_bookings_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."RoomsAndBookings_id_rooms_for_bookings_seq"', 502, true);


--
-- TOC entry 3076 (class 0 OID 0)
-- Dependencies: 220
-- Name: Rooms_rooms_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Rooms_rooms_id_seq"', 20, true);


--
-- TOC entry 3077 (class 0 OID 0)
-- Dependencies: 222
-- Name: Schedules_schedules_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Schedules_schedules_id_seq"', 65, true);


--
-- TOC entry 3078 (class 0 OID 0)
-- Dependencies: 224
-- Name: StudentsForBookings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."StudentsForBookings_id_seq"', 1192, true);


--
-- TOC entry 3079 (class 0 OID 0)
-- Dependencies: 226
-- Name: StudentsForCourses_id_students_to_courses_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."StudentsForCourses_id_students_to_courses_seq"', 1, false);


--
-- TOC entry 2874 (class 2606 OID 17241)
-- Name: Teachers Teachers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Teachers"
    ADD CONSTRAINT "Teachers_pkey" PRIMARY KEY (teachers_id);


--
-- TOC entry 2876 (class 2606 OID 17243)
-- Name: Users Users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT "Users_pkey" PRIMARY KEY ("Login");


--
-- TOC entry 2815 (class 2606 OID 17245)
-- Name: BookedPeriodsForClassesOfStudents pk_bookedperiodsclass; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."BookedPeriodsForClassesOfStudents"
    ADD CONSTRAINT pk_bookedperiodsclass PRIMARY KEY (id_booking_students);


--
-- TOC entry 2818 (class 2606 OID 17247)
-- Name: BookedPeriodsForRoom pk_bookedperiodsroom; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."BookedPeriodsForRoom"
    ADD CONSTRAINT pk_bookedperiodsroom PRIMARY KEY (id_booked_period);


--
-- TOC entry 2822 (class 2606 OID 17249)
-- Name: BookedPeriodsForTeachers pk_bookedperiodsteacher; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."BookedPeriodsForTeachers"
    ADD CONSTRAINT pk_bookedperiodsteacher PRIMARY KEY (bookings_teachers_id);


--
-- TOC entry 2826 (class 2606 OID 17251)
-- Name: BookedRoomsForCourse pk_bookedrooms; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."BookedRoomsForCourse"
    ADD CONSTRAINT pk_bookedrooms PRIMARY KEY ("id_bookedRoomsCourse");


--
-- TOC entry 2835 (class 2606 OID 17253)
-- Name: Bookings pk_bookings; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Bookings"
    ADD CONSTRAINT pk_bookings PRIMARY KEY (bookings_id);


--
-- TOC entry 2837 (class 2606 OID 17255)
-- Name: ClassesOfStudents pk_classOfstudents; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."ClassesOfStudents"
    ADD CONSTRAINT "pk_classOfstudents" PRIMARY KEY (classes_id);


--
-- TOC entry 2840 (class 2606 OID 17257)
-- Name: Courses pk_courses; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Courses"
    ADD CONSTRAINT pk_courses PRIMARY KEY (courses_id);


--
-- TOC entry 2845 (class 2606 OID 17259)
-- Name: Days pk_day; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Days"
    ADD CONSTRAINT pk_day PRIMARY KEY ("dateOfDay");


--
-- TOC entry 2847 (class 2606 OID 17261)
-- Name: DaysBookingPeriodes pk_dbp; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."DaysBookingPeriodes"
    ADD CONSTRAINT pk_dbp PRIMARY KEY ("DaysBookinPeriodId");


--
-- TOC entry 2850 (class 2606 OID 17263)
-- Name: NeighborAreasForRoom pk_neighbor; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."NeighborAreasForRoom"
    ADD CONSTRAINT pk_neighbor PRIMARY KEY (id_neighbors);


--
-- TOC entry 2852 (class 2606 OID 17265)
-- Name: Periods pk_periods; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Periods"
    ADD CONSTRAINT pk_periods PRIMARY KEY (periods_id);


--
-- TOC entry 2856 (class 2606 OID 17267)
-- Name: PossibleCoursesForTeacher pk_possiblecoursesForTeacher; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."PossibleCoursesForTeacher"
    ADD CONSTRAINT "pk_possiblecoursesForTeacher" PRIMARY KEY (id_of_couse_to_teacher);


--
-- TOC entry 2858 (class 2606 OID 17269)
-- Name: Rooms pk_room; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Rooms"
    ADD CONSTRAINT pk_room PRIMARY KEY (rooms_id);


--
-- TOC entry 2861 (class 2606 OID 17271)
-- Name: RoomsAndBookings pk_roomsandbokings; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."RoomsAndBookings"
    ADD CONSTRAINT pk_roomsandbokings PRIMARY KEY (id_rooms_for_bookings);


--
-- TOC entry 2864 (class 2606 OID 17273)
-- Name: Schedules pk_schedule; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Schedules"
    ADD CONSTRAINT pk_schedule PRIMARY KEY (schedules_id);


--
-- TOC entry 2872 (class 2606 OID 17275)
-- Name: StudentsForCourses pk_students; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."StudentsForCourses"
    ADD CONSTRAINT pk_students PRIMARY KEY (id_students_to_courses);


--
-- TOC entry 2868 (class 2606 OID 17277)
-- Name: StudentsForBookings pk_studentsForBookings; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."StudentsForBookings"
    ADD CONSTRAINT "pk_studentsForBookings" PRIMARY KEY (id);


--
-- TOC entry 2841 (class 1259 OID 17278)
-- Name: fki_best_period; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_best_period ON public."Days" USING btree ("bestPeriod");


--
-- TOC entry 2812 (class 1259 OID 17279)
-- Name: fki_booking_class; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_booking_class ON public."BookedPeriodsForClassesOfStudents" USING btree ("classOfStudents");


--
-- TOC entry 2813 (class 1259 OID 17280)
-- Name: fki_booking_students_period; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_booking_students_period ON public."BookedPeriodsForClassesOfStudents" USING btree ("bookingsPeriod");


--
-- TOC entry 2865 (class 1259 OID 17281)
-- Name: fki_bookings_students; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_bookings_students ON public."StudentsForBookings" USING btree ("bookingForstudents");


--
-- TOC entry 2823 (class 1259 OID 17282)
-- Name: fki_courseInRoom; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_courseInRoom" ON public."BookedRoomsForCourse" USING btree ("courseInRoom");


--
-- TOC entry 2827 (class 1259 OID 17283)
-- Name: fki_course_for_booking; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_course_for_booking ON public."Bookings" USING btree (course_for_booking);


--
-- TOC entry 2828 (class 1259 OID 17284)
-- Name: fki_course_for_bookings; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_course_for_bookings ON public."Bookings" USING btree (course_for_booking);


--
-- TOC entry 2869 (class 1259 OID 17285)
-- Name: fki_courses_students; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_courses_students ON public."StudentsForCourses" USING btree (course);


--
-- TOC entry 2842 (class 1259 OID 17286)
-- Name: fki_emergency_period; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_emergency_period ON public."Days" USING btree ("emergencyPeriod");


--
-- TOC entry 2829 (class 1259 OID 17287)
-- Name: fki_fkTeacherBooking; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_fkTeacherBooking" ON public."Bookings" USING btree (teacher_for_booking);


--
-- TOC entry 2843 (class 1259 OID 17288)
-- Name: fki_medium_period; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_medium_period ON public."Days" USING btree ("mediumPeriod");


--
-- TOC entry 2830 (class 1259 OID 17289)
-- Name: fki_period_for_booking; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_period_for_booking ON public."Bookings" USING btree (period_for_booking);


--
-- TOC entry 2862 (class 1259 OID 17290)
-- Name: fki_periods_to_schedule; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_periods_to_schedule ON public."Schedules" USING btree (periods_id);


--
-- TOC entry 2819 (class 1259 OID 17291)
-- Name: fki_pk_period_teacher_booking; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_pk_period_teacher_booking ON public."BookedPeriodsForTeachers" USING btree (periods_bookings_teacher);


--
-- TOC entry 2853 (class 1259 OID 17292)
-- Name: fki_possible_courses; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_possible_courses ON public."PossibleCoursesForTeacher" USING btree (course_possible);


--
-- TOC entry 2816 (class 1259 OID 17293)
-- Name: fki_room_booked; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_room_booked ON public."BookedPeriodsForRoom" USING btree (room_for_period);


--
-- TOC entry 2848 (class 1259 OID 17294)
-- Name: fki_room_for_neighbor; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_room_for_neighbor ON public."NeighborAreasForRoom" USING btree (room_for_areas);


--
-- TOC entry 2824 (class 1259 OID 17295)
-- Name: fki_room_periods; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_room_periods ON public."BookedRoomsForCourse" USING btree (fk_room);


--
-- TOC entry 2859 (class 1259 OID 17296)
-- Name: fki_room_to_bookings; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_room_to_bookings ON public."RoomsAndBookings" USING btree (room_for_courses);


--
-- TOC entry 2831 (class 1259 OID 17297)
-- Name: fki_schedules_id; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_schedules_id ON public."Bookings" USING btree (schedules_id);


--
-- TOC entry 2870 (class 1259 OID 17298)
-- Name: fki_students; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_students ON public."StudentsForCourses" USING btree (student);


--
-- TOC entry 2832 (class 1259 OID 17299)
-- Name: fki_students_booking; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_students_booking ON public."Bookings" USING btree ("studentsForBooking");


--
-- TOC entry 2866 (class 1259 OID 17300)
-- Name: fki_students_bookings; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_students_bookings ON public."StudentsForBookings" USING btree ("classOfStudents");


--
-- TOC entry 2877 (class 1259 OID 17301)
-- Name: fki_teacher; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_teacher ON public."Users" USING btree (users_teacher);


--
-- TOC entry 2820 (class 1259 OID 17302)
-- Name: fki_teacher_booking; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_teacher_booking ON public."BookedPeriodsForTeachers" USING btree (teacher_of_booking);


--
-- TOC entry 2838 (class 1259 OID 17303)
-- Name: fki_teacher_course; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_teacher_course ON public."Courses" USING btree (assigned_teacher);


--
-- TOC entry 2833 (class 1259 OID 17304)
-- Name: fki_teacher_for_booking; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_teacher_for_booking ON public."Bookings" USING btree (teacher_for_booking);


--
-- TOC entry 2854 (class 1259 OID 17305)
-- Name: fki_teacher_to_possible; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_teacher_to_possible ON public."PossibleCoursesForTeacher" USING btree (teacher_to_possible);


--
-- TOC entry 2889 (class 2606 OID 17306)
-- Name: StudentsForBookings fk_bookings_students; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."StudentsForBookings"
    ADD CONSTRAINT fk_bookings_students FOREIGN KEY ("bookingForstudents") REFERENCES public."Bookings"(bookings_id);


--
-- TOC entry 2880 (class 2606 OID 17311)
-- Name: Bookings fk_course_for_bookings; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Bookings"
    ADD CONSTRAINT fk_course_for_bookings FOREIGN KEY (course_for_booking) REFERENCES public."Courses"(courses_id);


--
-- TOC entry 2881 (class 2606 OID 17316)
-- Name: Bookings fk_period_for_booking; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Bookings"
    ADD CONSTRAINT fk_period_for_booking FOREIGN KEY (period_for_booking) REFERENCES public."Periods"(periods_id);


--
-- TOC entry 2888 (class 2606 OID 17321)
-- Name: Schedules fk_periods_to_schedule; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Schedules"
    ADD CONSTRAINT fk_periods_to_schedule FOREIGN KEY (periods_id) REFERENCES public."Periods"(periods_id);


--
-- TOC entry 2878 (class 2606 OID 17326)
-- Name: BookedPeriodsForRoom fk_room_booked; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."BookedPeriodsForRoom"
    ADD CONSTRAINT fk_room_booked FOREIGN KEY (room_for_period) REFERENCES public."Rooms"(rooms_id);


--
-- TOC entry 2886 (class 2606 OID 17331)
-- Name: NeighborAreasForRoom fk_room_for_neighbor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."NeighborAreasForRoom"
    ADD CONSTRAINT fk_room_for_neighbor FOREIGN KEY (room_for_areas) REFERENCES public."Rooms"(rooms_id);


--
-- TOC entry 2879 (class 2606 OID 17336)
-- Name: BookedRoomsForCourse fk_room_periods; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."BookedRoomsForCourse"
    ADD CONSTRAINT fk_room_periods FOREIGN KEY (fk_room) REFERENCES public."Rooms"(rooms_id);


--
-- TOC entry 2887 (class 2606 OID 17341)
-- Name: RoomsAndBookings fk_room_to_bookings; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."RoomsAndBookings"
    ADD CONSTRAINT fk_room_to_bookings FOREIGN KEY (room_for_courses) REFERENCES public."Rooms"(rooms_id);


--
-- TOC entry 2882 (class 2606 OID 17346)
-- Name: Bookings fk_schedules_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Bookings"
    ADD CONSTRAINT fk_schedules_id FOREIGN KEY (schedules_id) REFERENCES public."Schedules"(schedules_id);


--
-- TOC entry 2883 (class 2606 OID 17351)
-- Name: Bookings fk_students_booking; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Bookings"
    ADD CONSTRAINT fk_students_booking FOREIGN KEY ("studentsForBooking") REFERENCES public."StudentsForBookings"(id);


--
-- TOC entry 2890 (class 2606 OID 17356)
-- Name: StudentsForBookings fk_students_bookings; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."StudentsForBookings"
    ADD CONSTRAINT fk_students_bookings FOREIGN KEY ("classOfStudents") REFERENCES public."ClassesOfStudents"(classes_id);


--
-- TOC entry 2885 (class 2606 OID 17361)
-- Name: Courses fk_teacher_course; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Courses"
    ADD CONSTRAINT fk_teacher_course FOREIGN KEY (assigned_teacher) REFERENCES public."Teachers"(teachers_id);


--
-- TOC entry 2884 (class 2606 OID 17366)
-- Name: Bookings fk_teacher_for_booking; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Bookings"
    ADD CONSTRAINT fk_teacher_for_booking FOREIGN KEY (teacher_for_booking) REFERENCES public."Teachers"(teachers_id);


--
-- TOC entry 2891 (class 2606 OID 17371)
-- Name: Users teacher; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT teacher FOREIGN KEY (users_teacher) REFERENCES public."Teachers"(teachers_id);


-- Completed on 2019-06-01 22:02:28

--
-- PostgreSQL database dump complete
--

