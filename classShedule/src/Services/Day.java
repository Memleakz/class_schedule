/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static Services.Day.WEEKDAY.FRIDAY;
import static Services.Day.WEEKDAY.MONDAY;
import static Services.Day.WEEKDAY.SATURDAY;
import static Services.Day.WEEKDAY.SUNDAY;
import static Services.Day.WEEKDAY.THURSDAY;
import static Services.Day.WEEKDAY.TUESDAY;
import static Services.Day.WEEKDAY.WEDNESDAY;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author lawar15
 */

public class Day {
  public enum WEEKDAY {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
}
public WEEKDAY getDayOfTheWeek(Date date){
Calendar calendar = Calendar.getInstance();
calendar.setTime(date);
int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
switch(dayOfWeek) {
   case 1 :
      return MONDAY;
   case 2 :
      return TUESDAY;
   case 3 :
      return WEDNESDAY; 
   case 4 :
      return THURSDAY;
    case 5 :
      return FRIDAY;
    case 6 :
      return SATURDAY;
    case 7 :
      return SUNDAY;
   default : 
       break;
}
return null;
}
  
    
}
