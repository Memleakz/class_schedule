/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.IServices;

import Business_Logic.Common.Period;
import java.time.LocalDateTime;

/**
 *
 * @author lawar15
 */
public interface ClasificationInterface {
  public Period getBestPeriod();
 
  public Period getMediumPeriod();
  
  public Period getEmergencyPeriod();
  
  public LocalDateTime getDateOfTheDay();
}
  

