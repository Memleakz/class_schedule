/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.Days;

import Business_Logic.Common.Period;
import Business_Logic.Common.Schedule;
import Business_Logic.IServices.ClasificationInterface;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lawar15
 */
public class ClasificationFactory {

    /**
     *
     * @param p
     * @param bestTimeForDay
     * @param mediumTimeForDay
     * @param bestTimeForDay
     * @return
     */
    public static List<ClasificationInterface> getPossibleDays(Schedule bookingsSchedule,Map<String,String[]> bestTimeForDay,Map<String,String[]> mediumTimeForDay,Map<String,String[]> emergencyTimeForDay)
 
 
    {
      Period p =bookingsSchedule.getPeriodOfTerm();
      List<ClasificationInterface> possible_days_in_period = new ArrayList<ClasificationInterface>();
      LocalDateTime startdate = p.getStartDate();
      LocalDateTime enddate = p.getEndDate();
	for (LocalDateTime date =startdate ; date.isBefore(enddate); date = date.plusDays(1))
	{
	    ClasificationInterface newCreatedDay =new Day(date,bestTimeForDay,mediumTimeForDay, emergencyTimeForDay);
	    possible_days_in_period.add(newCreatedDay);
	}
	return possible_days_in_period;
  }
}
