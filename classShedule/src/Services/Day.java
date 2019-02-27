/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author lawar15
 */

public class Day {
    private final Period bestPeriod;
    private final Period mediumPeriod;
    private final Period emergencyPeriod;
    private final LocalDateTime dateOfTheDay;
    private boolean hollyDay;

  public Day(LocalDateTime dateOfTheDay){
      this.dateOfTheDay = dateOfTheDay;
      this.bestPeriod = this.calculateBestPeriodOfTheDay(dateOfTheDay);
      this.mediumPeriod = this.calculateMediumPeriodOfTheDay(dateOfTheDay);
      this.emergencyPeriod = this.calculateEmergencyPeriodOgTheDay(dateOfTheDay);
      //this.hollyDay = this.isThisHollyDay(dateOfTheDay);
  }
  public Period getBestPeriod(){
      return this.bestPeriod;
  }
  public Period getMediumPeriod(){
      return this.mediumPeriod;
  }
  public Period getEmergencyPeriod(){
      return this.emergencyPeriod;
  }
  public boolean isThisAHollyDay(){
      return this.hollyDay;
  }
private boolean isThisHollyDay(LocalDateTime date){
    String stringOfTheDate = new SimpleDateFormat("MM/dd").format(date);
   switch(stringOfTheDate) {
   case "12/24" :
      return true;
   case "12/25" :
      return true;
   case "12/26" :
      return true;
   case "12/31":
      return true;
   default : 
       break;
}
return false; 
}
private Period calculateBestPeriodOfTheDay(LocalDateTime date){
Date convertedDateOfTheDay = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
Calendar calendar = Calendar.getInstance();
calendar.setTime(convertedDateOfTheDay);
String stringOfTheDate = new SimpleDateFormat("dd/MM/yyyy").format(convertedDateOfTheDay);
int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
switch(dayOfWeek) {
   case Calendar.MONDAY :
      return new Period(stringOfTheDate+" 08:00:00",stringOfTheDate+" 16:00:00");
   case Calendar.TUESDAY :
      return new Period(stringOfTheDate+" 08:00:00",stringOfTheDate+" 16:00:00");
   case Calendar.WEDNESDAY :
      return new Period(stringOfTheDate+" 08:00:00",stringOfTheDate+" 16:00:00");
   case Calendar.THURSDAY :
      return new Period(stringOfTheDate+" 08:00:00",stringOfTheDate+" 16:00:00");
    case Calendar.FRIDAY :
      return new Period(stringOfTheDate+" 08:00:00",stringOfTheDate+" 16:00:00");
    case Calendar.SATURDAY :
      return null;
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
      return new Period(stringOfTheDate+" 16:00:00",stringOfTheDate+" 20:00:00");
   case Calendar.TUESDAY :
      return new Period(stringOfTheDate+" 16:00:00",stringOfTheDate+" 20:00:00");
   case Calendar.WEDNESDAY :
      return new Period(stringOfTheDate+" 16:00:00",stringOfTheDate+" 20:00:00");
   case Calendar.THURSDAY :
      return new Period(stringOfTheDate+" 16:00:00",stringOfTheDate+" 20:00:00");
    case Calendar.FRIDAY :
      return new Period(stringOfTheDate+" 16:00:00",stringOfTheDate+" 20:00:00");
    case Calendar.SATURDAY :
      return null;
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
      return new Period(stringOfTheDate+" 20:00:00",stringOfTheDate+" 22:00:00");
   case Calendar.TUESDAY :
      return new Period(stringOfTheDate+" 20:00:00",stringOfTheDate+" 22:00:00");
   case Calendar.WEDNESDAY :
      return new Period(stringOfTheDate+" 20:00:00",stringOfTheDate+" 22:00:00");
   case Calendar.THURSDAY :
      return new Period(stringOfTheDate+" 20:00:00",stringOfTheDate+" 22:00:00");
    case Calendar.FRIDAY :
      return new Period(stringOfTheDate+" 20:00:00",stringOfTheDate+" 22:00:00");
    case Calendar.SATURDAY :
      return new Period(stringOfTheDate+" 08:00:00",stringOfTheDate+" 16:00:00");
    case Calendar.SUNDAY :
      return null;
   default : 
       break;
}
return null;   
}
public LocalDateTime getDateOfTheDay()
{
    return this.dateOfTheDay;
}
  
    
}
