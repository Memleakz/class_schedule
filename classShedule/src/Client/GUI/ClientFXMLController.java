/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.GUI;

import Business_Logic.IServices.BookingLocationsInterface;
import Business_Logic.IServices.CourseInterface;
import Business_Logic.IServices.LocationInterface;
import Business_Logic.IServices.TeacherInterface;
import Business_Logic.Common.Schedule_result;
import Client.ClientController;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
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
    private PasswordField inputPassword;
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
    private ToggleGroup changeReasonGroup;
    @FXML
    private DatePicker startDateOfAbsenceDP;
    @FXML
    private DatePicker finishDateOfAbsenceDP;
    @FXML
    private Text startquestiondate;
    @FXML
    private Text finishquestiondate;
    @FXML
    private Text questionChooseTeacher;
    @FXML
    ListView listOfTeachers;
    @FXML
    Button backButton;
    @FXML
    Button savechangesButton;

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
    private Button backToSchedulerPaneButton;
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
    private ListView mondayLessonsListView;
    @FXML
    private ListView tuesdayLessonsListView;
    @FXML
    private ListView wednesdayLessonsListView;
    @FXML
    private ListView thursdayLessonsListView;
    @FXML
    private ListView fridayLessonsListView;
    @FXML
    private ListView saturdayLessonsListView;
    @FXML
    private ListView sundayLessonsListView;
    // Make new Schedule Pane
    @FXML
    private Pane creatingNewSchedulePane;
    @FXML
    private Button backfromnewScheduleButton;
    @FXML
    private Button createScheduleButton;
    @FXML
    private Button removeFromAllCoursesButton;
    @FXML
    private Button removeFromAllRoomsButton;
    @FXML
    private Button removeFromAllTeachersButton;
    @FXML
    private Button signoutfromcreatingnewscheduleButton;
    @FXML
    private DatePicker startOfTermDatePicker;
    @FXML
    private DatePicker finishOfTermDatePicker;
    @FXML
    private Button showPaneToDayTimePreferences;
    @FXML
    private Pane underPaneTimeToDays;
    @FXML
    private ListView allWeekDays;
    @FXML
    private ListView besttimeToStartPossibilities;
    @FXML
    private ListView besttimeToFinishPossibilities;
    @FXML
    private ListView mediumtimeToStartPossibilities;
    @FXML
    private ListView mediumtimeToFinishPossibilities;
    @FXML
    private ListView emergencytimeToStartPossibilities;
    @FXML
    private ListView emergencytimeToFinishPossibilities;
    @FXML
    private Button addToPreferences;
    @FXML
    private Button saveall;
    @FXML
    private Button hiddePaneToDayTimePreferencesButon;
    @FXML
    private ListView mypreferences;

    @FXML
    private ListView teachersInTermListView;
    @FXML
    private ListView coursesListView;
    @FXML
    private ListView roomsListView;
//add new teacher Pane
    @FXML
    private Pane addnewTeacherPane;
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField teachersIDField;
    @FXML
    private ListView allcoursesTochooseForNewTeacherListView;
    @FXML
    private ListView choosedCoursesToTeacherListView;
    @FXML
    private TextArea schedulerArea;
    @FXML
    private Button createNewTeacherButton;
    @FXML
    private Button backToschedulerMenu;
    @FXML
    private Button addToPossibleCoursesButton;

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
                backToSchedulerPaneButton.setVisible(true);
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
    @FXML
    private void handlesaveChangesAndSeeNewScheduleTeacherButtonAction(ActionEvent event) throws ParseException {
        LocalDate startlocaldate = startDateOfTeachersAbsence.getValue();
        String startDateOfAbsence = clientController.makeDatePickerDateToString(startlocaldate, true);
        LocalDate finishlocaldate = finishDateOfTeachersAbsence.getValue();
        String finishDateOfAbsence = clientController.makeDatePickerDateToString(finishlocaldate, false);

        Calendar cal = Calendar.getInstance();
        Date dateOfstart = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(startDateOfAbsence);
        cal.setTime(dateOfstart);
        int startWeekOfabsence = cal.get(Calendar.WEEK_OF_YEAR);
        changingPaneTeacher.setVisible(false);
        backToSchedulerPaneButton.setVisible(false);
        classScheduleViewPane.setVisible(true);
        String nameOfTeacher = classorTeacherName.getText();
        String[] parts = nameOfTeacher.split("Teacher ");
        String teachersname = parts[1];
        String teachersId = clientController.getTeachersIdByName(teachersname);
        clientController.handleTeachersAbsence(startDateOfAbsence, finishDateOfAbsence, null);
        Schedule_result res = clientController.getCurrentScheldue();
        displayNewScheldue(res, startWeekOfabsence);

    }

    @FXML
    private void handleTeachersignOutButtonAction(ActionEvent event) {
        changingPaneTeacher.setVisible(false);
        classScheduleViewPane.setVisible(false);
        clientController.handlesignoutAction();
        loginPane.setVisible(true);

    }

    //Class Scheduler pane
    @FXML
    private void handleMakeNewScheduleButtonAction(ActionEvent event) {

        boolean canMakeSchedule = clientController.preparePaneToMakingNewSchedule();
        if (canMakeSchedule == true) {
            classSchedulerWelcomePane.setVisible(false);
            creatingNewSchedulePane.setVisible(true);
            List<CourseInterface> allCourses = clientController.getAllCourses();
            coursesListView.getItems().clear();
            coursesListView.getItems().addAll(allCourses); //uses overwritten toString on course to show name.

            List<LocationInterface> allRooms = clientController.getAllRooms();
            roomsListView.getItems().clear();
            roomsListView.getItems().addAll(allRooms); //uses overwritten toString on course to show name.

            List<TeacherInterface> allTeachers = clientController.getAllTeachers();
            teachersInTermListView.getItems().clear();
            teachersInTermListView.getItems().addAll(allTeachers); //uses overwritten toString on course to show name.
            List<TeacherInterface> selectedTeachers = teachersInTermListView.getSelectionModel().getSelectedItems(); //get only selected
            List<TeacherInterface> allobjTeachers = teachersInTermListView.getItems(); // get all objects...selected or not.

        }
    }

    @FXML
    private void handleShowPaneToDayPreferencesButton(ActionEvent event) {
        underPaneTimeToDays.setVisible(true);
        String[] timestamps = clientController.getPossibleTimeStamps();
        String[] days = clientController.getNamesOfAllDaysOfWeek();
        allWeekDays.getItems().addAll(days);
        besttimeToStartPossibilities.getItems().clear();
        besttimeToFinishPossibilities.getItems().clear();
        mediumtimeToStartPossibilities.getItems().clear();
        mediumtimeToFinishPossibilities.getItems().clear();
        emergencytimeToStartPossibilities.getItems().clear();
        emergencytimeToFinishPossibilities.getItems().clear();
        allWeekDays.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                besttimeToStartPossibilities.getItems().addAll(timestamps);
                System.out.println("clicked on " + allWeekDays.getSelectionModel().getSelectedItem());
            }
        });
        besttimeToStartPossibilities.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (besttimeToStartPossibilities.getSelectionModel().getSelectedItem() == null) {
                    besttimeToFinishPossibilities.getItems().addAll(timestamps);
                } else {
                    List<String> timeStamps = clientController.getPossibleTimeStampsAfterTime((String) besttimeToStartPossibilities.getSelectionModel().getSelectedItem());
                    besttimeToFinishPossibilities.getItems().addAll(timeStamps);
                }
                System.out.println("clicked on " + besttimeToStartPossibilities.getSelectionModel().getSelectedItem());
            }
        });
        besttimeToFinishPossibilities.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (besttimeToFinishPossibilities.getSelectionModel().getSelectedItem() == null) {
                    mediumtimeToStartPossibilities.getItems().addAll(timestamps);
                } else {
                    List<String> timeStamps = clientController.getPossibleTimeStampsAfterTime((String) besttimeToFinishPossibilities.getSelectionModel().getSelectedItem());
                    mediumtimeToStartPossibilities.getItems().addAll(timeStamps);
                }
                System.out.println("clicked on " + besttimeToFinishPossibilities.getSelectionModel().getSelectedItem());
            }
        });
        mediumtimeToStartPossibilities.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (mediumtimeToStartPossibilities.getSelectionModel().getSelectedItem() == null) {
                    mediumtimeToFinishPossibilities.getItems().addAll(timestamps);
                } else {
                    List<String> timeStamps = clientController.getPossibleTimeStampsAfterTime((String) mediumtimeToStartPossibilities.getSelectionModel().getSelectedItem());
                    mediumtimeToFinishPossibilities.getItems().addAll(timeStamps);
                }
                System.out.println("clicked on " + mediumtimeToStartPossibilities.getSelectionModel().getSelectedItem());
            }
        });
        mediumtimeToFinishPossibilities.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (mediumtimeToFinishPossibilities.getSelectionModel().getSelectedItem() == null) {
                    emergencytimeToStartPossibilities.getItems().addAll(timestamps);
                } else {
                    List<String> timeStamps = clientController.getPossibleTimeStampsAfterTime((String) mediumtimeToFinishPossibilities.getSelectionModel().getSelectedItem());
                    emergencytimeToStartPossibilities.getItems().addAll(timeStamps);
                }
                System.out.println("clicked on " + mediumtimeToFinishPossibilities.getSelectionModel().getSelectedItem());
            }
        });
        emergencytimeToStartPossibilities.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (emergencytimeToStartPossibilities.getSelectionModel().getSelectedItem() == null) {
                    emergencytimeToFinishPossibilities.getItems().addAll(timestamps);
                } else {
                    List<String> timeStamps = clientController.getPossibleTimeStampsAfterTime((String) emergencytimeToStartPossibilities.getSelectionModel().getSelectedItem());
                    emergencytimeToFinishPossibilities.getItems().addAll(timeStamps);
                }
                System.out.println("clicked on " + emergencytimeToStartPossibilities.getSelectionModel().getSelectedItem());
            }
        });

        emergencytimeToFinishPossibilities.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                addToPreferences.setVisible(true);
                System.out.println("clicked on " + emergencytimeToFinishPossibilities.getSelectionModel().getSelectedItem());
            }
        });

    }

    @FXML
    private void handleAddToPreferencesButton(ActionEvent event) {
        String day = (String) allWeekDays.getSelectionModel().getSelectedItem();
        mypreferences.getItems().add(day + ": best period(#" + besttimeToStartPossibilities.getSelectionModel().getSelectedItem() + "-" + besttimeToFinishPossibilities.getSelectionModel().getSelectedItem() + "#), medium period(#" + mediumtimeToStartPossibilities.getSelectionModel().getSelectedItem() + "-" + mediumtimeToFinishPossibilities.getSelectionModel().getSelectedItem() + "#), emergency period(#" + emergencytimeToStartPossibilities.getSelectionModel().getSelectedItem() + "-" + emergencytimeToFinishPossibilities.getSelectionModel().getSelectedItem() + "#)");
        besttimeToStartPossibilities.getItems().clear();
        besttimeToFinishPossibilities.getItems().clear();
        mediumtimeToStartPossibilities.getItems().clear();
        mediumtimeToFinishPossibilities.getItems().clear();
        emergencytimeToStartPossibilities.getItems().clear();
        emergencytimeToFinishPossibilities.getItems().clear();
        allWeekDays.getItems().remove(day);
    }

    @FXML
    private void handleSaveAllDaysPreferences(ActionEvent event) {
        List<String> preferences = mypreferences.getItems();
        Map<String, String[]> besttimes = new TreeMap<String, String[]>();
        Map<String, String[]> mediumtimes = new TreeMap<String, String[]>();
        Map<String, String[]> emergencytimes = new TreeMap<String, String[]>();
        for (String s : preferences) {
            String[] splitted = s.split("#");
            String bestperiod = splitted[1];
            String mediumperiod = splitted[3];
            String emergencyperiod = splitted[5];
            String[] splittedbestperiod = bestperiod.split("-");
            String[] splittedToKey = s.split(":");
            besttimes.put(splittedToKey[0], splittedbestperiod);
            String[] splittedmediumperiod = mediumperiod.split("-");
            mediumtimes.put(splittedToKey[0], splittedmediumperiod);
            String[] splittedemergencyperiod = emergencyperiod.split("-");
            emergencytimes.put(splittedToKey[0], splittedemergencyperiod);

        }
        clientController.addPossibleTimesToBook(besttimes, mediumtimes, emergencytimes);
        mypreferences.getItems().clear();
        underPaneTimeToDays.setVisible(false);
    }

    @FXML
    private void handleremoveFromAllCoursesButton(ActionEvent event) {
        coursesListView.getItems().remove(coursesListView.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void handleremoveFromAllRoomsButton(ActionEvent event) {
        roomsListView.getItems().remove(roomsListView.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void handleremoveFromAllTeachersButton(ActionEvent event) {
        teachersInTermListView.getItems().remove(teachersInTermListView.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void handleTeachersAbsenceClassScheduler() {
        RadioButton selectedRadioButton = (RadioButton) changeReasonGroup.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
        if (toogleGroupValue.compareTo(teachersFaultRButton.getText()) == 0) {
            startquestiondate.setVisible(true);
            finishquestiondate.setVisible(true);
            questionChooseTeacher.setVisible(true);
            listOfTeachers.setVisible(true);
            List<TeacherInterface> allTeachers = clientController.getAllTeachers();
            listOfTeachers.getItems().addAll(allTeachers);
            startDateOfAbsenceDP.setVisible(true);
            finishDateOfAbsenceDP.setVisible(true);
        }
    }

    @FXML
    private void handlechangeScheduleButtonToPane(ActionEvent event) {
        classSchedulerWelcomePane.setVisible(false);
        changingPaneScheduler.setVisible(true);
    }

    @FXML
    private void handlesavechangesButton(ActionEvent event) {

        TeacherInterface teacher = (TeacherInterface) listOfTeachers.getSelectionModel().getSelectedItem();
        LocalDate localDate = startDateOfAbsenceDP.getValue();
        String startdate = clientController.makeDatePickerDateToString(localDate, true);
        LocalDate finishLocalDate = finishDateOfAbsenceDP.getValue();
        String finishDate = clientController.makeDatePickerDateToString(finishLocalDate, false);

        clientController.handleTeachersAbsence(startdate, finishDate, teacher);

        Schedule_result res = clientController.getCurrentScheldue();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int weekNumber = localDate.get(weekFields.weekOfWeekBasedYear());
        changingPaneScheduler.setVisible(false);
        classScheduleViewPane.setVisible(true);

        displayNewScheldue(res, weekNumber);

    }

    @FXML
    private void handlebackToSchedulerPaneButton(ActionEvent event) {
        classScheduleViewPane.setVisible(false);
        addnewTeacherPane.setVisible(false);
        changingPaneScheduler.setVisible(false);
        creatingNewSchedulePane.setVisible(false);
        classSchedulerWelcomePane.setVisible(true);

    }

    @FXML
    private void addNewTeacherButtonAction(ActionEvent event) {
        changingPaneScheduler.setVisible(false);
        List<CourseInterface> allCourses = clientController.getAllCourses();
        allcoursesTochooseForNewTeacherListView.getItems().clear();
        allcoursesTochooseForNewTeacherListView.getItems().addAll(allCourses);
        addnewTeacherPane.setVisible(true);
    }

    @FXML
    private void addNewTeacherToDbButtonAction(ActionEvent event) {
        String login = loginField.getText();
        String password = passwordField.getText();
        String name = nameField.getText();
        String id = teachersIDField.getText();
        List<CourseInterface> teachersCourses = choosedCoursesToTeacherListView.getItems();

        List<CourseInterface> teacherCourses_rmi = new ArrayList<CourseInterface>();
        for (CourseInterface c : teachersCourses) {
            teacherCourses_rmi.add(c);
        }
        clientController.addNewTeacher(login, password, name, id, new ArrayList<BookingLocationsInterface>(), teacherCourses_rmi);
        addnewTeacherPane.setVisible(false);
        classSchedulerWelcomePane.setVisible(true);
        schedulerArea.clear();
        schedulerArea.setText("Teacher " + name + " has been successfully added to database. How can I help You now?");
    }

    @FXML
    private void handleaddcoursestonewteacherButtonAction(ActionEvent event) {
        boolean isAllereadyOnList = false;
        CourseInterface coursetoadd = (CourseInterface) allcoursesTochooseForNewTeacherListView.getSelectionModel().getSelectedItem();

        List<CourseInterface> allCoursesFromListView = choosedCoursesToTeacherListView.getItems();
        for (CourseInterface ci : allCoursesFromListView) {
            if (ci.getId().equals(coursetoadd.getId())) {
                isAllereadyOnList = true;
            }
        }
        if (isAllereadyOnList == false) {
            choosedCoursesToTeacherListView.getItems().add(coursetoadd);
        }
    }

    @FXML
    private void handleCreateAndDisplayNewScheldueButtonAction(ActionEvent event) throws ParseException {
        List<TeacherInterface> selectedTeachers = teachersInTermListView.getSelectionModel().getSelectedItems(); //get only selected
        //check if 0 selected , get all if 0.
        selectedTeachers = teachersInTermListView.getItems(); // get all objects...selected or not.

        List<CourseInterface> selectedCourses = coursesListView.getSelectionModel().getSelectedItems(); //get only selected
        //check if 0 selected , get all if 0.
        selectedCourses = coursesListView.getItems(); // get all objects...selected or not.

        List<LocationInterface> selectedRooms = roomsListView.getSelectionModel().getSelectedItems(); //get only selected
        //check if 0 selected , get all if 0.
        selectedRooms = roomsListView.getItems(); // get all objects...selected or not.
        LocalDate startlocalDate = startOfTermDatePicker.getValue();
        String SemesterStat = clientController.makeDatePickerDateToString(startlocalDate, true);
        LocalDate finishlocalDate = finishOfTermDatePicker.getValue();
        String SemesterEnd = clientController.makeDatePickerDateToString(finishlocalDate, false);
        Date dateOfstart = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(SemesterStat);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateOfstart);
        int startWeekOfTerm = cal.get(Calendar.WEEK_OF_YEAR);
        ArrayList<CourseInterface> rmi_courses = new ArrayList<CourseInterface>();
        ArrayList<LocationInterface> rmi_rooms = new ArrayList<LocationInterface>();

        for (CourseInterface c : selectedCourses) {
            rmi_courses.add(c);
        }
        for (LocationInterface r : selectedRooms) {
            rmi_rooms.add(r);
        }
        Schedule_result new_scheldue = clientController.createNewScheldue(SemesterStat, SemesterEnd, rmi_rooms, rmi_courses);
        creatingNewSchedulePane.setVisible(false);

        classScheduleViewPane.setVisible(true);
        backToSchedulerPaneButton.setVisible(true);
        displayNewScheldue(new_scheldue, startWeekOfTerm);
    }

    private void displayNewScheldue(Schedule_result new_scheldue, int startWeekOfTerm) {
        mondayLessonsListView.getItems().clear();
        tuesdayLessonsListView.getItems().clear();
        wednesdayLessonsListView.getItems().clear();
        thursdayLessonsListView.getItems().clear();
        fridayLessonsListView.getItems().clear();
        saturdayLessonsListView.getItems().clear();
        sundayLessonsListView.getItems().clear();

        //start from first week ? start from the first week of the term
        //we show scheldue from start always , for easyness yep
        //extract booked rooms :)
        List<LocationInterface> rooms = new ArrayList<LocationInterface>();
        List<CourseInterface> bookedcourses = new_scheldue.getBookedCourses();
        //replace this with a server call to get all rooms , once scheldue is saved.
        for (CourseInterface c : bookedcourses) {
            List<LocationInterface> b = c.getBookedRooms();
            boolean match_found = false;
            for (LocationInterface broom : b) {
                for (LocationInterface r : rooms) {
                    if (r.getNameOfTheLocation().compareTo(broom.getNameOfTheLocation()) == 0) {
                        match_found = true;
                        break;
                    }
                }
                if (match_found == false) {
                    rooms.add(broom);
                }
            }
        }
        Map<Integer, List<BookingLocationsInterface>> All_Bookins = new TreeMap<Integer, List<BookingLocationsInterface>>();
        for (LocationInterface l : rooms) {
            Map<Integer, List<BookingLocationsInterface>> day_sortet = l.getBookingsForWeek(startWeekOfTerm);
            for (Map.Entry<Integer, List<BookingLocationsInterface>> entry : day_sortet.entrySet()) {
                int key = entry.getKey();
                List<BookingLocationsInterface> booklist = entry.getValue();
                List<BookingLocationsInterface> day_entries = All_Bookins.get(key);
                if (day_entries == null) {
                    day_entries = new ArrayList<BookingLocationsInterface>();
                }
                day_entries.addAll(booklist);
                All_Bookins.put(key, day_entries);
            }
        }

        //Sort all bookings by start time during their day
        //Display!
        weekNumber.clear();
        weekNumber.setText("This is schedule for week " + startWeekOfTerm);
        for (Map.Entry<Integer, List<BookingLocationsInterface>> entry : All_Bookins.entrySet()) {
            int dayOfWeek = entry.getKey() + 1; // account for diffrerence i calendar and our system
            List<BookingLocationsInterface> bookings = entry.getValue();
            for (BookingLocationsInterface booking : bookings) {

                LocationInterface r = booking.getCourse().getRoomReferencedByBooking(booking);
                String Booking_info = booking.getPeriodOfBooking().getStartDate().getHour() + " - " + booking.getPeriodOfBooking().getEndDate().getHour();
                Booking_info += " - " + booking.getCourse().getNameOfTheCourse() + " - " + r.getNameOfTheLocation() + " - " + booking.getCourse().getAssignedLecturer().getTeachersId();
                if (dayOfWeek == Calendar.MONDAY) {
                    mondayLessonsListView.getItems().add(Booking_info);
                } else if (dayOfWeek == Calendar.TUESDAY) {
                    tuesdayLessonsListView.getItems().add(Booking_info);
                } else if (dayOfWeek == Calendar.WEDNESDAY) {
                    wednesdayLessonsListView.getItems().add(Booking_info);
                } else if (dayOfWeek == Calendar.THURSDAY) {
                    thursdayLessonsListView.getItems().add(Booking_info);
                } else if (dayOfWeek == Calendar.FRIDAY) {
                    fridayLessonsListView.getItems().add(Booking_info);
                } else if (dayOfWeek == Calendar.SATURDAY) {
                    saturdayLessonsListView.getItems().add(Booking_info);
                } else if (dayOfWeek == Calendar.SUNDAY) {
                    sundayLessonsListView.getItems().add(Booking_info);
                }

            }
        }

    }

    @FXML
    private void handlenextWeekTeacherButton(ActionEvent event) {
        sundayLessonsListView.getItems().clear();
        saturdayLessonsListView.getItems().clear();
        fridayLessonsListView.getItems().clear();
        thursdayLessonsListView.getItems().clear();
        wednesdayLessonsListView.getItems().clear();
        tuesdayLessonsListView.getItems().clear();
        mondayLessonsListView.getItems().clear();
        String stringFromWeek = weekNumber.getText();
        String[] strings = stringFromWeek.split("week ");
        int currentweekNumber = Integer.parseInt(strings[1]);
        int nextWeekNumber = currentweekNumber + 1;
        if(nextWeekNumber > 52)
        {
            return;
        }
        Schedule_result res = clientController.getCurrentScheldue();
        displayNewScheldue(res, nextWeekNumber);
    }

    @FXML
    private void handlepreviousWeekButton(ActionEvent event) {
        sundayLessonsListView.getItems().clear();
        saturdayLessonsListView.getItems().clear();
        fridayLessonsListView.getItems().clear();
        thursdayLessonsListView.getItems().clear();
        wednesdayLessonsListView.getItems().clear();
        tuesdayLessonsListView.getItems().clear();
        mondayLessonsListView.getItems().clear();
        String stringFromWeek = weekNumber.getText();
        String[] strings = stringFromWeek.split("week ");
        int currentweekNumber = Integer.parseInt(strings[1]);
        int preWeekNumber = currentweekNumber - 1;
        if(preWeekNumber < 1)
        {
            return;
        }
        Schedule_result res = clientController.getCurrentScheldue();
        displayNewScheldue(res, preWeekNumber);
    }

}
