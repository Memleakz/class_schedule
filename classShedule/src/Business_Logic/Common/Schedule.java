/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.Common;

import java.io.Serializable;

/**
 *
 * @author lawar15
 */
public class Schedule implements Serializable{
    int id;
    Period periodOfTerm;
    public Schedule(int id, Period period){
	this.id = id;
	this.periodOfTerm = period;
    }
    public int getId(){
	return this.id;
    }
    public Period getPeriodOfTerm(){
	return this.periodOfTerm;
    }
    public void setPeriodOfTerm(Period periodOfTerm){
	this.periodOfTerm = periodOfTerm;
    }
}
