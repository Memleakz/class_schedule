/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Business_Logic.Common.Period;
import Business_Logic.Common.Schedule;
import Business_Logic.Days.ClasificationFactory;
import Business_Logic.Days.Day;
import Business_Logic.IServices.ClasificationInterface;
import Business_Logic.IServices.TeacherInterface;
import Business_Logic.Responsible.LecturerFactory;
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
import org.mockito.Mockito.*; 
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;

/**
 *
 * @author lawar15
 */
public class LecturersUnitTest {
    
    public LecturersUnitTest() {
    }


@org.junit.Test
	public void testisLecturerAvalible() {
	    String name = "Anna Niewierzalewska";
	    String id= "997";
	    Period period = Mockito.mock(Period.class);
	    Mockito.when(period.getStartDate()).thenReturn(LocalDateTime.of(2019, Month.MAY, 07, 8, 00, 00));
	    Mockito.when(period.getEndDate()).thenReturn(LocalDateTime.of(2019, Month.MAY, 07, 12, 00, 00));
	    List<Period> bookedPeriods = new ArrayList<Period>();	    
	    TeacherInterface teacher = (TeacherInterface) LecturerFactory.getTeacher(id, name, bookedPeriods, null);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	    LocalDateTime dateStartTime = LocalDateTime.of(2019, Month.MAY, 07, 13, 0, 0);
	    String formattedStartDateTime = dateStartTime.format(formatter);
	    LocalDateTime dateEndTime = LocalDateTime.of(2019, Month.MAY, 07, 16, 0, 0);
	    String formattedEndDateTime = dateEndTime.format(formatter);
	    Boolean avail = teacher.isTeacherAvailable(formattedStartDateTime,formattedEndDateTime);
	    Assert.assertTrue(avail);
	
	    
	}
}
