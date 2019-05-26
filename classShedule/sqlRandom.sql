Random rooms generation:

INSERT INTO public."Rooms"(
	rooms_name, area, "numberOfPlaces")
	VALUES ('rt' || floor(random()*(250-25+1))+25, 'uni', floor(random()*(250-25+1))+25);
	
Dokumentation: https://www.techonthenet.com/postgresql/functions/random.php

Random course:

INSERT INTO public."Courses"(
	"courseName", "numberOfParticipants", "numberOfLessons", "numberOfHoursTogether", "maximalTimesOfWeek", "desiredDaysBetweenLectures", courses_id, assigned_teacher)
	VALUES ('tcourse-' || floor(random()*(250-25+1))+25, floor(random()*(250-25+1))+25, 48, 4, floor(random()*(2-1+1))+1, floor(random()*(2-1+1))+1,'cid-' ||floor(random()*(250-25+1))+25 , 'jan0' || floor(random()*(10-1+1))+1);
	
Random students:

INSERT INTO public."ClassesOfStudents"(
	field_of_study, "numberOfMembers", classes_id)
	VALUES ('fieldofstudy-' || floor(random()*(250-25+1))+25, 'tcourse-' || floor(random()*(250-25+1))+25, 'SE');
	

Random teacher:

INSERT INTO public."Teachers"(
	teachers_id, "teachersName")
	VALUES ("jan0" || floor(random()*(10-1+1))+1, "jan0" || floor(random()*(10-1+1))+1);