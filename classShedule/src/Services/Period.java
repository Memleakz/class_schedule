/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.Date;

/**
 *
 * @author lawar15
 */
public class Period {
    Date startDate;
    Date finishDate;
    public Period(Date startDate,Date finishDate){
	this.startDate = startDate;
	this.finishDate = finishDate;
    }
}
