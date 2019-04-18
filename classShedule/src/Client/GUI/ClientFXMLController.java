/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.GUI;

import Business_Logic.Common.Period;
import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.TeacherInterface;
import Client.ClientController;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
//import java.awt.event.ActionEvent;
import javafx.event.ActionEvent;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author lawar15
 */
public class ClientFXMLController implements Initializable {

    @FXML
    AnchorPane commonPane;
    @FXML
    private Pane loginPane;
    @FXML
    private TextField inputLogin;
    @FXML
    private TextField inputPassword;
    @FXML
    private Button signInButton;
    @FXML
    private TextArea communityArea;

    @FXML
    private Pane classSchedulerWelcomePane;
    @FXML
    private Button signoutschedulerwelcomeButton;
    @FXML
    private Button makeNewscheduleWelcomeButton;
    @FXML
    private Button changeCurrentScheduleWelcomeButton;
    @FXML
    private Button addNewTeacherButton;

    @FXML
    private Pane changingPaneScheduler;
    @FXML
    private RadioButton teachersFaultRButton;
    @FXML
    private RadioButton studentsFaultRButton;
    @FXML
    private RadioButton roomsFaultRButton;
    @FXML
    private RadioButton newDataRButton;
    @FXML
    private ToggleGroup changeReasonGroup;
    @FXML
    private Text startquestiondate;
    @FXML
    private Text finishquestiondate;
    @FXML
    private DatePicker startDateOfAbsence;
    @FXML
    private DatePicker finishDateOfAbsence;
    @FXML
    private Text questionChooseTeacher;
    @FXML
    ListView listOfTeachers;
    @FXML
    Button backButton;
    @FXML
    Button savechangesButton;
    @FXML
    private Text questionChooseClass;
    @FXML
    ListView listOfStudents;

    @FXML
    private Pane changingPaneTeacher;
    @FXML
    private DatePicker startDateOfTeachersAbsence;
    @FXML
    private DatePicker finishDateOfTeachersAbsence;
    @FXML
    private Button signoutButtonForTeacher;
    @FXML
    private Button saveChangesAndSeeNewScheduleTeacher;

    @FXML
    private Pane inputsPane;
    @FXML
    private Button saveAndSeeScheduleWithInputs;
    @FXML
    private Button goBackToChangingPane;
    @FXML
    private Text whichToAdd;
    @FXML
    private RadioButton addNewTeacher;
    @FXML
    private RadioButton addNewClassOfStudents;
    @FXML
    private RadioButton addNewRoom;
    @FXML
    private RadioButton addNewCourse;
    @FXML
    private RadioButton addNewUser;
    @FXML
    private ToggleGroup inputsGroup;

    @FXML
    private Pane classScheduleViewPane;
    @FXML
    private TextField weekNumber;
    @FXML
    private TextField classorTeacherName;
    @FXML
    private Button nextWeekTeacherButton;
    @FXML
    private Button signOutTeacherFromViewButton;
    @FXML
    private Button previousWeekButton;
    @FXML
    private TextField mondayLessonsTextField;
    @FXML
    private TextField tuesdayLessonsTextField;
    @FXML
    private TextField wednesdayLessonsTextField;
    @FXML
    private TextField thursdayLessonsTextField;
    @FXML
    private TextField fridayLessonsTextField;
    @FXML
    private TextField saturdayLessonsTextField;
    @FXML
    private TextField sundayLessonsTextField;

    private ClientController clientController;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	try {
	    clientController = new ClientController();
	} catch (RemoteException ex) {
	    Logger.getLogger(ClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
	}
	loginPane.setVisible(true);
    }
    //For sign in methods:

    @FXML
    private void handleSignInButton(ActionEvent event) {

	String login = inputLogin.getText();
	String password = inputPassword.getText();

	//Checks if email or password is empty
	if (!isLogInParametersValid(login, password)) {
	    return;
	}

	//Signs in and checks if it succeeded
	boolean isSignedIn = false;
	try {
	    isSignedIn = clientController.signIn(login, hashPassword(password));
	} catch (IllegalArgumentException ex) {
	    System.out.println(ex.getMessage());
	}

	if (isSignedIn) {
	    boolean isTeacher = clientController.isThisATeacher(login, hashPassword(password));
	    loginPane.setVisible(false);
	    if (isTeacher == true) {
		resetLogInPane();
		classorTeacherName.setText("This is a class schedule for Teacher " + clientController.getUsersNameByLogin(login));
		changingPaneTeacher.setVisible(true);
	    } else {
		resetLogInPane();
		classSchedulerWelcomePane.setVisible(true);
	    }

	} else {
	    resetLogInPane();
	    communityArea.clear();
	    communityArea.setText("Login does not match with password! Try again or contact support!");
	}
    }

    private boolean isLogInParametersValid(String login, String password) {
	boolean isLogInParametersValid = true;

	if (login == null || login.isEmpty()) {
	    isLogInParametersValid = false;
	    communityArea.setText("Enter email!");
	} else {
	    communityArea.clear();
	}

	if (password == null || password.isEmpty()) {
	    isLogInParametersValid = false;
	    communityArea.setText("Enter password!");

	} else {
	    communityArea.clear();
	}

	return isLogInParametersValid;

    }

    private String hashPassword(String password) {

	if (password == null) {
	    return null;
	}

	byte[] hashBytes = null;
	// Salting the password
	password += password.substring(0, 4);
	MessageDigest md;
	try {
	    md = MessageDigest.getInstance("SHA-256");
	    md.update(password.getBytes());
	    hashBytes = md.digest();
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	}

	return String.format("%064x", new java.math.BigInteger(1, hashBytes)).toLowerCase();

    }

    private void showPane(AnchorPane pane) {
	//All panes set to invisible
	for (Node node : commonPane.getChildren()) {
	    node.setVisible(false);
	}
	//The given pane is set to visible
	pane.setVisible(true);
    }

    private void resetLogInPane() {
	inputLogin.setText("");
	inputPassword.setText("");
	loginPane.setVisible(false);
	communityArea.setText("Hello! Nice to see you again in out class scheduling system. Please, sign in to work with us!");
    }

//Teacher functionality Pane actions

    /*
@FXML
private Pane classScheduleViewPane;
@FXML
private TextField weekNumber;
@FXML
private TextField classorTeacherName;
@FXML
private Button nextWeekTeacherButton;
@FXML
private Button signOutTeacherFromViewButton;
@FXML
private Button previousWeekButton;
@FXML
private TextField mondayLessonsTextField;
@FXML
private TextField tuesdayLessonsTextField;
@FXML
private TextField wednesdayLessonsTextField;
@FXML
private TextField thursdayLessonsTextField;
@FXML
private TextField fridayLessonsTextField;
@FXML
private TextField saturdayLessonsTextField;
@FXML
private TextField sundayLessonsTextField;
     */
    @FXML
    private void handlesaveChangesAndSeeNewScheduleTeacherButtonAction(ActionEvent event) {
	String startDateOfAbsence = startDateOfTeachersAbsence.getValue().toString();
	String finishDateOfAbsence = finishDateOfTeachersAbsence.getValue().toString();
	clientController.handleTeachersAbsence(startDateOfAbsence, finishDateOfAbsence);
	changingPaneTeacher.setVisible(false);
	classScheduleViewPane.setVisible(true);
	String nameOfTeacher = classorTeacherName.getText();
	String[] parts = nameOfTeacher.split("Teacher ");
	String teachersname = parts[1];
	String teachersId = clientController.getTeachersIdByName(teachersname);
	Map<Integer, Map<Period, CourseInterface>> mapForTeacher = clientController.getClassScheduleForTeacher(teachersId);
	Map.Entry<Integer, Map<Period, CourseInterface>> entry = mapForTeacher.entrySet().iterator().next();
	int week = entry.getKey();
	fillScheduleForWeek(week, mapForTeacher);

    }

    @FXML
    private void handleTeachersignOutButtonAction(ActionEvent event) {
	changingPaneTeacher.setVisible(false);
	clientController.handlesignoutAction();
	loginPane.setVisible(true);

    }

    private void fillScheduleForWeek(int weeknr, Map<Integer, Map<Period, CourseInterface>> mapForTeacher) {
	for (Map.Entry<Integer, Map<Period, CourseInterface>> entry : mapForTeacher.entrySet()) {
	    if (entry.getKey() == weeknr) {
		weekNumber.setText("This is your schedule for week " + weeknr + ":");
		Map<Period, CourseInterface> value = entry.getValue();
		for (Map.Entry<Period, CourseInterface> entry2 : value.entrySet()) {
		    Period timeOfLesson = entry2.getKey();
		    CourseInterface coursetoSchedule = entry2.getValue();
		    Calendar cal = Calendar.getInstance();
		    Date dateOfLecture = Date.from((timeOfLesson.getStartDate()).atZone(ZoneId.systemDefault()).toInstant());

		    cal.setTime(dateOfLecture);
		    boolean monday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
		    boolean tuesday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY;
		    boolean wednesday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY;
		    boolean thurday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY;
		    boolean friday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
		    boolean saturday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
		    boolean sunday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
		    if (monday == true) {
			String currenttext = mondayLessonsTextField.getText();
			mondayLessonsTextField.setText(currenttext + '\n' + entry2.getKey().toString() + "-" + coursetoSchedule + '\n');
		    } else if (tuesday == true) {
			String currenttext = tuesdayLessonsTextField.getText();
			tuesdayLessonsTextField.setText(currenttext + '\n' + entry2.getKey().toString() + "-" + coursetoSchedule + '\n');
		    } else if (wednesday == true) {
			String currenttext = wednesdayLessonsTextField.getText();
			wednesdayLessonsTextField.setText(currenttext + '\n' + entry2.getKey().toString() + "-" + coursetoSchedule + '\n');
		    } else if (thurday == true) {
			String currenttext = thursdayLessonsTextField.getText();
			thursdayLessonsTextField.setText(currenttext + '\n' + entry2.getKey().toString() + "-" + coursetoSchedule + '\n');

		    } else if (friday == true) {
			String currenttext = fridayLessonsTextField.getText();
			fridayLessonsTextField.setText(currenttext + '\n' + entry2.getKey().toString() + "-" + coursetoSchedule + '\n');

		    } else if (saturday == true) {
			String currenttext = saturdayLessonsTextField.getText();
			saturdayLessonsTextField.setText(currenttext + '\n' + entry2.getKey().toString() + "-" + coursetoSchedule + '\n');

		    } else if (sunday == true) {
			String currenttext = sundayLessonsTextField.getText();
			sundayLessonsTextField.setText(currenttext + '\n' + entry2.getKey().toString() + "-" + coursetoSchedule + '\n');

		    }
		}
	    }
	}
    }
}
