

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationInterface {

    private HealthAndFitnessMemberJDBCConnect connect;


    private int member_id;
    private int trainer_id;


    boolean quit;
    boolean validEmail = false;

    String userName;

    String userPassword;

    String userEmail;

    String userDateOfBirth;

    String userAddress;




    public ApplicationInterface(HealthAndFitnessMemberJDBCConnect connect ){

        this.connect = connect;
    }

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


    public void createMemberAccount(String userName, String userPassword, String userEmail, String userDateOfBirth, String userAddress){

        String insertAccount = "INSERT INTO member (username, password, email, date_of_birth, address) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(insertAccount);

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPassword);
            preparedStatement.setString(3, userEmail);
            preparedStatement.setDate(4, java.sql.Date.valueOf(userDateOfBirth));
            preparedStatement.setString(5, userAddress);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


    private void createTrainerAccount(String trainerName, String specialization, String startDate, String endDate) {

        String createTrainer = "INSERT INTO trainer (name, specialization, start_availability, end_availability) VALUES (?, ?, ?, ?)";

        try{

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedStartDate = dateFormat.parse(startDate);
            java.sql.Date startDateSQL = new java.sql.Date(parsedStartDate.getTime());

            java.util.Date parsedEndDate = dateFormat.parse(startDate);
            java.sql.Date endDateSQL = new java.sql.Date(parsedEndDate.getTime());


            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(createTrainer);
            preparedStatement.setString(1, trainerName);
            preparedStatement.setString(2, specialization);
            preparedStatement.setDate(3, startDateSQL);
            preparedStatement.setDate(4, endDateSQL);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean validateMemberLogin(String userName, String Password){

        String findUser = "SELECT member_id, username, password FROM member WHERE username = ? AND password = ?";

        try{

            PreparedStatement preparedStatement = this.connect.getConn().prepareStatement(findUser);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, Password);
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

    public void memberLogin(){

        System.out.println("1. Login\n2. Create Account?\nPress 0 to exit and close application");

        Scanner userInput = new Scanner(System.in);
        String userInputString = userInput.nextLine();

        if (Integer.parseInt(userInputString) == 1){

            System.out.println("Please Enter User name: ");
            Scanner scanUsername = new Scanner(System.in);
            this.userName = scanUsername.nextLine();

            int attempts = 0;
            while (attempts < 3) {

                System.out.println("Please Enter Password: ");
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
            Scanner scanUsername = new Scanner(System.in);
            this.userName = scanUsername.nextLine();

            if (checkIfMemberExists(userName)) {
                System.out.println("User already exists!");

            } else {


                System.out.println("Please Enter Password: ");
                Scanner scanUserPassword = new Scanner(System.in);
                this.userPassword = scanUserPassword.nextLine();

                while (!validEmail) {
                    System.out.println("Please Enter Email:");
                    Scanner scanEmail = new Scanner(System.in);
                    this.userEmail = scanEmail.nextLine();

                    emailValidator(userEmail);


                    if (!validEmail) {
                        System.out.println("Email not recognized!");
                    }

                }

                System.out.println("Please Enter Date of Birth");
                Scanner scanDateOfBirth = new Scanner(System.in);
                String dataOfBirthString = scanDateOfBirth.nextLine();
                this.userDateOfBirth = dataOfBirthString;

                System.out.println("Please enter Home address");
                Scanner scanHomeAddress = new Scanner(System.in);
                String homeAddressString = scanHomeAddress.nextLine();
                this.userAddress = homeAddressString;

                createMemberAccount(this.userName, this.userPassword, this.userEmail, this.userDateOfBirth, this.userAddress);


            }
        }


    }



    public void emailValidator(String email){

        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);

        Matcher matcher = pattern.matcher(email);
        this.validEmail = matcher.matches();

    }

    public void trainerLogin(){

        System.out.println("Would you like to:\n1. Login\n2. Create Account");
        Scanner userChoice = new Scanner(System.in);
        int userChoiceInt = userChoice.nextInt();

        if (userChoiceInt == 1) {

            System.out.println("Enter your user Name");
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
            Scanner scanTrainerName = new Scanner(System.in);
            String trainerName = scanTrainerName.nextLine();

            boolean exists = checkIfTrainerExists(trainerName);

            if (exists){
                System.out.println("Trainer already Exists!");

            }
            else{
                System.out.println("Enter Trainer specialization:");
                Scanner scanSpecialization = new Scanner(System.in);
                String specialization = scanSpecialization.nextLine();

                System.out.println("When does Trainer availability begin?");
                Scanner scanTrainerStart = new Scanner(System.in);
                String startDate = scanTrainerStart.nextLine();

                System.out.println("When does Trainer availability end");
                Scanner scanTrainerEnd = new Scanner(System.in);
                String endDate = scanTrainerEnd.nextLine();

                createTrainerAccount(trainerName, specialization, startDate, endDate);
            }


        }




    }

    public void adminLogin(){

        AdminFunctions admin = new AdminFunctions(this.connect);
        admin.startAdminFunctions();
    }

    public void run(){

        while(!quit){

            System.out.println("Application running : Please indicate whether you are a Member, Trainer, or Admin\nTo close the app press 0");

            Scanner userType = new Scanner(System.in);
            String userTypeInput = userType.nextLine();

            if (userTypeInput.equalsIgnoreCase("member")){


                memberLogin();

            }
            else if (userTypeInput.toLowerCase().equals("trainer")){
                trainerLogin();

            }
            else if (userTypeInput.toLowerCase().equals("admin")){

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
}
