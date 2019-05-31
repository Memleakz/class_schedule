/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.User;

import Business_Logic.IServices.TeacherInterface;
import Business_Logic.IServices.UserInterface;
import java.io.Serializable;

/**
 *
 * @author lawar15
 */
public class User implements UserInterface, Serializable{
    String login;
    String password;
    TeacherInterface teacher;
    String name;
    boolean isClassScheduler;
    public User(String login, String password,TeacherInterface teacher, String name){
	this.login = login;
	this.password = password;
	this.teacher = teacher;
	if(teacher == null){
	    this.isClassScheduler = true;
	}
	else {
	    this.isClassScheduler = false;
	}
	this.name = name;
    }
    @Override
    public String getlogin(){
	return this.login;
    }
    @Override
    public String getpassword(){
	return this.password;
    }
    @Override
    public String getname(){
	return this.name;
    }
    @Override
    public void changePassword(String abcd){
	this.password = abcd;
    }

    @Override
    public boolean isTeacher() {
	if( this.teacher == null){
	    return false;
	}
	return true;
    }
    @Override
    public String getTeachersID() {
	TeacherInterface teachertoReturn =this.teacher;
	return teachertoReturn.getTeachersId();
    }
    
}
