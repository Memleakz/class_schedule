PGDMP     2                    w           class_scheldue    11.3    11.3 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            �           1262    16393    class_scheldue    DATABASE     �   CREATE DATABASE class_scheldue WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Danish_Denmark.1252' LC_CTYPE = 'Danish_Denmark.1252';
    DROP DATABASE class_scheldue;
             postgres    false            �            1259    17096 !   BookedPeriodsForClassesOfStudents    TABLE     �   CREATE TABLE public."BookedPeriodsForClassesOfStudents" (
    "bookingsPeriod" text,
    "classOfStudents" text,
    id_booking_students integer NOT NULL
);
 7   DROP TABLE public."BookedPeriodsForClassesOfStudents";
       public         postgres    false            �            1259    17102 9   BookedPeriodsForClassesOfStudents_id_booking_students_seq    SEQUENCE     �   CREATE SEQUENCE public."BookedPeriodsForClassesOfStudents_id_booking_students_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 R   DROP SEQUENCE public."BookedPeriodsForClassesOfStudents_id_booking_students_seq";
       public       postgres    false    196            �           0    0 9   BookedPeriodsForClassesOfStudents_id_booking_students_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE public."BookedPeriodsForClassesOfStudents_id_booking_students_seq" OWNED BY public."BookedPeriodsForClassesOfStudents".id_booking_students;
            public       postgres    false    197            �            1259    17104    BookedPeriodsForRoom    TABLE     �   CREATE TABLE public."BookedPeriodsForRoom" (
    id_booked_period integer NOT NULL,
    room_for_period integer,
    period_for_booking integer
);
 *   DROP TABLE public."BookedPeriodsForRoom";
       public         postgres    false            �            1259    17107 )   BookedPeriodsForRoom_id_booked_period_seq    SEQUENCE     �   CREATE SEQUENCE public."BookedPeriodsForRoom_id_booked_period_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 B   DROP SEQUENCE public."BookedPeriodsForRoom_id_booked_period_seq";
       public       postgres    false    198            �           0    0 )   BookedPeriodsForRoom_id_booked_period_seq    SEQUENCE OWNED BY     {   ALTER SEQUENCE public."BookedPeriodsForRoom_id_booked_period_seq" OWNED BY public."BookedPeriodsForRoom".id_booked_period;
            public       postgres    false    199            �            1259    17109    BookedPeriodsForTeachers    TABLE     �   CREATE TABLE public."BookedPeriodsForTeachers" (
    periods_bookings_teacher text,
    teacher_of_booking text,
    bookings_teachers_id integer NOT NULL
);
 .   DROP TABLE public."BookedPeriodsForTeachers";
       public         postgres    false            �            1259    17115 1   BookedPeriodsForTeachers_bookings_teachers_id_seq    SEQUENCE     �   CREATE SEQUENCE public."BookedPeriodsForTeachers_bookings_teachers_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 J   DROP SEQUENCE public."BookedPeriodsForTeachers_bookings_teachers_id_seq";
       public       postgres    false    200            �           0    0 1   BookedPeriodsForTeachers_bookings_teachers_id_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE public."BookedPeriodsForTeachers_bookings_teachers_id_seq" OWNED BY public."BookedPeriodsForTeachers".bookings_teachers_id;
            public       postgres    false    201            �            1259    17117    BookedRoomsForCourse    TABLE     �   CREATE TABLE public."BookedRoomsForCourse" (
    "courseInRoom" text,
    "id_bookedRoomsCourse" integer NOT NULL,
    fk_room integer
);
 *   DROP TABLE public."BookedRoomsForCourse";
       public         postgres    false            �            1259    17123 -   BookedRoomsForCourse_id_bookedRoomsCourse_seq    SEQUENCE     �   CREATE SEQUENCE public."BookedRoomsForCourse_id_bookedRoomsCourse_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 F   DROP SEQUENCE public."BookedRoomsForCourse_id_bookedRoomsCourse_seq";
       public       postgres    false    202            �           0    0 -   BookedRoomsForCourse_id_bookedRoomsCourse_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE public."BookedRoomsForCourse_id_bookedRoomsCourse_seq" OWNED BY public."BookedRoomsForCourse"."id_bookedRoomsCourse";
            public       postgres    false    203            �            1259    17125    Bookings    TABLE     �   CREATE TABLE public."Bookings" (
    teacher_for_booking text,
    course_for_booking text,
    bookings_id integer NOT NULL,
    period_for_booking integer,
    "studentsForBooking" integer,
    schedules_id integer
);
    DROP TABLE public."Bookings";
       public         postgres    false            �            1259    17131    Bookings_bookings_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Bookings_bookings_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public."Bookings_bookings_id_seq";
       public       postgres    false    204            �           0    0    Bookings_bookings_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public."Bookings_bookings_id_seq" OWNED BY public."Bookings".bookings_id;
            public       postgres    false    205            �            1259    17133    ClassesOfStudents    TABLE     �   CREATE TABLE public."ClassesOfStudents" (
    field_of_study text,
    "numberOfMembers" integer,
    classes_id text NOT NULL
);
 '   DROP TABLE public."ClassesOfStudents";
       public         postgres    false            �            1259    17139    Courses    TABLE     &  CREATE TABLE public."Courses" (
    "courseName" text,
    "numberOfParticipants" integer,
    "numberOfLessons" integer,
    "numberOfHoursTogether" integer,
    "maximalTimesOfWeek" integer,
    "desiredDaysBetweenLectures" integer,
    courses_id text NOT NULL,
    assigned_teacher text
);
    DROP TABLE public."Courses";
       public         postgres    false            �            1259    17145    Days    TABLE     �   CREATE TABLE public."Days" (
    "dateOfDay" date NOT NULL,
    "isHollyDay" boolean,
    "bestPeriod" text,
    "mediumPeriod" text,
    "emergencyPeriod" text
);
    DROP TABLE public."Days";
       public         postgres    false            �            1259    17151    DaysBookingPeriodes    TABLE     �   CREATE TABLE public."DaysBookingPeriodes" (
    "DaysBookinPeriodId" integer NOT NULL,
    daysbookinperiodename text,
    starthour text,
    endhour text,
    day text
);
 )   DROP TABLE public."DaysBookingPeriodes";
       public         postgres    false            �           0    0    TABLE "DaysBookingPeriodes"    COMMENT     �   COMMENT ON TABLE public."DaysBookingPeriodes" IS 'Contains the configure values for optimal booking periodes for different booking periodes';
            public       postgres    false    209            �            1259    17157 *   DaysBookingPeriodes_DaysBookinPeriodId_seq    SEQUENCE     �   CREATE SEQUENCE public."DaysBookingPeriodes_DaysBookinPeriodId_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 C   DROP SEQUENCE public."DaysBookingPeriodes_DaysBookinPeriodId_seq";
       public       postgres    false    209            �           0    0 *   DaysBookingPeriodes_DaysBookinPeriodId_seq    SEQUENCE OWNED BY        ALTER SEQUENCE public."DaysBookingPeriodes_DaysBookinPeriodId_seq" OWNED BY public."DaysBookingPeriodes"."DaysBookinPeriodId";
            public       postgres    false    210            �            1259    17159    NeighborAreasForRoom    TABLE     �   CREATE TABLE public."NeighborAreasForRoom" (
    areas_for_room text,
    id_neighbors integer NOT NULL,
    room_for_areas integer
);
 *   DROP TABLE public."NeighborAreasForRoom";
       public         postgres    false            �            1259    17165 %   NeighborAreasForRoom_id_neighbors_seq    SEQUENCE     �   CREATE SEQUENCE public."NeighborAreasForRoom_id_neighbors_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 >   DROP SEQUENCE public."NeighborAreasForRoom_id_neighbors_seq";
       public       postgres    false    211            �           0    0 %   NeighborAreasForRoom_id_neighbors_seq    SEQUENCE OWNED BY     s   ALTER SEQUENCE public."NeighborAreasForRoom_id_neighbors_seq" OWNED BY public."NeighborAreasForRoom".id_neighbors;
            public       postgres    false    212            �            1259    17167    Periods    TABLE     �   CREATE TABLE public."Periods" (
    "StartDate" timestamp without time zone,
    "FinishDate" timestamp without time zone,
    periods_id integer NOT NULL
);
    DROP TABLE public."Periods";
       public         postgres    false            �            1259    17170    Periods_periods_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Periods_periods_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public."Periods_periods_id_seq";
       public       postgres    false    213            �           0    0    Periods_periods_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public."Periods_periods_id_seq" OWNED BY public."Periods".periods_id;
            public       postgres    false    214            �            1259    17172    PossibleCoursesForTeacher    TABLE     �   CREATE TABLE public."PossibleCoursesForTeacher" (
    teacher_to_possible text,
    course_possible text,
    id_of_couse_to_teacher integer NOT NULL
);
 /   DROP TABLE public."PossibleCoursesForTeacher";
       public         postgres    false            �            1259    17178 4   PossibleCoursesForTeacher_id_of_couse_to_teacher_seq    SEQUENCE     �   CREATE SEQUENCE public."PossibleCoursesForTeacher_id_of_couse_to_teacher_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 M   DROP SEQUENCE public."PossibleCoursesForTeacher_id_of_couse_to_teacher_seq";
       public       postgres    false    215            �           0    0 4   PossibleCoursesForTeacher_id_of_couse_to_teacher_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE public."PossibleCoursesForTeacher_id_of_couse_to_teacher_seq" OWNED BY public."PossibleCoursesForTeacher".id_of_couse_to_teacher;
            public       postgres    false    216            �            1259    17180    Rooms    TABLE     �   CREATE TABLE public."Rooms" (
    rooms_name text,
    area text,
    "numberOfPlaces" integer,
    rooms_id integer NOT NULL
);
    DROP TABLE public."Rooms";
       public         postgres    false            �            1259    17186    RoomsAndBookings    TABLE     �   CREATE TABLE public."RoomsAndBookings" (
    id_rooms_for_bookings integer NOT NULL,
    room_for_courses integer,
    "bookingsToRooms" integer
);
 &   DROP TABLE public."RoomsAndBookings";
       public         postgres    false            �            1259    17189 *   RoomsAndBookings_id_rooms_for_bookings_seq    SEQUENCE     �   CREATE SEQUENCE public."RoomsAndBookings_id_rooms_for_bookings_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 C   DROP SEQUENCE public."RoomsAndBookings_id_rooms_for_bookings_seq";
       public       postgres    false    218            �           0    0 *   RoomsAndBookings_id_rooms_for_bookings_seq    SEQUENCE OWNED BY     }   ALTER SEQUENCE public."RoomsAndBookings_id_rooms_for_bookings_seq" OWNED BY public."RoomsAndBookings".id_rooms_for_bookings;
            public       postgres    false    219            �            1259    17191    Rooms_rooms_id_seq    SEQUENCE     }   CREATE SEQUENCE public."Rooms_rooms_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public."Rooms_rooms_id_seq";
       public       postgres    false    217            �           0    0    Rooms_rooms_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public."Rooms_rooms_id_seq" OWNED BY public."Rooms".rooms_id;
            public       postgres    false    220            �            1259    17193 	   Schedules    TABLE     _   CREATE TABLE public."Schedules" (
    schedules_id integer NOT NULL,
    periods_id integer
);
    DROP TABLE public."Schedules";
       public         postgres    false            �            1259    17196    Schedules_schedules_id_seq    SEQUENCE     �   CREATE SEQUENCE public."Schedules_schedules_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public."Schedules_schedules_id_seq";
       public       postgres    false    221            �           0    0    Schedules_schedules_id_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public."Schedules_schedules_id_seq" OWNED BY public."Schedules".schedules_id;
            public       postgres    false    222            �            1259    17198    StudentsForBookings    TABLE     �   CREATE TABLE public."StudentsForBookings" (
    id integer NOT NULL,
    "classOfStudents" text,
    "bookingForstudents" integer
);
 )   DROP TABLE public."StudentsForBookings";
       public         postgres    false            �            1259    17204    StudentsForBookings_id_seq    SEQUENCE     �   CREATE SEQUENCE public."StudentsForBookings_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public."StudentsForBookings_id_seq";
       public       postgres    false    223            �           0    0    StudentsForBookings_id_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public."StudentsForBookings_id_seq" OWNED BY public."StudentsForBookings".id;
            public       postgres    false    224            �            1259    17206    StudentsForCourses    TABLE     }   CREATE TABLE public."StudentsForCourses" (
    student text,
    course text,
    id_students_to_courses integer NOT NULL
);
 (   DROP TABLE public."StudentsForCourses";
       public         postgres    false            �            1259    17212 -   StudentsForCourses_id_students_to_courses_seq    SEQUENCE     �   CREATE SEQUENCE public."StudentsForCourses_id_students_to_courses_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 F   DROP SEQUENCE public."StudentsForCourses_id_students_to_courses_seq";
       public       postgres    false    225            �           0    0 -   StudentsForCourses_id_students_to_courses_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE public."StudentsForCourses_id_students_to_courses_seq" OWNED BY public."StudentsForCourses".id_students_to_courses;
            public       postgres    false    226            �            1259    17214    Teachers    TABLE     [   CREATE TABLE public."Teachers" (
    teachers_id text NOT NULL,
    "teachersName" text
);
    DROP TABLE public."Teachers";
       public         postgres    false            �            1259    17220    Users    TABLE     �   CREATE TABLE public."Users" (
    "Login" text NOT NULL,
    "Password" text,
    users_teacher text,
    name text,
    "isClassScheduler" boolean
);
    DROP TABLE public."Users";
       public         postgres    false            �
           2604    17226 5   BookedPeriodsForClassesOfStudents id_booking_students    DEFAULT     �   ALTER TABLE ONLY public."BookedPeriodsForClassesOfStudents" ALTER COLUMN id_booking_students SET DEFAULT nextval('public."BookedPeriodsForClassesOfStudents_id_booking_students_seq"'::regclass);
 f   ALTER TABLE public."BookedPeriodsForClassesOfStudents" ALTER COLUMN id_booking_students DROP DEFAULT;
       public       postgres    false    197    196            �
           2604    17227 %   BookedPeriodsForRoom id_booked_period    DEFAULT     �   ALTER TABLE ONLY public."BookedPeriodsForRoom" ALTER COLUMN id_booked_period SET DEFAULT nextval('public."BookedPeriodsForRoom_id_booked_period_seq"'::regclass);
 V   ALTER TABLE public."BookedPeriodsForRoom" ALTER COLUMN id_booked_period DROP DEFAULT;
       public       postgres    false    199    198            �
           2604    17228 -   BookedPeriodsForTeachers bookings_teachers_id    DEFAULT     �   ALTER TABLE ONLY public."BookedPeriodsForTeachers" ALTER COLUMN bookings_teachers_id SET DEFAULT nextval('public."BookedPeriodsForTeachers_bookings_teachers_id_seq"'::regclass);
 ^   ALTER TABLE public."BookedPeriodsForTeachers" ALTER COLUMN bookings_teachers_id DROP DEFAULT;
       public       postgres    false    201    200            �
           2604    17229 )   BookedRoomsForCourse id_bookedRoomsCourse    DEFAULT     �   ALTER TABLE ONLY public."BookedRoomsForCourse" ALTER COLUMN "id_bookedRoomsCourse" SET DEFAULT nextval('public."BookedRoomsForCourse_id_bookedRoomsCourse_seq"'::regclass);
 \   ALTER TABLE public."BookedRoomsForCourse" ALTER COLUMN "id_bookedRoomsCourse" DROP DEFAULT;
       public       postgres    false    203    202            �
           2604    17230    Bookings bookings_id    DEFAULT     �   ALTER TABLE ONLY public."Bookings" ALTER COLUMN bookings_id SET DEFAULT nextval('public."Bookings_bookings_id_seq"'::regclass);
 E   ALTER TABLE public."Bookings" ALTER COLUMN bookings_id DROP DEFAULT;
       public       postgres    false    205    204            �
           2604    17231 &   DaysBookingPeriodes DaysBookinPeriodId    DEFAULT     �   ALTER TABLE ONLY public."DaysBookingPeriodes" ALTER COLUMN "DaysBookinPeriodId" SET DEFAULT nextval('public."DaysBookingPeriodes_DaysBookinPeriodId_seq"'::regclass);
 Y   ALTER TABLE public."DaysBookingPeriodes" ALTER COLUMN "DaysBookinPeriodId" DROP DEFAULT;
       public       postgres    false    210    209            �
           2604    17232 !   NeighborAreasForRoom id_neighbors    DEFAULT     �   ALTER TABLE ONLY public."NeighborAreasForRoom" ALTER COLUMN id_neighbors SET DEFAULT nextval('public."NeighborAreasForRoom_id_neighbors_seq"'::regclass);
 R   ALTER TABLE public."NeighborAreasForRoom" ALTER COLUMN id_neighbors DROP DEFAULT;
       public       postgres    false    212    211            �
           2604    17233    Periods periods_id    DEFAULT     |   ALTER TABLE ONLY public."Periods" ALTER COLUMN periods_id SET DEFAULT nextval('public."Periods_periods_id_seq"'::regclass);
 C   ALTER TABLE public."Periods" ALTER COLUMN periods_id DROP DEFAULT;
       public       postgres    false    214    213            �
           2604    17234 0   PossibleCoursesForTeacher id_of_couse_to_teacher    DEFAULT     �   ALTER TABLE ONLY public."PossibleCoursesForTeacher" ALTER COLUMN id_of_couse_to_teacher SET DEFAULT nextval('public."PossibleCoursesForTeacher_id_of_couse_to_teacher_seq"'::regclass);
 a   ALTER TABLE public."PossibleCoursesForTeacher" ALTER COLUMN id_of_couse_to_teacher DROP DEFAULT;
       public       postgres    false    216    215            �
           2604    17235    Rooms rooms_id    DEFAULT     t   ALTER TABLE ONLY public."Rooms" ALTER COLUMN rooms_id SET DEFAULT nextval('public."Rooms_rooms_id_seq"'::regclass);
 ?   ALTER TABLE public."Rooms" ALTER COLUMN rooms_id DROP DEFAULT;
       public       postgres    false    220    217            �
           2604    17236 &   RoomsAndBookings id_rooms_for_bookings    DEFAULT     �   ALTER TABLE ONLY public."RoomsAndBookings" ALTER COLUMN id_rooms_for_bookings SET DEFAULT nextval('public."RoomsAndBookings_id_rooms_for_bookings_seq"'::regclass);
 W   ALTER TABLE public."RoomsAndBookings" ALTER COLUMN id_rooms_for_bookings DROP DEFAULT;
       public       postgres    false    219    218            �
           2604    17237    Schedules schedules_id    DEFAULT     �   ALTER TABLE ONLY public."Schedules" ALTER COLUMN schedules_id SET DEFAULT nextval('public."Schedules_schedules_id_seq"'::regclass);
 G   ALTER TABLE public."Schedules" ALTER COLUMN schedules_id DROP DEFAULT;
       public       postgres    false    222    221            �
           2604    17238    StudentsForBookings id    DEFAULT     �   ALTER TABLE ONLY public."StudentsForBookings" ALTER COLUMN id SET DEFAULT nextval('public."StudentsForBookings_id_seq"'::regclass);
 G   ALTER TABLE public."StudentsForBookings" ALTER COLUMN id DROP DEFAULT;
       public       postgres    false    224    223            �
           2604    17239 )   StudentsForCourses id_students_to_courses    DEFAULT     �   ALTER TABLE ONLY public."StudentsForCourses" ALTER COLUMN id_students_to_courses SET DEFAULT nextval('public."StudentsForCourses_id_students_to_courses_seq"'::regclass);
 Z   ALTER TABLE public."StudentsForCourses" ALTER COLUMN id_students_to_courses DROP DEFAULT;
       public       postgres    false    226    225            �          0    17096 !   BookedPeriodsForClassesOfStudents 
   TABLE DATA               w   COPY public."BookedPeriodsForClassesOfStudents" ("bookingsPeriod", "classOfStudents", id_booking_students) FROM stdin;
    public       postgres    false    196   N�       �          0    17104    BookedPeriodsForRoom 
   TABLE DATA               g   COPY public."BookedPeriodsForRoom" (id_booked_period, room_for_period, period_for_booking) FROM stdin;
    public       postgres    false    198   k�       �          0    17109    BookedPeriodsForTeachers 
   TABLE DATA               x   COPY public."BookedPeriodsForTeachers" (periods_bookings_teacher, teacher_of_booking, bookings_teachers_id) FROM stdin;
    public       postgres    false    200   ��       �          0    17117    BookedRoomsForCourse 
   TABLE DATA               a   COPY public."BookedRoomsForCourse" ("courseInRoom", "id_bookedRoomsCourse", fk_room) FROM stdin;
    public       postgres    false    202   ��       �          0    17125    Bookings 
   TABLE DATA               �   COPY public."Bookings" (teacher_for_booking, course_for_booking, bookings_id, period_for_booking, "studentsForBooking", schedules_id) FROM stdin;
    public       postgres    false    204   ��       �          0    17133    ClassesOfStudents 
   TABLE DATA               \   COPY public."ClassesOfStudents" (field_of_study, "numberOfMembers", classes_id) FROM stdin;
    public       postgres    false    206   ��       �          0    17139    Courses 
   TABLE DATA               �   COPY public."Courses" ("courseName", "numberOfParticipants", "numberOfLessons", "numberOfHoursTogether", "maximalTimesOfWeek", "desiredDaysBetweenLectures", courses_id, assigned_teacher) FROM stdin;
    public       postgres    false    207   ��       �          0    17145    Days 
   TABLE DATA               l   COPY public."Days" ("dateOfDay", "isHollyDay", "bestPeriod", "mediumPeriod", "emergencyPeriod") FROM stdin;
    public       postgres    false    208   7�       �          0    17151    DaysBookingPeriodes 
   TABLE DATA               u   COPY public."DaysBookingPeriodes" ("DaysBookinPeriodId", daysbookinperiodename, starthour, endhour, day) FROM stdin;
    public       postgres    false    209   T�       �          0    17159    NeighborAreasForRoom 
   TABLE DATA               ^   COPY public."NeighborAreasForRoom" (areas_for_room, id_neighbors, room_for_areas) FROM stdin;
    public       postgres    false    211   '�       �          0    17167    Periods 
   TABLE DATA               J   COPY public."Periods" ("StartDate", "FinishDate", periods_id) FROM stdin;
    public       postgres    false    213   f�       �          0    17172    PossibleCoursesForTeacher 
   TABLE DATA               s   COPY public."PossibleCoursesForTeacher" (teacher_to_possible, course_possible, id_of_couse_to_teacher) FROM stdin;
    public       postgres    false    215   ��       �          0    17180    Rooms 
   TABLE DATA               O   COPY public."Rooms" (rooms_name, area, "numberOfPlaces", rooms_id) FROM stdin;
    public       postgres    false    217   ��       �          0    17186    RoomsAndBookings 
   TABLE DATA               h   COPY public."RoomsAndBookings" (id_rooms_for_bookings, room_for_courses, "bookingsToRooms") FROM stdin;
    public       postgres    false    218   w�       �          0    17193 	   Schedules 
   TABLE DATA               ?   COPY public."Schedules" (schedules_id, periods_id) FROM stdin;
    public       postgres    false    221   ��       �          0    17198    StudentsForBookings 
   TABLE DATA               \   COPY public."StudentsForBookings" (id, "classOfStudents", "bookingForstudents") FROM stdin;
    public       postgres    false    223   ��       �          0    17206    StudentsForCourses 
   TABLE DATA               W   COPY public."StudentsForCourses" (student, course, id_students_to_courses) FROM stdin;
    public       postgres    false    225   ��       �          0    17214    Teachers 
   TABLE DATA               A   COPY public."Teachers" (teachers_id, "teachersName") FROM stdin;
    public       postgres    false    227   ��       �          0    17220    Users 
   TABLE DATA               _   COPY public."Users" ("Login", "Password", users_teacher, name, "isClassScheduler") FROM stdin;
    public       postgres    false    228   |�       �           0    0 9   BookedPeriodsForClassesOfStudents_id_booking_students_seq    SEQUENCE SET     l   SELECT pg_catalog.setval('public."BookedPeriodsForClassesOfStudents_id_booking_students_seq"', 1191, true);
            public       postgres    false    197            �           0    0 )   BookedPeriodsForRoom_id_booked_period_seq    SEQUENCE SET     [   SELECT pg_catalog.setval('public."BookedPeriodsForRoom_id_booked_period_seq"', 185, true);
            public       postgres    false    199            �           0    0 1   BookedPeriodsForTeachers_bookings_teachers_id_seq    SEQUENCE SET     d   SELECT pg_catalog.setval('public."BookedPeriodsForTeachers_bookings_teachers_id_seq"', 2149, true);
            public       postgres    false    201            �           0    0 -   BookedRoomsForCourse_id_bookedRoomsCourse_seq    SEQUENCE SET     _   SELECT pg_catalog.setval('public."BookedRoomsForCourse_id_bookedRoomsCourse_seq"', 185, true);
            public       postgres    false    203            �           0    0    Bookings_bookings_id_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public."Bookings_bookings_id_seq"', 2149, true);
            public       postgres    false    205                        0    0 *   DaysBookingPeriodes_DaysBookinPeriodId_seq    SEQUENCE SET     [   SELECT pg_catalog.setval('public."DaysBookingPeriodes_DaysBookinPeriodId_seq"', 24, true);
            public       postgres    false    210                       0    0 %   NeighborAreasForRoom_id_neighbors_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('public."NeighborAreasForRoom_id_neighbors_seq"', 1, true);
            public       postgres    false    212                       0    0    Periods_periods_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public."Periods_periods_id_seq"', 2210, true);
            public       postgres    false    214                       0    0 4   PossibleCoursesForTeacher_id_of_couse_to_teacher_seq    SEQUENCE SET     e   SELECT pg_catalog.setval('public."PossibleCoursesForTeacher_id_of_couse_to_teacher_seq"', 1, false);
            public       postgres    false    216                       0    0 *   RoomsAndBookings_id_rooms_for_bookings_seq    SEQUENCE SET     \   SELECT pg_catalog.setval('public."RoomsAndBookings_id_rooms_for_bookings_seq"', 185, true);
            public       postgres    false    219                       0    0    Rooms_rooms_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public."Rooms_rooms_id_seq"', 20, true);
            public       postgres    false    220                       0    0    Schedules_schedules_id_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public."Schedules_schedules_id_seq"', 48, true);
            public       postgres    false    222                       0    0    StudentsForBookings_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public."StudentsForBookings_id_seq"', 1192, true);
            public       postgres    false    224                       0    0 -   StudentsForCourses_id_students_to_courses_seq    SEQUENCE SET     ^   SELECT pg_catalog.setval('public."StudentsForCourses_id_students_to_courses_seq"', 1, false);
            public       postgres    false    226            :           2606    17241    Teachers Teachers_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public."Teachers"
    ADD CONSTRAINT "Teachers_pkey" PRIMARY KEY (teachers_id);
 D   ALTER TABLE ONLY public."Teachers" DROP CONSTRAINT "Teachers_pkey";
       public         postgres    false    227            <           2606    17243    Users Users_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT "Users_pkey" PRIMARY KEY ("Login");
 >   ALTER TABLE ONLY public."Users" DROP CONSTRAINT "Users_pkey";
       public         postgres    false    228            �
           2606    17245 7   BookedPeriodsForClassesOfStudents pk_bookedperiodsclass 
   CONSTRAINT     �   ALTER TABLE ONLY public."BookedPeriodsForClassesOfStudents"
    ADD CONSTRAINT pk_bookedperiodsclass PRIMARY KEY (id_booking_students);
 c   ALTER TABLE ONLY public."BookedPeriodsForClassesOfStudents" DROP CONSTRAINT pk_bookedperiodsclass;
       public         postgres    false    196                       2606    17247 )   BookedPeriodsForRoom pk_bookedperiodsroom 
   CONSTRAINT     w   ALTER TABLE ONLY public."BookedPeriodsForRoom"
    ADD CONSTRAINT pk_bookedperiodsroom PRIMARY KEY (id_booked_period);
 U   ALTER TABLE ONLY public."BookedPeriodsForRoom" DROP CONSTRAINT pk_bookedperiodsroom;
       public         postgres    false    198                       2606    17249 0   BookedPeriodsForTeachers pk_bookedperiodsteacher 
   CONSTRAINT     �   ALTER TABLE ONLY public."BookedPeriodsForTeachers"
    ADD CONSTRAINT pk_bookedperiodsteacher PRIMARY KEY (bookings_teachers_id);
 \   ALTER TABLE ONLY public."BookedPeriodsForTeachers" DROP CONSTRAINT pk_bookedperiodsteacher;
       public         postgres    false    200            
           2606    17251 #   BookedRoomsForCourse pk_bookedrooms 
   CONSTRAINT     w   ALTER TABLE ONLY public."BookedRoomsForCourse"
    ADD CONSTRAINT pk_bookedrooms PRIMARY KEY ("id_bookedRoomsCourse");
 O   ALTER TABLE ONLY public."BookedRoomsForCourse" DROP CONSTRAINT pk_bookedrooms;
       public         postgres    false    202                       2606    17253    Bookings pk_bookings 
   CONSTRAINT     ]   ALTER TABLE ONLY public."Bookings"
    ADD CONSTRAINT pk_bookings PRIMARY KEY (bookings_id);
 @   ALTER TABLE ONLY public."Bookings" DROP CONSTRAINT pk_bookings;
       public         postgres    false    204                       2606    17255 $   ClassesOfStudents pk_classOfstudents 
   CONSTRAINT     n   ALTER TABLE ONLY public."ClassesOfStudents"
    ADD CONSTRAINT "pk_classOfstudents" PRIMARY KEY (classes_id);
 R   ALTER TABLE ONLY public."ClassesOfStudents" DROP CONSTRAINT "pk_classOfstudents";
       public         postgres    false    206                       2606    17257    Courses pk_courses 
   CONSTRAINT     Z   ALTER TABLE ONLY public."Courses"
    ADD CONSTRAINT pk_courses PRIMARY KEY (courses_id);
 >   ALTER TABLE ONLY public."Courses" DROP CONSTRAINT pk_courses;
       public         postgres    false    207                       2606    17259    Days pk_day 
   CONSTRAINT     T   ALTER TABLE ONLY public."Days"
    ADD CONSTRAINT pk_day PRIMARY KEY ("dateOfDay");
 7   ALTER TABLE ONLY public."Days" DROP CONSTRAINT pk_day;
       public         postgres    false    208                       2606    17261    DaysBookingPeriodes pk_dbp 
   CONSTRAINT     l   ALTER TABLE ONLY public."DaysBookingPeriodes"
    ADD CONSTRAINT pk_dbp PRIMARY KEY ("DaysBookinPeriodId");
 F   ALTER TABLE ONLY public."DaysBookingPeriodes" DROP CONSTRAINT pk_dbp;
       public         postgres    false    209            "           2606    17263     NeighborAreasForRoom pk_neighbor 
   CONSTRAINT     j   ALTER TABLE ONLY public."NeighborAreasForRoom"
    ADD CONSTRAINT pk_neighbor PRIMARY KEY (id_neighbors);
 L   ALTER TABLE ONLY public."NeighborAreasForRoom" DROP CONSTRAINT pk_neighbor;
       public         postgres    false    211            $           2606    17265    Periods pk_periods 
   CONSTRAINT     Z   ALTER TABLE ONLY public."Periods"
    ADD CONSTRAINT pk_periods PRIMARY KEY (periods_id);
 >   ALTER TABLE ONLY public."Periods" DROP CONSTRAINT pk_periods;
       public         postgres    false    213            (           2606    17267 6   PossibleCoursesForTeacher pk_possiblecoursesForTeacher 
   CONSTRAINT     �   ALTER TABLE ONLY public."PossibleCoursesForTeacher"
    ADD CONSTRAINT "pk_possiblecoursesForTeacher" PRIMARY KEY (id_of_couse_to_teacher);
 d   ALTER TABLE ONLY public."PossibleCoursesForTeacher" DROP CONSTRAINT "pk_possiblecoursesForTeacher";
       public         postgres    false    215            *           2606    17269    Rooms pk_room 
   CONSTRAINT     S   ALTER TABLE ONLY public."Rooms"
    ADD CONSTRAINT pk_room PRIMARY KEY (rooms_id);
 9   ALTER TABLE ONLY public."Rooms" DROP CONSTRAINT pk_room;
       public         postgres    false    217            -           2606    17271 #   RoomsAndBookings pk_roomsandbokings 
   CONSTRAINT     v   ALTER TABLE ONLY public."RoomsAndBookings"
    ADD CONSTRAINT pk_roomsandbokings PRIMARY KEY (id_rooms_for_bookings);
 O   ALTER TABLE ONLY public."RoomsAndBookings" DROP CONSTRAINT pk_roomsandbokings;
       public         postgres    false    218            0           2606    17273    Schedules pk_schedule 
   CONSTRAINT     _   ALTER TABLE ONLY public."Schedules"
    ADD CONSTRAINT pk_schedule PRIMARY KEY (schedules_id);
 A   ALTER TABLE ONLY public."Schedules" DROP CONSTRAINT pk_schedule;
       public         postgres    false    221            8           2606    17275    StudentsForCourses pk_students 
   CONSTRAINT     r   ALTER TABLE ONLY public."StudentsForCourses"
    ADD CONSTRAINT pk_students PRIMARY KEY (id_students_to_courses);
 J   ALTER TABLE ONLY public."StudentsForCourses" DROP CONSTRAINT pk_students;
       public         postgres    false    225            4           2606    17277 *   StudentsForBookings pk_studentsForBookings 
   CONSTRAINT     l   ALTER TABLE ONLY public."StudentsForBookings"
    ADD CONSTRAINT "pk_studentsForBookings" PRIMARY KEY (id);
 X   ALTER TABLE ONLY public."StudentsForBookings" DROP CONSTRAINT "pk_studentsForBookings";
       public         postgres    false    223                       1259    17278    fki_best_period    INDEX     J   CREATE INDEX fki_best_period ON public."Days" USING btree ("bestPeriod");
 #   DROP INDEX public.fki_best_period;
       public         postgres    false    208            �
           1259    17279    fki_booking_class    INDEX     n   CREATE INDEX fki_booking_class ON public."BookedPeriodsForClassesOfStudents" USING btree ("classOfStudents");
 %   DROP INDEX public.fki_booking_class;
       public         postgres    false    196            �
           1259    17280    fki_booking_students_period    INDEX     w   CREATE INDEX fki_booking_students_period ON public."BookedPeriodsForClassesOfStudents" USING btree ("bookingsPeriod");
 /   DROP INDEX public.fki_booking_students_period;
       public         postgres    false    196            1           1259    17281    fki_bookings_students    INDEX     g   CREATE INDEX fki_bookings_students ON public."StudentsForBookings" USING btree ("bookingForstudents");
 )   DROP INDEX public.fki_bookings_students;
       public         postgres    false    223                       1259    17282    fki_courseInRoom    INDEX     _   CREATE INDEX "fki_courseInRoom" ON public."BookedRoomsForCourse" USING btree ("courseInRoom");
 &   DROP INDEX public."fki_courseInRoom";
       public         postgres    false    202                       1259    17283    fki_course_for_booking    INDEX     [   CREATE INDEX fki_course_for_booking ON public."Bookings" USING btree (course_for_booking);
 *   DROP INDEX public.fki_course_for_booking;
       public         postgres    false    204                       1259    17284    fki_course_for_bookings    INDEX     \   CREATE INDEX fki_course_for_bookings ON public."Bookings" USING btree (course_for_booking);
 +   DROP INDEX public.fki_course_for_bookings;
       public         postgres    false    204            5           1259    17285    fki_courses_students    INDEX     W   CREATE INDEX fki_courses_students ON public."StudentsForCourses" USING btree (course);
 (   DROP INDEX public.fki_courses_students;
       public         postgres    false    225                       1259    17286    fki_emergency_period    INDEX     T   CREATE INDEX fki_emergency_period ON public."Days" USING btree ("emergencyPeriod");
 (   DROP INDEX public.fki_emergency_period;
       public         postgres    false    208                       1259    17287    fki_fkTeacherBooking    INDEX     \   CREATE INDEX "fki_fkTeacherBooking" ON public."Bookings" USING btree (teacher_for_booking);
 *   DROP INDEX public."fki_fkTeacherBooking";
       public         postgres    false    204                       1259    17288    fki_medium_period    INDEX     N   CREATE INDEX fki_medium_period ON public."Days" USING btree ("mediumPeriod");
 %   DROP INDEX public.fki_medium_period;
       public         postgres    false    208                       1259    17289    fki_period_for_booking    INDEX     [   CREATE INDEX fki_period_for_booking ON public."Bookings" USING btree (period_for_booking);
 *   DROP INDEX public.fki_period_for_booking;
       public         postgres    false    204            .           1259    17290    fki_periods_to_schedule    INDEX     U   CREATE INDEX fki_periods_to_schedule ON public."Schedules" USING btree (periods_id);
 +   DROP INDEX public.fki_periods_to_schedule;
       public         postgres    false    221                       1259    17291    fki_pk_period_teacher_booking    INDEX     x   CREATE INDEX fki_pk_period_teacher_booking ON public."BookedPeriodsForTeachers" USING btree (periods_bookings_teacher);
 1   DROP INDEX public.fki_pk_period_teacher_booking;
       public         postgres    false    200            %           1259    17292    fki_possible_courses    INDEX     g   CREATE INDEX fki_possible_courses ON public."PossibleCoursesForTeacher" USING btree (course_possible);
 (   DROP INDEX public.fki_possible_courses;
       public         postgres    false    215                        1259    17293    fki_room_booked    INDEX     ]   CREATE INDEX fki_room_booked ON public."BookedPeriodsForRoom" USING btree (room_for_period);
 #   DROP INDEX public.fki_room_booked;
       public         postgres    false    198                        1259    17294    fki_room_for_neighbor    INDEX     b   CREATE INDEX fki_room_for_neighbor ON public."NeighborAreasForRoom" USING btree (room_for_areas);
 )   DROP INDEX public.fki_room_for_neighbor;
       public         postgres    false    211                       1259    17295    fki_room_periods    INDEX     V   CREATE INDEX fki_room_periods ON public."BookedRoomsForCourse" USING btree (fk_room);
 $   DROP INDEX public.fki_room_periods;
       public         postgres    false    202            +           1259    17296    fki_room_to_bookings    INDEX     _   CREATE INDEX fki_room_to_bookings ON public."RoomsAndBookings" USING btree (room_for_courses);
 (   DROP INDEX public.fki_room_to_bookings;
       public         postgres    false    218                       1259    17297    fki_schedules_id    INDEX     O   CREATE INDEX fki_schedules_id ON public."Bookings" USING btree (schedules_id);
 $   DROP INDEX public.fki_schedules_id;
       public         postgres    false    204            6           1259    17298    fki_students    INDEX     P   CREATE INDEX fki_students ON public."StudentsForCourses" USING btree (student);
     DROP INDEX public.fki_students;
       public         postgres    false    225                       1259    17299    fki_students_booking    INDEX     [   CREATE INDEX fki_students_booking ON public."Bookings" USING btree ("studentsForBooking");
 (   DROP INDEX public.fki_students_booking;
       public         postgres    false    204            2           1259    17300    fki_students_bookings    INDEX     d   CREATE INDEX fki_students_bookings ON public."StudentsForBookings" USING btree ("classOfStudents");
 )   DROP INDEX public.fki_students_bookings;
       public         postgres    false    223            =           1259    17301    fki_teacher    INDEX     H   CREATE INDEX fki_teacher ON public."Users" USING btree (users_teacher);
    DROP INDEX public.fki_teacher;
       public         postgres    false    228                       1259    17302    fki_teacher_booking    INDEX     h   CREATE INDEX fki_teacher_booking ON public."BookedPeriodsForTeachers" USING btree (teacher_of_booking);
 '   DROP INDEX public.fki_teacher_booking;
       public         postgres    false    200                       1259    17303    fki_teacher_course    INDEX     T   CREATE INDEX fki_teacher_course ON public."Courses" USING btree (assigned_teacher);
 &   DROP INDEX public.fki_teacher_course;
       public         postgres    false    207                       1259    17304    fki_teacher_for_booking    INDEX     ]   CREATE INDEX fki_teacher_for_booking ON public."Bookings" USING btree (teacher_for_booking);
 +   DROP INDEX public.fki_teacher_for_booking;
       public         postgres    false    204            &           1259    17305    fki_teacher_to_possible    INDEX     n   CREATE INDEX fki_teacher_to_possible ON public."PossibleCoursesForTeacher" USING btree (teacher_to_possible);
 +   DROP INDEX public.fki_teacher_to_possible;
       public         postgres    false    215            I           2606    17306 (   StudentsForBookings fk_bookings_students    FK CONSTRAINT     �   ALTER TABLE ONLY public."StudentsForBookings"
    ADD CONSTRAINT fk_bookings_students FOREIGN KEY ("bookingForstudents") REFERENCES public."Bookings"(bookings_id);
 T   ALTER TABLE ONLY public."StudentsForBookings" DROP CONSTRAINT fk_bookings_students;
       public       postgres    false    223    204    2835            @           2606    17311    Bookings fk_course_for_bookings    FK CONSTRAINT     �   ALTER TABLE ONLY public."Bookings"
    ADD CONSTRAINT fk_course_for_bookings FOREIGN KEY (course_for_booking) REFERENCES public."Courses"(courses_id);
 K   ALTER TABLE ONLY public."Bookings" DROP CONSTRAINT fk_course_for_bookings;
       public       postgres    false    204    207    2840            A           2606    17316    Bookings fk_period_for_booking    FK CONSTRAINT     �   ALTER TABLE ONLY public."Bookings"
    ADD CONSTRAINT fk_period_for_booking FOREIGN KEY (period_for_booking) REFERENCES public."Periods"(periods_id);
 J   ALTER TABLE ONLY public."Bookings" DROP CONSTRAINT fk_period_for_booking;
       public       postgres    false    2852    213    204            H           2606    17321     Schedules fk_periods_to_schedule    FK CONSTRAINT     �   ALTER TABLE ONLY public."Schedules"
    ADD CONSTRAINT fk_periods_to_schedule FOREIGN KEY (periods_id) REFERENCES public."Periods"(periods_id);
 L   ALTER TABLE ONLY public."Schedules" DROP CONSTRAINT fk_periods_to_schedule;
       public       postgres    false    221    2852    213            >           2606    17326 #   BookedPeriodsForRoom fk_room_booked    FK CONSTRAINT     �   ALTER TABLE ONLY public."BookedPeriodsForRoom"
    ADD CONSTRAINT fk_room_booked FOREIGN KEY (room_for_period) REFERENCES public."Rooms"(rooms_id);
 O   ALTER TABLE ONLY public."BookedPeriodsForRoom" DROP CONSTRAINT fk_room_booked;
       public       postgres    false    2858    217    198            F           2606    17331 )   NeighborAreasForRoom fk_room_for_neighbor    FK CONSTRAINT     �   ALTER TABLE ONLY public."NeighborAreasForRoom"
    ADD CONSTRAINT fk_room_for_neighbor FOREIGN KEY (room_for_areas) REFERENCES public."Rooms"(rooms_id);
 U   ALTER TABLE ONLY public."NeighborAreasForRoom" DROP CONSTRAINT fk_room_for_neighbor;
       public       postgres    false    2858    211    217            ?           2606    17336 $   BookedRoomsForCourse fk_room_periods    FK CONSTRAINT     �   ALTER TABLE ONLY public."BookedRoomsForCourse"
    ADD CONSTRAINT fk_room_periods FOREIGN KEY (fk_room) REFERENCES public."Rooms"(rooms_id);
 P   ALTER TABLE ONLY public."BookedRoomsForCourse" DROP CONSTRAINT fk_room_periods;
       public       postgres    false    202    2858    217            G           2606    17341 $   RoomsAndBookings fk_room_to_bookings    FK CONSTRAINT     �   ALTER TABLE ONLY public."RoomsAndBookings"
    ADD CONSTRAINT fk_room_to_bookings FOREIGN KEY (room_for_courses) REFERENCES public."Rooms"(rooms_id);
 P   ALTER TABLE ONLY public."RoomsAndBookings" DROP CONSTRAINT fk_room_to_bookings;
       public       postgres    false    218    2858    217            B           2606    17346    Bookings fk_schedules_id    FK CONSTRAINT     �   ALTER TABLE ONLY public."Bookings"
    ADD CONSTRAINT fk_schedules_id FOREIGN KEY (schedules_id) REFERENCES public."Schedules"(schedules_id);
 D   ALTER TABLE ONLY public."Bookings" DROP CONSTRAINT fk_schedules_id;
       public       postgres    false    2864    204    221            C           2606    17351    Bookings fk_students_booking    FK CONSTRAINT     �   ALTER TABLE ONLY public."Bookings"
    ADD CONSTRAINT fk_students_booking FOREIGN KEY ("studentsForBooking") REFERENCES public."StudentsForBookings"(id);
 H   ALTER TABLE ONLY public."Bookings" DROP CONSTRAINT fk_students_booking;
       public       postgres    false    223    204    2868            J           2606    17356 (   StudentsForBookings fk_students_bookings    FK CONSTRAINT     �   ALTER TABLE ONLY public."StudentsForBookings"
    ADD CONSTRAINT fk_students_bookings FOREIGN KEY ("classOfStudents") REFERENCES public."ClassesOfStudents"(classes_id);
 T   ALTER TABLE ONLY public."StudentsForBookings" DROP CONSTRAINT fk_students_bookings;
       public       postgres    false    206    223    2837            E           2606    17361    Courses fk_teacher_course    FK CONSTRAINT     �   ALTER TABLE ONLY public."Courses"
    ADD CONSTRAINT fk_teacher_course FOREIGN KEY (assigned_teacher) REFERENCES public."Teachers"(teachers_id);
 E   ALTER TABLE ONLY public."Courses" DROP CONSTRAINT fk_teacher_course;
       public       postgres    false    2874    227    207            D           2606    17366    Bookings fk_teacher_for_booking    FK CONSTRAINT     �   ALTER TABLE ONLY public."Bookings"
    ADD CONSTRAINT fk_teacher_for_booking FOREIGN KEY (teacher_for_booking) REFERENCES public."Teachers"(teachers_id);
 K   ALTER TABLE ONLY public."Bookings" DROP CONSTRAINT fk_teacher_for_booking;
       public       postgres    false    227    2874    204            K           2606    17371    Users teacher    FK CONSTRAINT     �   ALTER TABLE ONLY public."Users"
    ADD CONSTRAINT teacher FOREIGN KEY (users_teacher) REFERENCES public."Teachers"(teachers_id);
 9   ALTER TABLE ONLY public."Users" DROP CONSTRAINT teacher;
       public       postgres    false    2874    227    228            �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �   �   x�m�AN�0E��)����I�� �@,;K6��U��J� ��I���l������y�~�^��q�T.u���h�:��i>�cg��d��G��y�����y��~�?�)�[��xϨ)�@��@ޘݳ�<}���
IY�t�}*L���WF����%�Ye	�j�+r�H<�
��ʒ�#�-��$�d-���w�.�:%JD�f[6OTU;��]���.�R
��� ��1��S��I1r�y"޼<c~����      �   ?  x�m��n�0�g�)�)DZ�c;'@��]TWm 6���A**�2x�>��N�ŷi��4�hڃ�o���8*��a��iX6�8��tI������q�X~�6�x�8�&�B�������@a&���{K��W�Iس��t#�zC�b�܍����Z�<�kwmZ����̍�dJe�rb�)K�^�m jE��J^o�Ut#mI�3�xS;�N0##rʡ3Hڷ��]M�^@+`�5�U=���ˤ5�A�Q�0^$�Ik�q�*j��ne��U$ހ�e�/��A@RER����� ������Qٕ �6��^��_����2���      �      x������ � �      �   �   x�}��� ���a���=Ğ�7��\��ŷ�PPt)I/������Ȅ���OF�C�L<#��53q����"�S��t�_1�mg�� u��l� Jaa�4��, ���`{W.�֌ָj��.$����F���>mc�8�-�X�X��V�U�󾥈���Z]H����<VE���)n�$��2�_�8�_�5�K      �   /   x���/KM��KIO�K�4�4��@0�4B0�4F0�4����� Vje      �      x������ � �      �   !   x��J�300���4�ʂ�}�8��b���� pbG      �   �   x�E��
AE�;#I�Z���FXwAX��0�2��ܓ��r8���a���(a�	��s�N����$B�Ƽ�X.�������	N��IdYQ:INjE�%�A͉��o%'v��3�:QSf'�K#X:�q���7	%SK���Iz�χ\�P�������چu+N2ح�e��p݅���Z�      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �   �   x�m�;�@�z}�=Ad�騐��1��p�������PPy�l�������0���2���;�O�}P��m�p��N�d�Ҳ̦���aMl+a;Xk`-L`5��c%L`-��U�oO���Z��9V�      �   Z   x��J��L�/JˬH���J�300��J�Sp��)�Q��W�����I,-J�,�O�L,�9}@|��L�|JbF�B@jIjHq	W� ?� 0     