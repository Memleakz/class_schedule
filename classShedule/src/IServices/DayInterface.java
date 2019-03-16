/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Common.Period;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author lawar15
 */
public interface DayInterface {
  public Period getBestPeriod();
 
  public Period getMediumPeriod();
  
  public Period getEmergencyPeriod();
  
  public LocalDateTime getDateOfTheDay();
}
  

