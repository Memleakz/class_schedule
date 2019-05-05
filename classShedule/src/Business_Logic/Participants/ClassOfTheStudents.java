/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.Participants;

import Business_Logic.Common.Period;
import Business_Logic.IServices.StudentsInterface;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author lawar15
 */
public class ClassOfTheStudents implements StudentsInterface, Serializable {
    String id;
    String fieldOfStudy;
    int numberOfMembers;
    List<Period> bookedPeriods;
public ClassOfTheStudents (String id, String fieldOfStudy, int numberOfMembers, List<Period> bookedPeriods){
    this.id = id;
    this.fieldOfStudy = fieldOfStudy;
    this.numberOfMembers = numberOfMembers;
    this.bookedPeriods = bookedPeriods;
    
}
    @Override
    public int getNumberOfStuents(){
    return this.numberOfMembers;
}
    @Override
    public List<Period> getbookedPeriods(){
    return this.bookedPeriods;
}

    @Override
    public String getId() {
	return this.id;
    }
}
