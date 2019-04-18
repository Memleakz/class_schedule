/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.IServices;

/**
 *
 * @author lawar15
 */
public interface UserInterface {
    public String getlogin();
    public String getpassword();
    public String getname();
    public void changePassword(String abcd);
    public boolean isTeacher();
    public String getTeachersID();
}
