package org.backend.Controllers;

import org.backend.HealthAndFitnessMemberJDBCConnect;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main interface to the application
 * @author Zarif, Arun
 * @version 1.0
 */
public class ApplicationInterface {

    private HealthAndFitnessMemberJDBCConnect connect;

    private static ApplicationInterface applicationInterface;


    private int member_id;
    private int trainer_id;


    boolean quit;
    boolean validEmail = false;

    String userName;

    String userPassword;

    String userEmail;

    String userDateOfBirth;

    String userAddress;


    /**
     * Constructor for the ApplicationInterface class.
     * Initializes the ApplicationInterface with the provided HealthAndFitnessMemberJDBCConnect object.
     * @param connect The HealthAndFitnessMemberJDBCConnect object to use for database connection.
     */

    public ApplicationInterface(HealthAndFitnessMemberJDBCConnect connect ){

        this.connect = connect;
    }

    public int getMember_id(){
        return this.member_id;
    }

    public int getTrainer_id(){return this.trainer_id; }

    /**
     * Checks if a member with the given username exists in the database.
     * @param userName The username to check for existence.
     * @return True if the member exists, otherwise false.
     */
    public boolean checkIfMemberExists(String userName){

        String findUser = "SELECT username FROM member WHERE username = ?";

        try{
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(findUser);
            preparedStatement.setString(1, userName);

            ResultSet userFound = preparedStatement.executeQuery();

            if (userFound.next()){
                return true;
            }
            else{
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * Checks if a trainer with the given username exists in the database.
     * @param userName The username to check for existence.
     * @return True if the trainer exists, otherwise false.
     */
    public boolean checkIfTrainerExists(String userName){

        String searchUser = "SELECT name FROM trainer WHERE name = ?";

        try{

            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(searchUser);
            preparedStatement.setString(1, userName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){

                return true;
            }
            else{
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a new member account in the database.
     * @param userName The username of the new member.
     * @param userPassword The password of the new member.
     */
    public void createMemberAccount(String userName, String userPassword){

        String insertAccount = "INSERT INTO member (username, password) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(insertAccount);

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPassword);

            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    /**
     * Creates a new trainer account in the database.
     * @param trainerName The name of the new trainer.
     * @param specialization The specialization of the new trainer.
     */

    public void createTrainerAccount(String trainerName, String specialization) {

        String createTrainer = "INSERT INTO trainer (name, specialization, start_availability, end_availability) VALUES (?, ?, ?, ?)";

        try{

            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(createTrainer);
            preparedStatement.setString(1, trainerName);
            preparedStatement.setString(2, specialization);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Validates the login credentials of a member.
     * @param userName The username of the member.
     * @param userPassword The password of the member.
     * @return True if the login credentials are valid, otherwise false.
     */

        public boolean validateMemberLogin(String userName, String userPassword){

            String findUser = "SELECT member_id, username, password FROM member WHERE username = ? AND password = ?";
            String userNameFormatted = userName.replaceAll("\\s+", "");
            try{

                PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(findUser);
                preparedStatement.setString(1, userNameFormatted);
                preparedStatement.setString(2, userPassword);
                ResultSet userMatch = preparedStatement.executeQuery();

                if (userMatch.next()){

                    this.member_id = userMatch.getInt("member_id");
                    return true;
                }
                else{
                    return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    /**
     * Validates the login credentials of a trainer.
     * @param userName The username of the trainer.
     * @return True if the login credentials are valid, otherwise false.
     */
    public boolean validateTrainerLogin(String userName){

        String validateLogin = "SELECT trainer_id, name FROM trainer WHERE name = ?";

        try{
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(validateLogin);
            preparedStatement.setString(1, userName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){

                this.trainer_id = resultSet.getInt("trainer_id");

                return true;

            }
            else{
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Initiates the login or account creation process for members.
     */
    public void memberLogin(){

        System.out.println("1. Login\n2. Create Account?\nPress 0 to exit and close application");

        @SuppressWarnings("resource")
        Scanner userInput = new Scanner(System.in);
        String userInputString = userInput.nextLine();

        if (Integer.parseInt(userInputString) == 1){

            System.out.println("Please Enter User name: ");
            @SuppressWarnings("resource")
            Scanner scanUsername = new Scanner(System.in);
            this.userName = scanUsername.nextLine();

            int attempts = 0;
            while (attempts < 3) {

                System.out.println("Please Enter Password: ");
                @SuppressWarnings("resource")
                Scanner scanUserPassword = new Scanner(System.in);
                this.userPassword = scanUserPassword.nextLine();

                boolean login = validateMemberLogin(userName, userPassword);

                if (login) {
                    System.out.println("Login success!");
                    MemberFunctions memberFunctions = new MemberFunctions(this.connect, this.member_id);
                    attempts = 3;
                    memberFunctions.startMemberFunctions();
                } else {
                    System.out.println("Incorrect Password!");
                    attempts ++;
                }
            }


        }
        else if (Integer.parseInt(userInputString) == 2) {

            System.out.println("Please Enter User name: ");
            @SuppressWarnings("resource")
            Scanner scanUsername = new Scanner(System.in);
            this.userName = scanUsername.nextLine();

            if (checkIfMemberExists(userName)) {
                System.out.println("User already exists!");

            } else {


                System.out.println("Please Enter Password: ");
                @SuppressWarnings("resource")
                Scanner scanUserPassword = new Scanner(System.in);
                this.userPassword = scanUserPassword.nextLine();

                while (!validEmail) {
                    System.out.println("Please Enter Email:");
                    @SuppressWarnings("resource")
                    Scanner scanEmail = new Scanner(System.in);
                    this.userEmail = scanEmail.nextLine();

                    emailValidator(userEmail);


                    if (!validEmail) {
                        System.out.println("Email not recognized!");
                    }

                }

                System.out.println("Please Enter Date of Birth");
                @SuppressWarnings("resource")
                Scanner scanDateOfBirth = new Scanner(System.in);
                String dataOfBirthString = scanDateOfBirth.nextLine();
                this.userDateOfBirth = dataOfBirthString;

                System.out.println("Please enter Home address");
                @SuppressWarnings("resource")
                Scanner scanHomeAddress = new Scanner(System.in);
                String homeAddressString = scanHomeAddress.nextLine();
                this.userAddress = homeAddressString;

//                createMemberAccount(this.userName, this.userPassword, this.userEmail, this.userDateOfBirth, this.userAddress);


            }
        }


    }

    /**
     * Validates an email address using regex pattern matching.
     * @param email The email address to validate.
     */

    public void emailValidator(String email){

        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);

        Matcher matcher = pattern.matcher(email);
        this.validEmail = matcher.matches();

    }
    /**
     * Initiates the login or account creation process for trainers.
     */
    public void trainerLogin(){

        System.out.println("Would you like to:\n1. Login\n2. Create Account");
        @SuppressWarnings("resource")
        Scanner userChoice = new Scanner(System.in);
        int userChoiceInt = userChoice.nextInt();

        if (userChoiceInt == 1) {

            System.out.println("Enter your user Name");
            @SuppressWarnings("resource")
            Scanner scanUserName = new Scanner(System.in);
            String userName = scanUserName.nextLine();

            if (validateTrainerLogin(userName)) {

                TrainerFunctions trainer = new TrainerFunctions(this.connect, this.trainer_id);
                trainer.startTrainerFunctions();

            } else {
                System.out.println("User does not exist!");
            }
        }
        else if (userChoiceInt == 2){

            System.out.println("Input a Trainer name:");
            @SuppressWarnings("resource")
            Scanner scanTrainerName = new Scanner(System.in);
            String trainerName = scanTrainerName.nextLine();

            boolean exists = checkIfTrainerExists(trainerName);

            if (exists){
                System.out.println("Trainer already Exists!");

            }
            else{
                System.out.println("Enter Trainer specialization:");
                @SuppressWarnings("resource")
                Scanner scanSpecialization = new Scanner(System.in);
                @SuppressWarnings("unused")
                String specialization = scanSpecialization.nextLine();

                System.out.println("When does Trainer availability begin?");
                @SuppressWarnings("resource")
                Scanner scanTrainerStart = new Scanner(System.in);
                @SuppressWarnings("unused")
                String startDate = scanTrainerStart.nextLine();

                System.out.println("When does Trainer availability end");
                @SuppressWarnings("resource")
                Scanner scanTrainerEnd = new Scanner(System.in);
                @SuppressWarnings("unused")
                String endDate = scanTrainerEnd.nextLine();

//                createTrainerAccount(trainerName, specialization, startDate, endDate);
            }


        }

    }
    /**
     * Initiates the login process for an admin user.
     * Creates an AdminFunctions object and starts the admin functions.
     */
    public void adminLogin(){

        AdminFunctions admin = new AdminFunctions(this.connect);
        admin.startAdminFunctions();
    }
    /**
     * Runs the application and handles user interactions.
     * Prompts the user to indicate whether they are a Member, Trainer, or Admin.
     * Initiates corresponding login processes based on user input.
     * Closes the application when the user chooses to quit.
     */
    public void run(){

        while(!quit){

            System.out.println("Application running : Please indicate whether you are a Member, Trainer, or Admin\nTo close the app press 0");

            @SuppressWarnings("resource")
            Scanner userType = new Scanner(System.in);
            String userTypeInput = userType.nextLine();

            if (userTypeInput.equalsIgnoreCase("member")){


                memberLogin();

            }
            else if (userTypeInput.equalsIgnoreCase("trainer")){
                trainerLogin();

            }
            else if (userTypeInput.equalsIgnoreCase("admin")){

//                try {

                    System.out.println("Authenticating...");
//                    Thread.sleep(5 * 1000);
//                    System.out.println("Scanning your eyeballs :P");
//                    Thread.sleep(2 * 1000);
//                    System.out.println("Sequencing...");
//                    Thread.sleep( 500);
//                    System.out.println("loading...");
//                    Thread.sleep( 500);
//                    System.out.println("loading...");
//                    Thread.sleep( 500);
//                    System.out.println("loading...");
                    adminLogin();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }

            }
            else{
                System.out.println("Shutting down...");
                this.quit = true;
                this.connect.closeJDBCConnection();
            }

        }

    }

    public static ApplicationInterface getInstance(){

        if (applicationInterface == null){
            applicationInterface = new ApplicationInterface(HealthAndFitnessMemberJDBCConnect.getInstance());
        }
        return applicationInterface;


    }



}
