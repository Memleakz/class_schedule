/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.Days;

import Business_Logic.Common.Period;
import Business_Logic.IServices.ClasificationInterface;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author lawar15
 */

public class Day implements ClasificationInterface{
    private Map<String, String[]> bestTimeForDay;
    private Map<String, String[]> mediumTimeForDay;
    private Map<String, String[]> emergencyTimeForDay;
    private final Period bestPeriod;
    private final Period mediumPeriod;
    private final Period emergencyPeriod;
    private final LocalDateTime dateOfTheDay;
    private boolean hollyDay;

  public Day(LocalDateTime dateOfTheDay,Map<String, String[]> bestTimeForDay,Map<String, String[]> mediumTimeForDay, Map<String, String[]> emergencyTimeForDay ){
 
      
      this.dateOfTheDay = dateOfTheDay;
      this.bestTimeForDay = bestTimeForDay;
      this.mediumTimeForDay = mediumTimeForDay;
      this.emergencyTimeForDay = emergencyTimeForDay;
      
      this.bestPeriod = this.calculateBestPeriodOfTheDay(dateOfTheDay);
      this.mediumPeriod = this.calculateMediumPeriodOfTheDay(dateOfTheDay);
      this.emergencyPeriod = this.calculateEmergencyPeriodOgTheDay(dateOfTheDay);
  }
    @Override
  public Period getBestPeriod(){
      return this.bestPeriod;
  }
    @Override
  public Period getMediumPeriod(){
      return this.mediumPeriod;
  }
    @Override
  public Period getEmergencyPeriod(){
      return this.emergencyPeriod;
  }
  public boolean isThisAHollyDay(){
      return this.hollyDay;
  }
private Period calculateBestPeriodOfTheDay(LocalDateTime date){
Date convertedDateOfTheDay = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
Calendar calendar = Calendar.getInstance();
calendar.setTime(convertedDateOfTheDay);
String stringOfTheDate = new SimpleDateFormat("dd/MM/yyyy").format(convertedDateOfTheDay);
int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
switch(dayOfWeek) {
   case Calendar.MONDAY :
       String[] timesformonaday =bestTimeForDay.get("monday");  
      return new Period(stringOfTheDate+" "+timesformonaday[0],stringOfTheDate+" "+timesformonaday[1]);
   case Calendar.TUESDAY :
       String[] timesfortuesday =bestTimeForDay.get("tuesday");
      return new Period(stringOfTheDate+" "+timesfortuesday[0],stringOfTheDate+" "+timesfortuesday[1]);
   case Calendar.WEDNESDAY :
        String[] timesforwednesday =bestTimeForDay.get("wednesday");
      return new Period(stringOfTheDate+" "+timesforwednesday[0],stringOfTheDate+" "+timesforwednesday[1]);
   case Calendar.THURSDAY :
        String[] timesforthursday =bestTimeForDay.get("thursday");
      return new Period(stringOfTheDate+" "+timesforthursday[0],stringOfTheDate+" "+timesforthursday[1]);
    case Calendar.FRIDAY :
	String[] timesforfriday =bestTimeForDay.get("friday");
      return new Period(stringOfTheDate+" "+timesforfriday[0],stringOfTheDate+" "+timesforfriday[1]);
    case Calendar.SATURDAY :
	String[] timesforsaturday =bestTimeForDay.get("saturday");
	if(timesforsaturday.length>0 && timesforsaturday[0] != null && timesforsaturday[1] != null){
      return new Period(stringOfTheDate+" "+timesforsaturday[0],stringOfTheDate+" "+timesforsaturday[1]);
	}
	else{
	    return null;
	}
    case Calendar.SUNDAY :
      return null;
   default : 
       break;
}
return null; 
}
private Period calculateMediumPeriodOfTheDay(LocalDateTime date){
 Date convertedDateOfTheDay = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
 Calendar calendar = Calendar.getInstance();
calendar.setTime(convertedDateOfTheDay);
String stringOfTheDate = new SimpleDateFormat("dd/MM/yyyy").format(convertedDateOfTheDay);
int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
switch(dayOfWeek) {
   case Calendar.MONDAY :
       String[] timesformonaday =mediumTimeForDay.get("monday");  
      return new Period(stringOfTheDate+" "+timesformonaday[0],stringOfTheDate+" "+timesformonaday[1]);
   case Calendar.TUESDAY :
       String[] timesfortuesday =mediumTimeForDay.get("tuesday");
      return new Period(stringOfTheDate+" "+timesfortuesday[0],stringOfTheDate+" "+timesfortuesday[1]);
   case Calendar.WEDNESDAY :
        String[] timesforwednesday =mediumTimeForDay.get("wednesday");
      return new Period(stringOfTheDate+" "+timesforwednesday[0],stringOfTheDate+" "+timesforwednesday[1]);
   case Calendar.THURSDAY :
        String[] timesforthursday =mediumTimeForDay.get("thursday");
      return new Period(stringOfTheDate+" "+timesforthursday[0],stringOfTheDate+" "+timesforthursday[1]);
    case Calendar.FRIDAY :
	String[] timesforfriday =mediumTimeForDay.get("friday");
      return new Period(stringOfTheDate+" "+timesforfriday[0],stringOfTheDate+" "+timesforfriday[1]);
    case Calendar.SATURDAY :
	
	String[] timesforsaturday =mediumTimeForDay.get("saturday");
	if(timesforsaturday.length>0 && timesforsaturday[0] != null && timesforsaturday[1] != null){
      return new Period(stringOfTheDate+" "+timesforsaturday[0],stringOfTheDate+" "+timesforsaturday[1]);
	}
	else{
	    return null;
	}
    case Calendar.SUNDAY :
      return null;
   default : 
       break;
}
return null; 
}
private Period calculateEmergencyPeriodOgTheDay(LocalDateTime date){
 Date convertedDateOfTheDay = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
  Calendar calendar = Calendar.getInstance();
calendar.setTime(convertedDateOfTheDay);
String stringOfTheDate = new SimpleDateFormat("dd/MM/yyyy").format(convertedDateOfTheDay);
int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
switch(dayOfWeek) {
   case Calendar.MONDAY :
       String[] timesformonaday =emergencyTimeForDay.get("monday");  
      return new Period(stringOfTheDate+" "+timesformonaday[0],stringOfTheDate+" "+timesformonaday[1]);
   case Calendar.TUESDAY :
       String[] timesfortuesday =emergencyTimeForDay.get("tuesday");
      return new Period(stringOfTheDate+" "+timesfortuesday[0],stringOfTheDate+" "+timesfortuesday[1]);
   case Calendar.WEDNESDAY :
        String[] timesforwednesday =emergencyTimeForDay.get("wednesday");
      return new Period(stringOfTheDate+" "+timesforwednesday[0],stringOfTheDate+" "+timesforwednesday[1]);
   case Calendar.THURSDAY :
        String[] timesforthursday =emergencyTimeForDay.get("thursday");
      return new Period(stringOfTheDate+" "+timesforthursday[0],stringOfTheDate+" "+timesforthursday[1]);
    case Calendar.FRIDAY :
	String[] timesforfriday =emergencyTimeForDay.get("friday");
      return new Period(stringOfTheDate+" "+timesforfriday[0],stringOfTheDate+" "+timesforfriday[1]);
    case Calendar.SATURDAY :
	String[] timesforsaturday =emergencyTimeForDay.get("saturday");
	if(timesforsaturday.length>0 && timesforsaturday[0] != null && timesforsaturday[1] != null){
      return new Period(stringOfTheDate+" "+timesforsaturday[0],stringOfTheDate+" "+timesforsaturday[1]);}
	else{
	    return null;
	}
    case Calendar.SUNDAY :
      return null;
   default : 
       break;
}
return null;   
}
    @Override
    public LocalDateTime getDateOfTheDay()
{
    return this.dateOfTheDay;
}
  
    
}
