/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.Common;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author lawar15
 */
public class Period {
    LocalDateTime startTime;
    LocalDateTime finishTime;
    public Period(String startTime,String finishTime){
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	LocalDateTime formatOfStartTime = LocalDateTime.parse(startTime,formatter);
	this.startTime = formatOfStartTime;
	LocalDateTime formatOfFinishTime = LocalDateTime.parse(finishTime,formatter);
	this.finishTime = formatOfFinishTime;
    }
    public LocalDateTime getStartDate()
    {
	return this.startTime;
    }
    public LocalDateTime getEndDate()
    {
	return this.finishTime;
    }
    public LocalDateTime getRelativeDate(int plusDays,int plusHours)
    {
	return this.startTime.plusDays(plusDays).plusHours(plusHours);
    }
}
