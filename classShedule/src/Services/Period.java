/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lawar15
 */
public class Period {
    String startTime;
    String finishTime;
    public Period(String startTime,String finishTime){
	try {
	    Date formatOfStartTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(startTime);
	} catch (ParseException ex) {
	    Logger.getLogger(Period.class.getName()).log(Level.SEVERE, null, ex);
	}
	try {
	    Date formatOfFinishTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(finishTime);
	} catch (ParseException ex) {
	    Logger.getLogger(Period.class.getName()).log(Level.SEVERE, null, ex);
	}
	this.startTime = startTime;
	this.finishTime = finishTime;
    }
}
