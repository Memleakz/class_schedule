/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.Participants;

import Business_Logic.Common.Period;
import Business_Logic.IServices.StudentsInterface;
import java.util.List;

/**
 *
 * @author lawar15
 */
public class StudentsFactory {
     public static StudentsInterface getClassOfStudents(String id, String fieldOfStudy, int numberOfMembers, List<Period> bookedPeriods)
  {
      
      return new ClassOfTheStudents(id,fieldOfStudy,numberOfMembers,bookedPeriods);
  }   
}
